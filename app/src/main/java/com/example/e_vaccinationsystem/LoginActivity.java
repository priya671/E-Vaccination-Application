package com.example.e_vaccinationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button btnlogin;
    DBHelper DB;
    Button btnloginasadmin;
    AdminDBHelper AdminDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        DB = new DBHelper(this);
        btnloginasadmin = (Button) findViewById(R.id.btnadminlogin);
        AdminDB = new AdminDBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);

                        SharedPreferences sp=getSharedPreferences("Login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor Ed=sp.edit();
                        Ed.putString("username",user );
//                        Ed.putString("Psw",Value);
                        Ed.apply();

                    }else
                        Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnloginasadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

//                Boolean Datainsertion = AdminDB.insertadminuser(user,pass);
//                if(Datainsertion==true){
//                    Toast.makeText(LoginActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
//                }
                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = AdminDB.checkadminusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),admin_Layout.class);
                        startActivity(intent);

                    }else
                        Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}