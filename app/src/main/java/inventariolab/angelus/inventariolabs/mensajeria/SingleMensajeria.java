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
    private static String host="http://www.legionx.com.mx/inventariolabs/public/android/";
    //host de prueba local
    //public static String host="http://10.0.2.2/inventariolabs/public/android/";
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
