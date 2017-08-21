package inventariolab.angelus.inventariolabs.mensajeria;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Angelus on 20/08/2017.
 */

public class SingleMensajeria {

    private static String host="http://www.legionx.com.mx";
    //public static String host="http://10.0.2.2";
    public static String urlinventory=host+"/inventariolabs/public/android/inventory";
    public static String urllab=host+"/inventariolabs/public/android/lab";

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
