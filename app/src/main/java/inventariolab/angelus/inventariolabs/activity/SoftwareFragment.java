package inventariolab.angelus.inventariolabs.activity;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import inventariolab.angelus.inventariolabs.R;
import inventariolab.angelus.inventariolabs.fragment.inventory.SoftwareAdapter;
import inventariolab.angelus.inventariolabs.fragment.inventory.SwAdapterSpinner;
import inventariolab.angelus.inventariolabs.fragment.labs.LabAdapter;
import inventariolab.angelus.inventariolabs.modelo.Software;

public class SoftwareFragment  extends Fragment {


    List<Software> softwareList ;
    private RecyclerView recyclerView;
   private ListView listView ;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_software , container, false);
     //   recyclerView=(RecyclerView) v.findViewById(R.id.listview_sw_select);
        listView=(ListView) v.findViewById(R.id.milista_sw_select);
        getSoftware();

        return v;
    }

    List<String> lstSoftware =  new ArrayList<String>();

    private void getSoftware(){

        String url="http://www.legionx.com.mx/inventariolabs/public/android/software";
        RequestQueue volleyCola= Volley.newRequestQueue(getContext());
        softwareList = new ArrayList<Software>();
         lstSoftware =  new ArrayList<String>();



        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Software p;
                JSONObject result=null;
                try{
                    for (int i=0;i<response.length();i++) {
                        result = (JSONObject) response.getJSONObject(i);
                        p = new Software();
                        p.setId(((Integer) result.getInt("id")).intValue());
                        p.setName(result.getString("name"));
                        p.setDesc(result.getString("desc"));

                        lstSoftware.add(""+ result.getInt("id") + " "+result.getString("name") + "  -  "+result.getString("desc") );
                  //    softwareList.add(p);

                    }




                    ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),  android.R.layout.simple_list_item_1, lstSoftware.toArray());


                    listView.setAdapter(arrayAdapter );



                }catch (Exception ex){
                    ex.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getStackTrace();
            }
        });
        volleyCola.add(jsonArrayRequest);

    }
}

