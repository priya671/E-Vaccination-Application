package com.example.e_vaccinationsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Vaccine Data";

    public DBHelper(Context context) {
        super(context, "Vaccine Data", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table users (username TEXT primary key, password TEXT)");
        MyDB.execSQL("create table Appointmentdata (username TEXT primary key,date TEXT,centre_name TEXT,Centre_add TEXT,time TEXT,vaccine_name TEXT,fees TEXT,age_limit TEXT,availability TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop table if exists users");
        MyDB.execSQL("drop table if exists Appointmentdata");
    }


    public Boolean insertvaccinedata(String username, String date, String centre_name, String centre_address, String from_time, String vaccine_name, String fees, String age_limit, String availability){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("date", date);
        contentValues.put("centre_name", centre_name);
        contentValues.put("Centre_add",centre_address);
        contentValues.put("time", from_time);
        //contentValues.put("To_time",to_time);
        contentValues.put("vaccine_name", vaccine_name);
        contentValues.put("fees", fees);
        contentValues.put("age_limit", age_limit);
        contentValues.put("availability", availability);
        long result = db.insert("Appointmentdata",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean insertData(String username,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }


    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username=?",new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor getAllUserdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from users",null);
        return res;
    }


    public Cursor getAllAppointmentdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Appointmentdata",null);
        return res;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username=? and password=?",new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean updateUserData(String username,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        MyDB.update("users",contentValues,"username = ?",new String[] {username});
        return true;
    }

    public Integer deleteUserData(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        return MyDB.delete("users","username=?",new String[] {username});
    }
}
