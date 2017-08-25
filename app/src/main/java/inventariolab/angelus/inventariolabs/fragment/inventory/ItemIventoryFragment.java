package inventariolab.angelus.inventariolabs.fragment.inventory;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import inventariolab.angelus.inventariolabs.R;
import inventariolab.angelus.inventariolabs.fragment.labs.LabAdapterSpinner;
import inventariolab.angelus.inventariolabs.mensajeria.SingleMensajeria;
import inventariolab.angelus.inventariolabs.modelo.Inventory;
import inventariolab.angelus.inventariolabs.modelo.Laboratorios;

public class ItemIventoryFragment extends Fragment {


    private Inventory inv;//el inventario principal para toda la clase y pueda usar todos los campos
    private TextView txtid;
    private EditText txtbarcode;
    private EditText txtmodelo;
    private EditText txtmarca;
    private Spinner spinestado;
    private EditText txtdesc;
    private EditText txtlab;


    private ImageButton barcode;
    private ImageButton fallas;
    private ImageButton software;
    private ImageButton perdidas;

    public ItemIventoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //permite que un fragmento tenga diferentes menus
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        Bundle bundle=getArguments();

        inv=bundle.getParcelable("inventoryobj");

        View v=inflater.inflate(R.layout.fragment_item_iventory, container, false);
        txtid=(TextView) v.findViewById(R.id.txt_item_inventory_id);
        txtbarcode=(EditText) v.findViewById(R.id.txt_item_inventory_codigobarra);
        txtmodelo=(EditText) v.findViewById(R.id.txt_item_inventory_modelo);
        txtmarca=(EditText) v.findViewById(R.id.txt_item_inventory_marca);
        txtdesc=(EditText) v.findViewById(R.id.txt_item_inventory_desc);
        txtlab=(EditText) v.findViewById(R.id.txt_item_inventory_lab);
        spinestado=(Spinner) v.findViewById(R.id.spin_item_inventory_estado);

        txtid.setText(String.valueOf(inv.getId()));
        txtbarcode.setText(inv.getBarcode());
        txtmodelo.setText(inv.getComputer().getModelname());
        txtmarca.setText(inv.getComputer().getMark().getName());
        txtlab.setText(inv.getLab().getName());
        //ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getContext(),R.array.item_inventory_state,android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinestado.setAdapter(adapter);




        fallas=(ImageButton) v.findViewById(R.id.btn_item_inventory_fallas);
        software=(ImageButton) v.findViewById(R.id.btn_item_inventory_software);
        perdidas=(ImageButton) v.findViewById(R.id.btn_item_inventory_perdidas);
        barcode=(ImageButton) v.findViewById(R.id.btn_item_inventory_barcode);
        getListLaboratories();






        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_inventory, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.menu_del)
        {
           deleteInventory();
        }
        if (id==R.id.menu_save)
        {
            getFragmentManager().popBackStackImmediate();
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteInventory(){

        String url=SingleMensajeria.urlinventory+"/"+inv.getId();

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.DELETE, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Snackbar.make(getView(),"Elemento Eliminado",Toast.LENGTH_SHORT).show();

                getFragmentManager().popBackStack();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        SingleMensajeria.getInstance(getContext()).addToRequestQueue(request);
    }


    private void getListLaboratories(){

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, SingleMensajeria.urllab, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    List<Laboratorios> laboratoriosList = new ArrayList<>();
                    Gson g = new Gson();
                    for (int i = 0; i < response.length(); i++) {
                        Laboratorios laboratorios = g.fromJson(response.getJSONObject(i).toString(), Laboratorios.class);
                        laboratoriosList.add(laboratorios);
                    }
                    LabAdapterSpinner labAdapterSpinner=new LabAdapterSpinner(getContext(),laboratoriosList);
                    spinestado.setAdapter(labAdapterSpinner);
                    int pos=labAdapterSpinner.getElementPosition(inv.getLab());
                    spinestado.setSelection(pos);


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
