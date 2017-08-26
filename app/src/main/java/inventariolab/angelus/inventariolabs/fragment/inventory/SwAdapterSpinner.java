package inventariolab.angelus.inventariolabs.fragment.inventory;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import inventariolab.angelus.inventariolabs.modelo.Laboratorios;
import inventariolab.angelus.inventariolabs.modelo.Software;

/**
 * Created by Angelus on 25/08/2017.
 */

public class SwAdapterSpinner   extends ArrayAdapter<Software> {

    private List<Software> softwareList;


    public SwAdapterSpinner(@NonNull Context context, List<Software> swfList) {
        super(context,android.R.layout. simple_spinner_item,swfList);
        this.softwareList=swfList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView label = new TextView(getContext());
        label.setTextColor(Color.BLACK);
        label.setText(softwareList.get(position).getName());

        return label;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = new TextView(getContext());
        label.setTextColor(Color.BLACK);
        label.setText(softwareList.get(position).getName());

        return label;
    }

    public int getElementPosition(Software software){
        return softwareList.indexOf(software);
    }


}
