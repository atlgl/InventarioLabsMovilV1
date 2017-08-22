package inventariolab.angelus.inventariolabs.fragment.inventory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import inventariolab.angelus.inventariolabs.R;
import inventariolab.angelus.inventariolabs.modelo.Laboratorios;

/**
 * Created by Angelus on 22/08/2017.
 */

public class LabAdapter extends ArrayAdapter<Laboratorios> {
    private List<Laboratorios> list;
    private Context ctx;

    public LabAdapter(@NonNull Context context, List<Laboratorios> laboratorioses) {
        super(context, R.layout.item_spin_lab);
        this.ctx=context;
        this.list=laboratorioses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View inflater=LayoutInflater.from(ctx).inflate(R.layout.item_spin_lab,parent,false);
        TextView txtid=(TextView) inflater.findViewById(R.id.txt_item_spin_id);
        TextView txtlab=(TextView) inflater.findViewById(R.id.spin_inventory_lab);


        return super.getView(position, convertView, parent);
    }
}
