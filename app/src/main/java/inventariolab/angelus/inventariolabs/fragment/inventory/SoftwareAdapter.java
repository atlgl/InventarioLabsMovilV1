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

import inventariolab.angelus.inventariolabs.modelo.Software;

/**
 * Created by cat on 24/08/2017.
 */

public class SoftwareAdapter   extends ArrayAdapter<Software>    {

        private Context ctx;
        private List<Software> softList;


        public SoftwareAdapter(@NonNull Context context,List<Software> list) {
            super(context, R.layout.activity_faltante,list);
            this.ctx=context;
            this.softList=list;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View v=LayoutInflater.from(ctx).inflate(R.layout.activity_faltante,parent,false);


            return v;
        }


}
