package com.example.e_vaccinationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registernewadminuser extends AppCompatActivity {

    EditText adminusername,adminpassword,adminrepassword;
    Button registernewadmin;
    AdminDBHelper adminDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registernewadminuser);

        adminusername = (EditText) findViewById(R.id.adminusername);
        adminpassword = (EditText) findViewById(R.id.adminpassword);
        adminrepassword = (EditText) findViewById(R.id.adminrepassword);
        registernewadmin = (Button) findViewById(R.id.btnadminregister);
        adminDBHelper = new AdminDBHelper(this);

        registernewadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = adminusername.getText().toString();
                String pass = adminpassword.getText().toString();
                String repass = adminrepassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(registernewadminuser.this,"Please fill all the empty fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = adminDBHelper.checkadminusername(user);
                        if(checkuser==false){
                            Boolean insert = adminDBHelper.insertadminuser(user,pass);
                            if(insert==true){
                                Toast.makeText(registernewadminuser.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),admin_Layout.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(registernewadminuser.this,"Registration failed",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(registernewadminuser.this,"User already exists! Please Login",Toast.LENGTH_SHORT).show();
                        }
                    }else
                        Toast.makeText(registernewadminuser.this, "Password does not match",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}