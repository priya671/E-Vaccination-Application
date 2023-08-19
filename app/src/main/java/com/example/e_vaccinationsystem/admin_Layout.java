package com.example.e_vaccinationsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class admin_Layout extends AppCompatActivity {

    EditText username,password,repassword;
    Button newAdminUser, viewRecords, updateRecords, deleteRecords;
    AdminDBHelper adminDBHelper;
    DBHelper mydbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_layout);

        newAdminUser = (Button) findViewById(R.id.btnregisteradmin);
        viewRecords = (Button) findViewById(R.id.btnviewallrecords);
        updateRecords = (Button) findViewById(R.id.btnupdaterecords);
        deleteRecords = (Button) findViewById(R.id.btndeleterecords);
        adminDBHelper = new AdminDBHelper(this);
        mydbhelper = new DBHelper(this);


        newAdminUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),registernewadminuser.class);
                startActivity(i);
            }
        });
        viewRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),viewDatabaseContent.class);
                startActivity(i);
            }
        });

        updateRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Updaterecords.class);
                startActivity(i);
            }
        });

        deleteRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),deleterecords.class);
                startActivity(i);
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}