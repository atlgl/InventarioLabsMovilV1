package inventariolab.angelus.inventariolabs.fragment.inventory;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

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
import inventariolab.angelus.inventariolabs.modelo.Inventory;
import inventariolab.angelus.inventariolabs.modelo.Laboratorios;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryFragment extends Fragment {

    private List<Inventory> inventoryList;

    private ListView listView;
    private Spinner spinner;

    public InventoryFragment() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_inventory, container, false);
        listView=(ListView) v.findViewById(R.id.listview_inventory_computers);
        spinner=(Spinner) v.findViewById(R.id.spin_inventory_lab);
        //listView.setAdapter(new InventoryAdapter(getContext(),inventoryList));
        getInventories();


        return v;
    }

    public void getLaboratories(){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, SingleMensajeria.urllab, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    List<Laboratorios> inventoryList;
                    inventoryList=new ArrayList<>();
                    JSONArray jsonArray=response;
                    Gson gson=new Gson();
                   // ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.two_line_list_item);

                    for(int i=0;i<jsonArray.length();i++){
                        Laboratorios lab= gson.fromJson(jsonArray.getJSONObject(i).toString(),Laboratorios.class);
                        inventoryList.add(lab);
                    }




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

    public void getInventories(){


        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, SingleMensajeria.urlinventory, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    List<Inventory> inventoryList;
                    inventoryList=new ArrayList<>();
                    JSONArray jsonArray=response;
                    Gson gson=new Gson();
                    for(int i=0;i<jsonArray.length();i++){
                        Inventory inventory= gson.fromJson(jsonArray.getJSONObject(i).toString(),Inventory.class);
                        inventoryList.add(inventory);
                    }

                    listView.setAdapter(new InventoryAdapter(getContext(),inventoryList));
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
