package com.example.hoteltejas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Confirmed extends AppCompatActivity {
    TextView t2,t3,t4,t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id=10001;
        setContentView(R.layout.activity_confirmed);
        t1=(TextView)findViewById(R.id.id);
        t2 = (TextView)findViewById(R.id.name_b);
        t3 = (TextView)findViewById(R.id.mail);
        t4 = (TextView)findViewById(R.id.phone);
        Intent intent = getIntent();
        String str_name = intent.getStringExtra("name_message");
        String str_mail = intent.getStringExtra("mail_message");
        String str_phone = intent.getStringExtra("phone_message");
        t2.setText(str_name);
        t3.setText(str_mail);
        t4.setText(str_phone);
        t1.setText(String.valueOf(id));
    }
}
