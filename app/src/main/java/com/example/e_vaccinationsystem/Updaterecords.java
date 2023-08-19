package com.example.e_vaccinationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Updaterecords extends AppCompatActivity {

    Button btnupdateadminusers,btnupdateregistereduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updaterecords);

        btnupdateadminusers = (Button) findViewById(R.id.btnupdateadminusers);
        btnupdateregistereduser = (Button) findViewById(R.id.btnupdateregistereduser);

        btnupdateregistereduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),UpdateRegisteredUserData.class);
                startActivity(i);
            }
        });

        btnupdateadminusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),UpdateRegisteredUserData.class);
                startActivity(i);
            }
        });

    }
}