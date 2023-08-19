package com.example.e_vaccinationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class deleterecords extends AppCompatActivity {

    EditText edtusername;
    Button btndeleteuserrecord,btndeleteadminrecord;
    DBHelper mydb;
    AdminDBHelper admindb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleterecords);

        edtusername = (EditText) findViewById(R.id.edtusername);
        btndeleteuserrecord = (Button) findViewById(R.id.btndeleteuserrecord);
        btndeleteadminrecord = (Button) findViewById(R.id.btndeleteadminrecord);
        mydb = new DBHelper(this);
        admindb = new AdminDBHelper(this);

        btndeleteuserrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedrows = mydb.deleteUserData(edtusername.getText().toString());
                if(edtusername.equals(""))
                    Toast.makeText(deleterecords.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                if(deletedrows>0)
                    Toast.makeText(deleterecords.this,"Record Deleted Successfully",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(deleterecords.this,"Data not deleted",Toast.LENGTH_SHORT).show();
            }
        });

        btndeleteadminrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedrows = admindb.deleteAdminData(edtusername.getText().toString());
                if(edtusername.equals(""))
                    Toast.makeText(deleterecords.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                if(deletedrows>0)
                    Toast.makeText(deleterecords.this,"Record Deleted Successfully",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(deleterecords.this,"Data not deleted",Toast.LENGTH_SHORT).show();
            }
        });

    }
}