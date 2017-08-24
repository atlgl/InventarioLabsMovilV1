package inventariolab.angelus.inventariolabs.fragment.inventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class InventoryShowItemActivity extends AppCompatActivity {

    private List<Inventory> inventoryList;

    private String urlId=null;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inventory);

        Intent intent=getIntent();
        urlId=intent.getStringExtra("inventoryid");

        listView=(ListView) findViewById(R.id.listview_inventory_computers);
        getInventories();

    }


    public void getInventories(){


        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, SingleMensajeria.urlinventory+"/"+urlId, null, new Response.Listener<JSONArray>() {
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

                    listView.setAdapter(new InventoryAdapter(getBaseContext(),inventoryList));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(getBaseContext(),InventoryFragment.class);
                            Inventory inventory= (Inventory) listView.getSelectedItem();
                            intent.putExtra("inventorycomputer",inventory);
                            startActivity(intent);
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
        SingleMensajeria.getInstance(getBaseContext()).addToRequestQueue(jsonArrayRequest);
    }

}
