package com.example.hoteltejas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView ConStatus;
    TextView cstv;
    Button csb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConStatus=findViewById(R.id.ConStatus);
        cstv=findViewById(R.id.cstv);
        csb=findViewById(R.id.csb);
        boolean wc;
        boolean mc;
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo=cm.getActiveNetworkInfo();
        if(activeInfo!=null&&activeInfo.isConnected()){
            wc=activeInfo.getType()==cm.TYPE_WIFI;
            mc=activeInfo.getType()==cm.TYPE_MOBILE;
            if(wc){

                //String nstr="Welcome"+str;
                cstv.setText("Welcome to Ambassador");
                ConStatus.setImageResource(R.drawable.tejas);
                //cstv.setText("Welcome to Hotel Tejas");
            }
            else if(mc){
                ConStatus.setImageResource(R.drawable.tejas);
                cstv.setText("Welcome to Ambassador");

            }
        }
        else{
            /*ConStatus.setImageResource(R.drawable.ic_action_wifi);
            cstv.setText("No Internet Connection");*/
            new AlertDialog.Builder(this)
                    .setTitle("Network Connection")
                    .setMessage("Please Check Your Net Connectivity")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            System.exit(0);
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        csb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                finish();
            }
        });
    }

}
