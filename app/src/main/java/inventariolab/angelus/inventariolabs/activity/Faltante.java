package inventariolab.angelus.inventariolabs.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import inventariolab.angelus.inventariolabs.R;
import inventariolab.angelus.inventariolabs.fragment.labs.LabAdapter;
import inventariolab.angelus.inventariolabs.mensajeria.SingleMensajeria;
import inventariolab.angelus.inventariolabs.modelo.Laboratorios;
import inventariolab.angelus.inventariolabs.modelo.Software;

public class Faltante  extends Fragment  {

 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faltante);
    }*/

    Spinner spinerLab;
    String url="http://http://www.legionx.com.mx/inventariolabs/public/android/software";


    public Faltante() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


    //    View v=inflater.inflate(R.layout.activity_faltante, container, false);
   //     spinerLab=(Spinner) v.findViewById(R.id.spinLaboratorio);
    //    getLaboratories();
        return  inflater.inflate(R.layout.activity_faltante, container, false);
    }






    public void getLaboratories(){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    List<Laboratorios> inventoryList=new ArrayList<>();
                    JSONArray jsonArray=response;
                    Gson gson=new Gson();
                    // ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.two_line_list_item);

                    for(int i=0;i<jsonArray.length();i++){
                        Laboratorios lab= gson.fromJson(jsonArray.getJSONObject(i).toString(),Laboratorios.class);
                        inventoryList.add(lab);
                    }

                    spinerLab.setAdapter((SpinnerAdapter) new LabAdapter(inventoryList,getContext()));

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        SingleMensajeria.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
    }


}
