package com.example.e_vaccinationsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class viewDatabaseContent extends AppCompatActivity {

    Button btnviewadminuserdata,btnviewregisteruser,btnviewappointmentdata;
    AdminDBHelper adminDBHelper;
    DBHelper mydbhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_database_content);

        btnviewadminuserdata = (Button) findViewById(R.id.btnviewadminuserdata);
        btnviewregisteruser = (Button) findViewById(R.id.btnviewregisteruser);
        btnviewappointmentdata = (Button) findViewById(R.id.btnviewappointmentdata);
        adminDBHelper = new AdminDBHelper(this);
        mydbhelper = new DBHelper(this);

        btnviewadminuserdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = adminDBHelper.getAlldata();
                if(res.getCount() == 0){
                    //show message
                    showMessage("Error","Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Admin Users" + "\n");
                    buffer.append("Admin Name: " + res.getString(0) + "\n");
                    buffer.append("Admin Password: " + res.getString(1) + "\n\n");

                    //show all data
                    showMessage("Admin Data",buffer.toString());
                }
            }
        });

        btnviewregisteruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res2 = mydbhelper.getAllUserdata();
                if(res2.getCount() == 0){
                    showMessage("Error","Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res2.moveToNext()){
                    buffer.append("Registered Users" + "\n");
                    buffer.append("Name: " + res2.getString(0) + "\n");
                    buffer.append("Password: " + res2.getString(1) + "\n\n");

                    showMessage("User Data",buffer.toString());
                }
            }
        });

        btnviewappointmentdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res3 = mydbhelper.getAllAppointmentdata();
                if(res3.getCount() == 0){
                    showMessage("Error","Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res3.moveToNext()){
                    buffer.append("Appointment Data" + "\n");
                    buffer.append("Name: " + res3.getString(0) + "\n");
                    buffer.append("Date: " + res3.getString(1) + "\n");
                    buffer.append("Centre Name: " + res3.getString(2) + "\n");
                    buffer.append("Centre Address: " + res3.getString(3) + "\n");
                    buffer.append("Time: " + res3.getString(4) + "\n");
                    buffer.append("Vaccine Type: " + res3.getString(5) + "\n");
                    buffer.append("Fees: " + res3.getString(6) + "\n");
                    buffer.append("Age Limit: " + res3.getString(7) + "\n");
                    buffer.append("Availability: " + res3.getString(8) + "\n\n");

                    showMessage("Appointment Data",buffer.toString());
                }
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