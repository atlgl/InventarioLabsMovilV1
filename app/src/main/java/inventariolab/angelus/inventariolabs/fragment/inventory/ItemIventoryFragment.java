package inventariolab.angelus.inventariolabs.fragment.inventory;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import inventariolab.angelus.inventariolabs.R;
import inventariolab.angelus.inventariolabs.modelo.Inventory;

public class ItemIventoryFragment extends Fragment {

    private TextView txtid;
    private EditText txtbarcode;
    private EditText txtmodelo;
    private EditText txtmarca;
    private Spinner spinestado;
    private EditText txtdesc;


    private ImageButton barcode;
    private ImageButton fallas;
    private ImageButton software;
    private ImageButton perdidas;

    public ItemIventoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle=getArguments();

        Inventory inv=bundle.getParcelable("inventoryobj");

        View v=inflater.inflate(R.layout.fragment_item_iventory, container, false);
        txtid=(TextView) v.findViewById(R.id.txt_item_inventory_id);
        txtbarcode=(EditText) v.findViewById(R.id.txt_item_inventory_codigobarra);
        txtmodelo=(EditText) v.findViewById(R.id.txt_item_inventory_modelo);
        txtmarca=(EditText) v.findViewById(R.id.txt_item_inventory_marca);
        spinestado=(Spinner) v.findViewById(R.id.spin_item_inventory_estado);

        txtid.setText(String.valueOf(inv.getId()));
        txtbarcode.setText(inv.getBarcode());
        txtmodelo.setText(inv.getComputer().getModelname());
        txtmarca.setText(inv.getComputer().getMark().getName());
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getContext(),R.array.item_inventory_state,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinestado.setAdapter(adapter);

        fallas=(ImageButton) v.findViewById(R.id.btn_item_inventory_fallas);
        software=(ImageButton) v.findViewById(R.id.btn_item_inventory_software);
        perdidas=(ImageButton) v.findViewById(R.id.btn_item_inventory_perdidas);
        barcode=(ImageButton) v.findViewById(R.id.btn_item_inventory_barcode);





        return v;
    }



}
