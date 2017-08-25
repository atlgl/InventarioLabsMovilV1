package inventariolab.angelus.inventariolabs.fragment.labs;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import inventariolab.angelus.inventariolabs.R;
import inventariolab.angelus.inventariolabs.modelo.Laboratorios;

/**
 * Created by Angelus on 25/08/2017.
 */

public class LabAdapterSpinner extends ArrayAdapter<Laboratorios> {

    private List<Laboratorios> laboratoriosList;
    public LabAdapterSpinner(@NonNull Context context, List<Laboratorios> laboratoriosList) {
        super(context,android.R.layout.simple_spinner_item,laboratoriosList);
        this.laboratoriosList=laboratoriosList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /*View v=LayoutInflater.from(getContext()).inflate(R.layout.item_spin_lab,null,false);
        TextView lblid=(TextView) v.findViewById(R.id.txt_item_spin_id);
        TextView lbldesc=(TextView) v.findViewById(R.id.txt_item_spin_desc);
        lblid.setText(String.valueOf(laboratoriosList.get(position).getId()));
        lbldesc.setText(String.valueOf(laboratoriosList.get(position).getDesc()));*/
        TextView label = new TextView(getContext());
        label.setTextColor(Color.BLACK);
        label.setText(laboratoriosList.get(position).getName());

        return label;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = new TextView(getContext());
        label.setTextColor(Color.BLACK);
        label.setText(laboratoriosList.get(position).getName());

        return label;
    }

    public int getElementPosition(Laboratorios laboratorios){
        return laboratoriosList.indexOf(laboratorios);
    }


}
