package inventariolab.angelus.inventariolabs.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import inventariolab.angelus.inventariolabs.R;
import inventariolab.angelus.inventariolabs.fragment.inventory.InventoryFragment;
import inventariolab.angelus.inventariolabs.fragment.inventory.ItemIventoryFragment;
import inventariolab.angelus.inventariolabs.fragment.inventory.SoftwareAdapter;
import inventariolab.angelus.inventariolabs.fragment.labs.LaboratoryFragment;
import inventariolab.angelus.inventariolabs.fragment.scanner.SimpleScannerFragment;
import inventariolab.angelus.inventariolabs.mensajeria.SingleMensajeria;
import inventariolab.angelus.inventariolabs.modelo.Inventory;
import inventariolab.angelus.inventariolabs.modelo.Software;

public class MenuPrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LaboratoryFragment laboratoryFragment;
    private AsignaEquipo asignaEquipo;
    private Faltante faltante;
    private  BajaEquipo bajaEquipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Agregar una Computadora al Inventario", Snackbar.LENGTH_LONG)
                        .setAction("Action",null ).show();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        //carga el primer fragmento comentado por angel
    //    loadFramgent(1); comentado por Uriel
        loadFramgent(1);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_fails) {
            loadFramgent(3);
        } else if (id == R.id.nav_inventory) {
            //carga el inventario por laboratorios
            loadFramgent(1);
        } else if (id == R.id.nav_software) {
            loadFramgent(2);
        } else if (id == R.id.nav_fails_reports) {
            loadFramgent(4);
        } else if( id== R.id.nav_codebar){
            //busca un codigo de barras y muesta su informacion
            //se usa un staractivity for result por que espera a la camara
            //a que termine para trabajar con el resultado obtenido del lector de codigo de barras
            //el handler es onActivityResult
            //startActivityForResult(new Intent(getBaseContext(), SimpleScannerFragment.class),1);

            getIventoryFromBarCode("7702111333970");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /***
     * Manejador que se dispara cuando termina un staractivityfor result en este caso espera por
     * el resultado de la lectura del codigo de barras para buscar en el inventario y mostrar la informacion
     * @param requestCode codigo de respuesta
     * @param resultCode codifo de resultado
     * @param data la data esta envuelta en un intent y se requiere un objeto parceable
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            String code=data.getStringExtra("Code");
            //Toast.makeText(getBaseContext(),"code"+code,Toast.LENGTH_SHORT).show();
            getIventoryFromBarCode(code);
        }
    }

    private FragmentManager fragmentManager;

    public void loadFramgent(int opc){
        fragmentManager=getFragmentManager();
        switch (opc){
            case 1:
                if(laboratoryFragment==null) {
                    laboratoryFragment = new LaboratoryFragment();
                }
                fragmentManager.beginTransaction().replace(R.id.contenedorFragmetos,laboratoryFragment).commit();
                break;
            case 2:
                if(asignaEquipo==null) {
                    asignaEquipo = new AsignaEquipo();
                }
                fragmentManager.beginTransaction().replace(R.id.contenedorFragmetos,asignaEquipo).commit();
            break;
            case 3:
                if(faltante==null) {
                    faltante = new Faltante();
                }
  //              getSoftware();
                fragmentManager.beginTransaction().replace(R.id.contenedorFragmetos,faltante).commit();
                break;
            case 4:
                if(bajaEquipo==null) {
                    bajaEquipo = new BajaEquipo();
                }
                fragmentManager.beginTransaction().replace(R.id.contenedorFragmetos,bajaEquipo).commit();
                break;

            case 11:

                break;
            default:
                break;
        }
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


    public  void switchContent(int id,Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.contenedorFragmetos,fragment).commit();
    }

    /**
     * Funcion que busca un codigo de barras
     * @param barcode recibe el codigo de barras a buscar en la bd
     */

    private void getIventoryFromBarCode(String barcode){
        //construye la url para enviar la informacion por el metodo get
        String url=SingleMensajeria.urlfindinventory+"/"+barcode;

        JsonArrayRequest jsonObjectRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {

                    Log.d("Responce", response.toString());

                    if (response != null) {
                        //gson se encarga de hacer el trabajo sucio de transformar un objeto en un inventario
                        //la unica condicion de gson es que los campos se deben llamar tal cual en el objeto json
                        Gson gson = new Gson();
                        //transforma un objeto gson a un inventario

                        Inventory inventory = gson.fromJson(response.getJSONObject(0).toString(), Inventory.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("inventoryobj", inventory);
                        ItemIventoryFragment itemIventoryFragment = new ItemIventoryFragment();
                        itemIventoryFragment.setArguments(bundle);
                        switchContent(R.id.contenedorFragmetos, itemIventoryFragment);
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



        SingleMensajeria.getInstance(getBaseContext()).addToRequestQueue(jsonObjectRequest);
    }

    List softwareList ;
    Software software;
    Spinner spinerSoftware;
    String[] contenido;
    ArrayAdapter<String> adaptador;

    private void getSoftware(){

        String url="http://www.legionx.com.mx/inventariolabs/public/android/software";
        RequestQueue volleyCola= Volley.newRequestQueue(getBaseContext());

        spinerSoftware = (Spinner) findViewById(R.id.spinFaltanteSoft) ;

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                softwareList =new ArrayList<>();
                try{
                    for (int i=0;i<response.length();i++){
                        JSONObject object=response.getJSONObject(i);
                        Software p=new Software();
                        software.setId(object.getInt("id"));
                        software.setName(object.getString("name"));
                        software.setDesc(object.getString("desc"));

                        softwareList.add(software);
                    }}catch (Exception ex){
                    ex.printStackTrace();
                }
                //Toast.makeText(getBaseContext(),response.toString(),Toast.LENGTH_LONG).show();
                spinerSoftware.setAdapter(new SoftwareAdapter(getBaseContext(),softwareList));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        volleyCola.add(jsonArrayRequest);
        int i = softwareList.size();
    }
}
