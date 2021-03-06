package inventariolab.angelus.inventariolabs.fragment.scanner;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import inventariolab.angelus.inventariolabs.R;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class SimpleScannerFragment extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);


        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(final com.google.zxing.Result rawResult) {
        // Do something with the result here

        Log.v("Escaner", rawResult.getText()); // Prints scan results
        Log.v("Escaner", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        AlertDialog.Builder alerta=new AlertDialog.Builder(this);
        alerta.setTitle("Codigo Leido");
        alerta.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mScannerView.resumeCameraPreview(SimpleScannerFragment.this);
                mScannerView.stopCamera();
                Intent i=new Intent();
                i.putExtra("CodeFormat",rawResult.getBarcodeFormat().toString());
                i.putExtra("Code",rawResult.getText().toString());
                setResult(1,i);
                finish();
            }
        });
        alerta.setMessage("---"+rawResult.getText()+"--"+rawResult.getBarcodeFormat().toString()+"--");
        mScannerView.resumeCameraPreview(this);
        alerta.show();

        //Toast.makeText(getBaseContext(),"---"+rawResult.getText()+"--"+rawResult.getBarcodeFormat().toString()+"--",Toast.LENGTH_SHORT).show();


        // If you would like to resume scanning, call this method below:

        //setResult(1);
        //finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==1){
            mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
            setContentView(mScannerView);                // Set the scanner view as the content view
        }
    }
}


