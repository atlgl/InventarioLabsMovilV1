package inventariolab.angelus.inventariolabs.fragment.labs;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import inventariolab.angelus.inventariolabs.R;
import inventariolab.angelus.inventariolabs.mensajeria.SingleMensajeria;
import inventariolab.angelus.inventariolabs.modelo.Laboratorios;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaboratoryFragment extends Fragment {


    public LaboratoryFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_laboratory, container, false);
        recyclerView=(RecyclerView) v.findViewById(R.id.listview_labs_select);
        getLaboratories();

        return v;
    }


    public void getLaboratories(){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, SingleMensajeria.urllab, null, new Response.Listener<JSONArray>() {
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
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),2);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(new LabAdapter(inventoryList,getContext()));

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
