package inventariolab.angelus.inventariolabs.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import java.util.LinkedList;
import java.util.List;

import inventariolab.angelus.inventariolabs.R;
import inventariolab.angelus.inventariolabs.fragment.inventory.InventoryAdapter;
import inventariolab.angelus.inventariolabs.fragment.inventory.ItemIventoryFragment;
import inventariolab.angelus.inventariolabs.fragment.inventory.SWAdapter;

import inventariolab.angelus.inventariolabs.fragment.inventory.SwAdapterSpinner;
import inventariolab.angelus.inventariolabs.fragment.labs.LabAdapter;
import inventariolab.angelus.inventariolabs.fragment.labs.LabAdapterSpinner;
import inventariolab.angelus.inventariolabs.mensajeria.SingleMensajeria;
import inventariolab.angelus.inventariolabs.modelo.Laboratorios;
import inventariolab.angelus.inventariolabs.modelo.Software;

public class FaltanteFragment extends Fragment  {

 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faltante);
    }*/

    Spinner spinerLab;
    String url="http://http://www.legionx.com.mx/inventariolabs/public/android/software";

    private RecyclerView recyclerView;
    LinkedList<Software> softwareList ;
    Software software;
    Spinner spinerSoftware;
    String[] contenido;
    ArrayAdapter<String> adaptador;
 //   private ListView listView;

    public FaltanteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private Software inv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle=getArguments();
//        Software software=bundle.getParcelable("inventoryid");
        View v=inflater.inflate(R.layout.activity_faltante, container, false);
      //  listView=(TextView) v.findViewById(R.id.textViewSpinner);
        spinerSoftware=(Spinner) v.findViewById(R.id.spinFaltanteLab);
   //     inv=bundle.getParcelable("inventoryobj");

//        recyclerView=(RecyclerView) v.findViewById(R.id.spinFaltanteSoft);
    //    spinerSoftware = (Spinner)  v.findViewById(R.id.spinFaltanteSoft) ;
     //   getLaboratories();
       getSoftware();
        return  v;
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



    private void getSoftware(){

        String url="http://www.legionx.com.mx/inventariolabs/public/android/software";
        RequestQueue volleyCola= Volley.newRequestQueue(getContext());
        softwareList = new LinkedList();




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

                        //softwareList.add( result.getInt("id") + " "+result.getString("name"));
                        softwareList.add(p);

                    }

                        spinerSoftware.setAdapter(new SwAdapterSpinner(getContext(),softwareList));

                  /*  SwAdapterSpinner labAdapterSpinner=new SwAdapterSpinner(getContext(),softwareList);
                    spinerSoftware.setAdapter(labAdapterSpinner);
                    int pos=labAdapterSpinner.getElementPosition(inv.getLab());
                  s  spinerSoftware.setPromptId(pos);
*/


 /*                       spinerSoftware.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ItemIventoryFragment fragment=new ItemIventoryFragment();
                                Bundle bundle=new Bundle();
                                bundle.putParcelable("inventoryobj",softwareList.get(position));
                                fragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.contenedorFragmetos,fragment).commit();
                            }
                        });
*/


                    }catch (Exception ex){
                    ex.printStackTrace();
                }

        //        spinerSoftware.setAdapter((SpinnerAdapter) new SwAdapterSpinner(getContext(),softwareList));
             //   SwAdapterSpinner


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
