package inventariolab.angelus.inventariolabs.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import inventariolab.angelus.inventariolabs.R;

public class BajaEquipo extends Fragment {



    public BajaEquipo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_asigna_equipo, container, false);
    }
}
