package com.example.hoteltejas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;

public class Payment extends AppCompatActivity {

    CardForm cardForm;
    Button buy;
    AlertDialog.Builder alertBuilder;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        boolean wc;
        boolean mc;

        text = (TextView)findViewById(R.id.amt);
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo=cm.getActiveNetworkInfo();
        if(activeInfo!=null&&activeInfo.isConnected()){
            wc=activeInfo.getType()==cm.TYPE_WIFI;
            mc=activeInfo.getType()==cm.TYPE_MOBILE;
            if(wc){
                Intent intent = getIntent();
                String str = intent.getStringExtra("message");
                text.setText(str);
                cardForm = findViewById(R.id.card_form);
                buy = findViewById(R.id.btnBuy);
                cardForm.cardRequired(true)
                        .expirationRequired(true)
                        .cvvRequired(true)
                        .postalCodeRequired(true)
                        .mobileNumberRequired(true)
                        .mobileNumberExplanation("SMS is required on this number")
                        .setup(Payment.this);
                cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (cardForm.isValid()) {
                            alertBuilder = new AlertDialog.Builder(Payment.this);
                            alertBuilder.setTitle("Confirm before purchase");
                            alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                                    "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                                    "Card CVV: " + cardForm.getCvv() + "\n" +
                                    "Postal code: " + cardForm.getPostalCode() + "\n" +
                                    "Phone number: " + cardForm.getMobileNumber());
                            alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Toast.makeText(Payment.this, "Thank you for purchase", Toast.LENGTH_LONG).show();
                                }
                            });
                            alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            AlertDialog alertDialog = alertBuilder.create();
                            alertDialog.show();

                        } else {
                            Toast.makeText(Payment.this, "Please complete the form", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
            else if(mc){
                Intent intent = getIntent();
                String str = intent.getStringExtra("message");
                text.setText(str);
                cardForm = findViewById(R.id.card_form);
                buy = findViewById(R.id.btnBuy);
                cardForm.cardRequired(true)
                        .expirationRequired(true)
                        .cvvRequired(true)
                        .postalCodeRequired(true)
                        .mobileNumberRequired(true)
                        .mobileNumberExplanation("SMS is required on this number")
                        .setup(Payment.this);
                cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (cardForm.isValid()) {
                            alertBuilder = new AlertDialog.Builder(Payment.this);
                            alertBuilder.setTitle("Confirm before purchase");
                            alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                                    "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                                    "Card CVV: " + cardForm.getCvv() + "\n" +
                                    "Postal code: " + cardForm.getPostalCode() + "\n" +
                                    "Phone number: " + cardForm.getMobileNumber());
                            alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Toast.makeText(Payment.this, "Booking Confirmed!!", Toast.LENGTH_LONG).show();
                                }
                            });
                            alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            AlertDialog alertDialog = alertBuilder.create();
                            alertDialog.show();

                        } else {
                            Toast.makeText(Payment.this, "Please complete the form", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
        else{
            new AlertDialog.Builder(this)
                    .setTitle("Network Connection")
                    .setMessage("Please Check Your Net Connectivity")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}
