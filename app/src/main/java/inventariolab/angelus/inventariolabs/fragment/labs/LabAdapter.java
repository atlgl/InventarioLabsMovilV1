package inventariolab.angelus.inventariolabs.fragment.labs;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import inventariolab.angelus.inventariolabs.R;
import inventariolab.angelus.inventariolabs.activity.MenuPrincipalActivity;
import inventariolab.angelus.inventariolabs.fragment.inventory.InventoryFragment;
import inventariolab.angelus.inventariolabs.fragment.inventory.InventoryShowItemActivity;
import inventariolab.angelus.inventariolabs.modelo.Laboratorios;

/**
 * Created by Angelus on 22/08/2017.
 */

public class LabAdapter extends RecyclerView.Adapter<LabAdapter.ItemLabHolder> {
    private List<Laboratorios> list;
    private Context ctx;

    //public AdapterView.OnItemClickListener onItemClickListener;


    public LabAdapter(List<Laboratorios> list,Context context) {
        this.list=list;
        this.ctx=context;
    }

    @Override
    public ItemLabHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_labs_select,parent,false);
        ItemLabHolder itemLabHolder=new ItemLabHolder(v);
        return itemLabHolder;
    }

    @Override
    public void onBindViewHolder(ItemLabHolder holder, final int position) {
        holder.txtid.setText(String.valueOf(list.get(position).getId()));
        holder.txtdesc.setText(list.get(position).getName());
        holder.btnlab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InventoryFragment fragment=new InventoryFragment();
                Bundle bundle=new Bundle();

                Laboratorios lab=list.get(position);
                bundle.putParcelable("inventoryid",lab);
                fragment.setArguments(bundle);
                if(ctx instanceof MenuPrincipalActivity){
                    MenuPrincipalActivity menu=(MenuPrincipalActivity)ctx;
                    menu.switchContent(R.id.contenedorFragmetos,fragment);



                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ItemLabHolder extends RecyclerView.ViewHolder{
        public ImageButton btnlab;
        public TextView txtdesc;
        public TextView txtid;

        public ItemLabHolder(View itemView) {
            super(itemView);
            btnlab=(ImageButton) itemView.findViewById(R.id.btn_item_labs_select);
            txtdesc=(TextView) itemView.findViewById(R.id.txt_item_labs_select);
            txtid=(TextView) itemView.findViewById(R.id.txtid_item_labs_select);
            /*btnlab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent t=new Intent(v.getContext(), InventoryShowItemActivity.class);
                    t.putExtra("inventoryid",String.valueOf(txtid.getText()));
                    v.getContext().startActivity(t);
                    Snackbar.make(v,"Mensaje"+txtdesc.getText().toString(),Snackbar.LENGTH_SHORT).show();
                }
            });*/

            //itemView.setOnClickListener();
        }
    }

}
