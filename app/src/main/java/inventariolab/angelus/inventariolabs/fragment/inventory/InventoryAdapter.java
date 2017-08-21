package inventariolab.angelus.inventariolabs.fragment.inventory;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import inventariolab.angelus.inventariolabs.R;
import inventariolab.angelus.inventariolabs.modelo.Inventory;

/**
 * Created by Angelus on 20/08/2017.
 */

public class InventoryAdapter extends ArrayAdapter<Inventory> {

    private Context ctx;
    private List<Inventory> inventoryList;


    public InventoryAdapter(@NonNull Context context,List<Inventory> list) {
        super(context, R.layout.item_inventory,list);
        this.ctx=context;
        this.inventoryList=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=LayoutInflater.from(ctx).inflate(R.layout.item_inventory,parent,false);

        TextView txtid=(TextView) v.findViewById(R.id.txt_inventory_id);
        TextView txtmodelo=(TextView) v.findViewById(R.id.txt_inventory_modelo);
        TextView txtestado=(TextView) v.findViewById(R.id.txt_inventory_estado);
        TextView txtmarca=(TextView) v.findViewById(R.id.txt_inventory_marca);

        txtid.setText("ID: "+String.valueOf(inventoryList.get(position).getId()));
        txtestado.setText("Estado: "+inventoryList.get(position).getInventorystate());
        txtmodelo.setText("Modelo: "+inventoryList.get(position).getComputer().getModelname());
        txtmarca.setText("Marca "+inventoryList.get(position).getComputer().getMark().getName());

        return v;
    }
}
