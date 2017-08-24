package inventariolab.angelus.inventariolabs.fragment.inventory;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import inventariolab.angelus.inventariolabs.fragment.labs.LabAdapter;
import inventariolab.angelus.inventariolabs.mensajeria.SingleMensajeria;
import inventariolab.angelus.inventariolabs.modelo.Inventory;
import inventariolab.angelus.inventariolabs.modelo.Laboratorios;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryFragment extends Fragment {

    private List<Inventory> inventoryList;

    private ListView listView;

    private String urlid;

    public InventoryFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        Laboratorios laboratorios=bundle.getParcelable("inventoryid");
        urlid=String.valueOf(laboratorios.getId());
        View v=inflater.inflate(R.layout.fragment_inventory, container, false);
        listView=(ListView) v.findViewById(R.id.listview_inventory_computers);
        getInventories();
        return v;
    }



    public void getInventories(){


        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, SingleMensajeria.urlinventory+"/"+urlid, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    final List<Inventory> inventoryList;
                    inventoryList=new ArrayList<>();
                    JSONArray jsonArray=response;
                    Gson gson=new Gson();
                    for(int i=0;i<jsonArray.length();i++){
                        Inventory inventory= gson.fromJson(jsonArray.getJSONObject(i).toString(),Inventory.class);
                        inventoryList.add(inventory);
                    }

                    listView.setAdapter(new InventoryAdapter(getContext(),inventoryList));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ItemIventoryFragment fragment=new ItemIventoryFragment();
                            Bundle bundle=new Bundle();
                            bundle.putParcelable("inventoryobj",inventoryList.get(position));
                            fragment.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.contenedorFragmetos,fragment).commit();
                            }
                    });
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

