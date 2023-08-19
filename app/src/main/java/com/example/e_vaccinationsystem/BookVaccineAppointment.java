package com.example.e_vaccinationsystem;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.StaticLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BookVaccineAppointment extends AppCompatActivity {

    TextView nameTxt;
    TextView date;
    TextView centre_name;
    TextView centre_address;
    TextView From_time;
    TextView vaccine_name;
    TextView fees;
    TextView age_limit;
    TextView available;
    Button BookAppointment;
    private static final int PERMISSION_REQUEST_CODE = 200;
    int pagewidth = 792;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_vaccine_appointment);

        nameTxt = findViewById(R.id.txtusername);
        date = findViewById(R.id.txtdate);
        centre_name = findViewById(R.id.centre_name);
        centre_address = findViewById(R.id.txtcentre_address);
        From_time = findViewById(R.id.txtFrom_time);
        vaccine_name = findViewById(R.id.txtvaccine_name);
        fees = findViewById(R.id.txtfees);
        age_limit = findViewById(R.id.txtage);
        available = findViewById(R.id.txtavailable);
        BookAppointment = findViewById(R.id.btnbook);
        DB = new DBHelper(this);

        String scentre_name = "Centre name not set";
        String scentre_address = "Centre Address not set";
        String sfrom_time = "From time not set";
        String sto_time = "To time not set";
        String svaccine_name = "Vaccine name not set";
        String sfees = "Fees not set";
        Integer sage_limit = 0;
        Integer savailable = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            scentre_name = extras.getString("centerName");
            scentre_address = extras.getString("centerAddress");
            sfrom_time = extras.getString("centerFromTime");
            sto_time = extras.getString("centerToTime");
            svaccine_name = extras.getString("vaccineName");
            sfees = extras.getString("fee_type");
            sage_limit = extras.getInt("ageLimit");
            savailable = extras.getInt("availableCapacity");
        }

        SharedPreferences sp1 = this.getSharedPreferences("Login", Context.MODE_PRIVATE);
        String user = sp1.getString("username", "");

        SharedPreferences sp2 = this.getSharedPreferences("Sel_date", Context.MODE_PRIVATE);
        String sdate = sp2.getString("date", "");

        nameTxt.setText("Username: " + user);
        date.setText("Appointment Date: " + sdate);
        centre_name.setText("Centre Name: " + scentre_name);
        centre_address.setText("Centre Address: " + scentre_address);
        From_time.setText("From " + sfrom_time + "to " + sto_time);
        //To_time.setText(sto_time);
        vaccine_name.setText("Vaccine Type: " + svaccine_name);
        fees.setText("Fees: " + sfees);
        age_limit.setText("Age Limit: " + sage_limit.toString());
        available.setText("Availability: " + savailable.toString());

        if (checkPermission()) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }

        String finalScentre_name = scentre_name;
        String finalScentre_address = scentre_address;
        String finalSfrom_time = sfrom_time;
        String finalSvaccine_name = svaccine_name;
        String finalSfees = sfees;
        String finalSage_limit = sage_limit.toString();
        String finalSavailable = savailable.toString();
        BookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                  if(Integer.parseInt(String.valueOf(available))==0){
//                      Toast.makeText(BookVaccineAppointment.this,"There are no vaccine slots available. Please try again later",Toast.LENGTH_LONG).show();
//                  }
                createPDF();
                Boolean insertdata = DB.insertvaccinedata(user,sdate, finalScentre_name, finalScentre_address, finalSfrom_time, finalSvaccine_name, finalSfees, finalSage_limit, finalSavailable);
                if(insertdata==true){
                    //Toast.makeText(BookVaccineAppointment.this,"Inserted data successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private void createPDF() {
        PdfDocument pdfdoc = new PdfDocument();
        Paint mypaint = new Paint();
        Paint titlepaint = new Paint();

        PdfDocument.PageInfo.Builder mypageinfo1 = new PdfDocument.PageInfo.Builder(1200,2010,1);
        PdfDocument.Page mypage1 = pdfdoc.startPage(mypageinfo1.create());
        Canvas mycanvas = mypage1.getCanvas();

        titlepaint.setTextAlign(Paint.Align.CENTER);
        titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        titlepaint.setTextSize(70);
        mycanvas.drawText("Appointment Details",pagewidth/2,270,titlepaint);

        mypaint.setColor(Color.rgb(0,113,188));
        mypaint.setTextSize(30f);
        mypaint.setTextAlign(Paint.Align.RIGHT);
        mycanvas.drawText("For any queries please contact the centre or",1160,40,mypaint);
        mycanvas.drawText("contact us at: e-vaccinationapp@gmail.com",1160,80,mypaint);

        mypaint.setTextSize(30);
        mypaint.setTextAlign(Paint.Align.LEFT);
        mycanvas.drawText("Your vaccination appointment is successful.",70,350,mypaint);

        mypaint.setTextAlign(Paint.Align.LEFT);

        mycanvas.drawText(nameTxt.getText().toString(),70,400,mypaint);

        mycanvas.drawText(centre_name.getText().toString(),70,500,mypaint);

        mycanvas.drawText(date.getText().toString(),70,600,mypaint);

        mycanvas.drawText(From_time.getText().toString(),70,700,mypaint);

        mycanvas.drawText(fees.getText().toString(),70,800,mypaint);

        mycanvas.drawText(age_limit.getText().toString(),70,900,mypaint);

        mycanvas.drawText(vaccine_name.getText().toString(),70,1000,mypaint);

        mycanvas.drawText("Please carry any Photo ID Proof to the centre",70,1100,mypaint);
        mycanvas.drawText("If you have any comorbidities, please carry a medical certificate with you",70,1200,mypaint);
        mycanvas.drawText("for vaccine appointment",70,1250,mypaint);
        mycanvas.drawText("For more information please call 123",70,1350,mypaint);

        pdfdoc.finishPage(mypage1);

        File file = new File(Environment.getExternalStorageDirectory(), "Appointment_Details.pdf");

        try {
            pdfdoc.writeTo(new FileOutputStream(file));

            Toast.makeText(BookVaccineAppointment.this, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfdoc.close();


    }


    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(BookVaccineAppointment.this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }

    }
}