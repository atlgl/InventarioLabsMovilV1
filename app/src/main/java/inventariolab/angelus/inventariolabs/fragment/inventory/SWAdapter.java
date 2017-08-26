package inventariolab.angelus.inventariolabs.fragment.inventory;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import inventariolab.angelus.inventariolabs.R;
import inventariolab.angelus.inventariolabs.activity.FaltanteFragment;
import inventariolab.angelus.inventariolabs.activity.MenuPrincipalActivity;
import inventariolab.angelus.inventariolabs.modelo.Laboratorios;
import inventariolab.angelus.inventariolabs.modelo.Software;

/**
 * Created by Angelus on 22/08/2017.
 */

public class SWAdapter extends RecyclerView.Adapter<SWAdapter.ItemSWHolder> {
    private List<Software> list;
    private Context ctx;

    //public AdapterView.OnItemClickListener onItemClickListener;


    public SWAdapter(List<Software> list, Context context) {
        this.list=list;
        this.ctx=context;
    }

    @Override
    public ItemSWHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_faltante,parent,false);
        ItemSWHolder itemSWHolder=new ItemSWHolder(v);
        return itemSWHolder;
    }

    @Override
    public void onBindViewHolder(ItemSWHolder holder, final int position) {
        holder.txtid.setText(String.valueOf(list.get(position).getId()));
        holder.txtdesc.setText(list.get(position).getName());
        holder.btnlab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FaltanteFragment  fragment=new FaltanteFragment();
                Bundle bundle=new Bundle();

                Software sw=list.get(position);
                bundle.putParcelable("id",sw);
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


    public static class ItemSWHolder extends RecyclerView.ViewHolder{
        public ImageButton btnlab;
        public TextView txtdesc;
        public TextView txtid;

        public ItemSWHolder(View itemView) {
            super(itemView);
       //     btnlab=(ImageButton) itemView.findViewById(R.id.btn_item_labs_select);
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
