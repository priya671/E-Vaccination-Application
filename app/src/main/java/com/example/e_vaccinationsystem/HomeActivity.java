package com.example.e_vaccinationsystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Button searchbutton;
    EditText pincodeedt;
    RecyclerView centerRV;
    ProgressBar loadingPB;
    List<CenterRVModal> userList;
    List<CenterRVModal> centerList;
    private CenterRVAdapter.RecyclerViewListener listener;
    CenterRVAdapter centerRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        searchbutton = (Button) findViewById(R.id.btnsearch);
        pincodeedt = (EditText) findViewById(R.id.edtpincode);
        centerRV = findViewById(R.id.recyclervw);
        loadingPB = (ProgressBar) findViewById(R.id.ProgressBrL);
        centerList = new ArrayList<CenterRVModal>();

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String pincode = pincodeedt.getText().toString();
                if (pincode.length() != 6) {
                    Toast.makeText(HomeActivity.this, "Please enter a valid Pin Code", Toast.LENGTH_SHORT).show();
                } else {
                    centerList.clear();
                    Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dpd = new DatePickerDialog(HomeActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    loadingPB.setVisibility(v.VISIBLE);

                                    String dateStr = String.valueOf(dayOfMonth) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(year);

                                    getAppointmentDetails(pincode, dateStr);

                                    SharedPreferences sharedPreferences=getSharedPreferences("Sel_date", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor Edt = sharedPreferences.edit();
                                    Edt.putString("date",dateStr );
                                    Edt.apply();

                                }
                            }, year, month, day);
                    dpd.show();
                }

            }
        });
    }

    ;
//}

    private void getAppointmentDetails(String pincode, String date) {

        String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=" + pincode + "&date=" + date;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray centerArray = response.getJSONArray("centers");
                    if(centerArray.length()==0){
                        Toast.makeText(HomeActivity.this,"No Vaccination centers available",Toast.LENGTH_SHORT).show();
                    }
                    for(int i=0; i<centerArray.length();i++){
                        JSONObject centerObj = centerArray.getJSONObject(i);
                        String centerName = centerObj.getString("name");
                        String centerAddress = centerObj.getString("address");
                        String centerFromTime = centerObj.getString("from");
                        String centerToTime = centerObj.getString("to");
                        String fee_type = centerObj.getString("fee_type");
                        JSONObject sessionObj = centerObj.getJSONArray("sessions").getJSONObject(0);
                        int availableCapacity = sessionObj.getInt("available_capacity");
                        int ageLimit = sessionObj.getInt("min_age_limit");
                        String vaccineName = sessionObj.getString("vaccine");

                        CenterRVModal center = new CenterRVModal(centerName,centerAddress,centerFromTime,
                                centerToTime,fee_type,ageLimit,vaccineName,availableCapacity);

                        centerList.add(center);
                    }
                    setOnClickListener();
                    CenterRVAdapter centerRVAdapter = new CenterRVAdapter(centerList,listener);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this,LinearLayoutManager.VERTICAL,false);
                    centerRV.setLayoutManager(layoutManager);
                    centerRV.setAdapter(centerRVAdapter);
                }
                catch(JSONException e){
                    loadingPB.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeActivity.this,"Failed to get the data",Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    private void setOnClickListener() {
        listener = new CenterRVAdapter.RecyclerViewListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(),BookVaccineAppointment.class);
                intent.putExtra("centerName",centerList.get(position).getCenterName());
                intent.putExtra("centerAddress",centerList.get(position).getCenterAddress());
                intent.putExtra("centerFromTime",centerList.get(position).getCenterFromTime());
                intent.putExtra("centerToTime",centerList.get(position).getCenterToTime());
                intent.putExtra("vaccineName",centerList.get(position).getVaccineName());
                intent.putExtra("fee_type",centerList.get(position).getFee_type());
                intent.putExtra("ageLimit",centerList.get(position).getAgeLimit());
                intent.putExtra("availableCapacity",centerList.get(position).getAvailableCapacity());
                startActivity(intent);
            }
        };
    }
}