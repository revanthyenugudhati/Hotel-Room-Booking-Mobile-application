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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class Main2Activity extends AppCompatActivity {
    private Spinner sp1,sp2,sp3,sp4;
    private ImageView i1,i2;
    Button b;
    RadioGroup radioGroup;
    private RadioButton radioButton;
    CheckBox c1,c2,c3,c4;
    EditText ed1,ed2,ed3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b=(Button)findViewById(R.id.book);
        i1=(ImageView)findViewById(R.id.suite_desc);
        i2=(ImageView)findViewById(R.id.additional);
        radioGroup = (RadioGroup) findViewById(R.id.rooms);
        c1=(CheckBox)findViewById(R.id.jacuzzi);
        c2=(CheckBox)findViewById(R.id.swimming);
        c3=(CheckBox)findViewById(R.id.meals);
        c4=(CheckBox)findViewById(R.id.addbed);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, RoomsInfo.class));
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, AdditionalAssets.class));
            }
        });
        sp1 = (Spinner) findViewById(R.id.CIDate);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ci_dates, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sp1.setAdapter(adapter);
        /*sp2 = (Spinner) findViewById(R.id.CIMonth);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.ci_months, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sp2.setAdapter(adapter1);*/
        sp3 = (Spinner) findViewById(R.id.CODate);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.ci_dates, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sp3.setAdapter(adapter3);
       /* sp4 = (Spinner) findViewById(R.id.COMonth);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.ci_months, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sp4.setAdapter(adapter4);*/
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               cal();
            }
        });
    }

    private void cal() {
        int amt=0,days=0;
            if((sp1.getSelectedItem().toString()).equals("Select Date")||(sp3.getSelectedItem().toString()).equals("Select Date")){
                Toast.makeText(this," Please Select Date", Toast.LENGTH_LONG).show();
            }
        else{
            if(Integer.parseInt(sp1.getSelectedItem().toString())<=Integer.parseInt(sp3.getSelectedItem().toString())){
                days=Integer.parseInt(sp3.getSelectedItem().toString())-Integer.parseInt(sp1.getSelectedItem().toString());
            }
            else{
                Toast.makeText(this," Please Check your Checkout date", Toast.LENGTH_LONG).show();
            }
        }
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton.getText().equals("Standard Suite")){
            amt=150;
        }
        else if(radioButton.getText().equals("Executive Suite")){
            amt=300;
        }
        else if(radioButton.getText().equals("Presidential Suite")){
            amt=500;
        }
        if(c1.isChecked()){
            amt=amt+10;
        }
        else if(c2.isChecked()){
            amt+=20;
        }
        else if(c3.isChecked()){
            amt+=30;
        }
        else if(c4.isChecked()){
            amt+=50;
        }

        String total=String.valueOf(days*amt);
       // Toast.makeText(Main2Activity.this,total, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), Payment.class);
        intent.putExtra("message", total);


        startActivity(intent);
    }

}
