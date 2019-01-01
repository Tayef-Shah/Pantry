package com.pocketpantry.pantry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class MainActivity extends AppCompatActivity {

    private TextView statusMessage;
    private TextView itemName;
    private BarcodeDetector detector;
    private Barcode barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusMessage = findViewById(R.id.status_message);
        itemName = findViewById(R.id.item_name);

        //Sets up a barcode detector, builds it?, and sets barcode formats to all 1D formats
        detector = new BarcodeDetector.Builder(getApplicationContext()).build();
        BarcodeDetector.Builder.setBarcodeFormats(Barcode.EAN_13 | Barcode.EAN_8 | Barcode.UPC_A | Barcode.UPC_E | Barcode.CODE_39 | Barcode.CODE_93 | Barcode.CODE_128 | Barcode.ITF | Barcode.CODABAR);

        if(!detector.isOperational()){ //Check for dysfunctional barcode detector
            statusMessage.setText(String.format(getString(R.string.barcode_error),
                    CommonStatusCodes.getStatusCodeString(resultCode)));
        }
    }
}
