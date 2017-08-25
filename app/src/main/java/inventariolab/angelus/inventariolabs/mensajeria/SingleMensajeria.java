package inventariolab.angelus.inventariolabs.mensajeria;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Angelus on 20/08/2017.
 * Clase singleton para invocar el Volley
 * Permite crear una sola cola de mensajeria para toda la aplicacion
 * permite ahorrar los recursos para que no se consuman mas de los necesarios
 * o crear una nueva cola de mensajes
 */

public class SingleMensajeria {

    //host principal
    //private static String host="http://www.legionx.com.mx/inventariolabs/public/android/";
    //host de prueba local
    public static String host="http://10.0.2.2/inventariolabs/public/android/";
    //devuelve todos los inventarios
    public static String urlinventory=host+"inventory";
    //devuelve todos los laboratorios
    public static String urllab=host+"lab";

    //devuele el catalogo de software
    public static String urlsoftware=host+"software";

    //devuelve los usuarios
    public static String urlususrio=host+"user";

    //devuelve los inventarios por codigodebarras findinventory/12
    public static String urlfindinventory=host+"findinventory";

    //devuelve el inventario por laboratorio
    public static String urlfindbylab=host+"findbylab";


    //ligas para las fallas
    //cambiar el metodo si quieres eliminar usa el Request.Method.DELETE y concadena asi  /id  id es el elemento a eliminar
    //cambiar el metodo si quieres guardar un elelemento usa POST y no concadenes nada a la url y mando los
    // nombres de los campos tal cual estan en la base de datos te devolvera el mensaje de que se a creado
    //para buscar una falla solo agrega /id y usa el metodo get
    //para pasar valores entre fragmentos en el modelo agrega parseable el editor te ayuda con alt + enter

    public static String urlfail=host+"fail";




    private static SingleMensajeria mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private SingleMensajeria(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

    }

    public static synchronized SingleMensajeria getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SingleMensajeria(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


}
