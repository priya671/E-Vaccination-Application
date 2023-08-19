package com.example.e_vaccinationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateRegisteredUserData extends AppCompatActivity {

    EditText upusername1,uppassword1;
    Button btnupdateuser,btnupdateadminuser;
    DBHelper mydbhelper;
    AdminDBHelper admindbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_registered_user_data);

        upusername1 = (EditText) findViewById(R.id.upusername1);
        uppassword1 = (EditText) findViewById(R.id.uppassword1);
        btnupdateuser = (Button) findViewById(R.id.btnupdateuser);
        btnupdateadminuser = (Button) findViewById(R.id.btnupdateadminuser);
        mydbhelper = new DBHelper(this);
        admindbhelper = new AdminDBHelper(this);

        btnupdateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = upusername1.getText().toString();
                String pass = uppassword1.getText().toString();
                boolean isUpdated = mydbhelper.updateUserData(uname,pass);
                if(uname.equals("")||pass.equals("")){
                    Toast.makeText(UpdateRegisteredUserData.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                if(isUpdated==true) {
                    Toast.makeText(UpdateRegisteredUserData.this, "Record Updated Successfully", Toast.LENGTH_SHORT).show();
                }
                    else {
                    Toast.makeText(UpdateRegisteredUserData.this, "Data not updated", Toast.LENGTH_SHORT).show();
                }
                    }
        });

        btnupdateadminuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = upusername1.getText().toString();
                String pass = uppassword1.getText().toString();
                boolean isUpdated = admindbhelper.updateAdminData(uname,pass);
                if(uname.equals("")||pass.equals("")) {
                    Toast.makeText(UpdateRegisteredUserData.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                if(isUpdated==true) {
                    Toast.makeText(UpdateRegisteredUserData.this, "Record Updated Successfully", Toast.LENGTH_SHORT).show();
                }
                    else {
                    Toast.makeText(UpdateRegisteredUserData.this, "Data not updated", Toast.LENGTH_SHORT).show();
                }
                    }
        });

    }
}