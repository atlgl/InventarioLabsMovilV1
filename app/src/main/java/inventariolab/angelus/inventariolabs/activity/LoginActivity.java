package inventariolab.angelus.inventariolabs.activity;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import inventariolab.angelus.inventariolabs.R;
import inventariolab.angelus.inventariolabs.mensajeria.SingleMensajeria;
import inventariolab.angelus.inventariolabs.modelo.Usuarios;

public class LoginActivity extends AppCompatActivity {

    private EditText txtusuario;
    private EditText txtpass;
    private ImageButton btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initgui();

    }

    public void initgui(){
        txtusuario=(EditText) findViewById(R.id.txt_login_user);
        txtpass=(EditText) findViewById(R.id.txt_login_pass);

        btnlogin=(ImageButton) findViewById(R.id.btn_login_user);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtusuario.getText().length()>0 && txtpass.getText().length()>0){
                    iniciarsesion(txtusuario.getText().toString(),txtpass.getText().toString());
                }else
                    Snackbar.make(v,"Escribir un nombre de usuario y password",Snackbar.LENGTH_SHORT).show();

            }
        });
    }


    public void iniciarsesion(final String usuario, String pass){
        final JSONObject object=new JSONObject();
        try {
            object.put("email",usuario);
            object.put("pass",pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.POST, SingleMensajeria.urllogin, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if(response!=null){

                    //{"mensaje":"logeado","email":"mtroangelsanchez@hotmail.com"}
                    JSONObject object1=response;
                    try {
                        if(object1.getString("error").equals("0")){
                            Intent intent=new Intent(getBaseContext(),MenuPrincipalActivity.class);
                            intent.putExtra("email",usuario);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(getBaseContext(),"Error en acceso al inventario",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        SingleMensajeria.getInstance(getBaseContext()).addToRequestQueue(objectRequest);
    }

}
