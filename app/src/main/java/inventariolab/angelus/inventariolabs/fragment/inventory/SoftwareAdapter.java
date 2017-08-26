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
import inventariolab.angelus.inventariolabs.activity.MenuPrincipalActivity;

import inventariolab.angelus.inventariolabs.modelo.Software;

/**
 * Created by cat on 24/08/2017.
 */

public class SoftwareAdapter    extends RecyclerView.Adapter<SoftwareAdapter.ItemSWHolder> {
    private List<Software> list;
    private Context ctx;

    //public AdapterView.OnItemClickListener onItemClickListener;


    public SoftwareAdapter(List<Software> list,Context context) {
        this.list=list;
        this.ctx=context;
    }

    @Override
    public ItemSWHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_software  ,parent,false);
    //    View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.txtid_item_software_select,parent,false);
        ItemSWHolder itemSWHolder =new ItemSWHolder(v);
        return itemSWHolder;
    }

    @Override
    public void onBindViewHolder(ItemSWHolder holder, final int position) {
        holder.txtid.setText(String.valueOf(list.get(position).getId()));
        holder.txtdesc.setText(list.get(position).getName());
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
            btnlab=(ImageButton) itemView.findViewById(R.id.btn_item_software_select);
            txtdesc=(TextView) itemView.findViewById(R.id.txt_item_software_select);
            txtid=(TextView) itemView.findViewById(R.id.txtid_item_software_select);

        }
    }
}