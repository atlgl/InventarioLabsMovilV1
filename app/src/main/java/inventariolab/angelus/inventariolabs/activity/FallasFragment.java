package inventariolab.angelus.inventariolabs.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import inventariolab.angelus.inventariolabs.R;
import inventariolab.angelus.inventariolabs.modelo.Fallas;
import inventariolab.angelus.inventariolabs.modelo.Software;

public class FallasFragment extends Fragment {


    List<Fallas> softwareList ;
    private RecyclerView recyclerView;
   private ListView listView ;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_fallas , container, false);

        listView=(ListView) v.findViewById(R.id.milista_fallas_select);
        getFallas();

        return v;
    }

    List<String> lstSoftware =  new ArrayList<String>();

    private void getFallas(){

        String url="http://www.legionx.com.mx/inventariolabs/public/android/fail";
        RequestQueue volleyCola= Volley.newRequestQueue(getContext());
        softwareList = new ArrayList<Fallas>();
         lstSoftware =  new ArrayList<String>();



        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Fallas p;
                JSONObject result=null;
                try{
                    for (int i=0;i<response.length();i++) {
                        result = (JSONObject) response.getJSONObject(i);
                        p = new Fallas();
                        p.setId(((Integer) result.getInt("id")).intValue());
                        p.setInventory_id(result.getInt("inventory_id"));
                        p.setDesc(result.getString("desc"));
                        p.setFailtype(result.getString("failtype"));
                        p.setFailstate(result.getString("failstate"));



                        lstSoftware.add(p.toPresentaFalla()) ;
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

