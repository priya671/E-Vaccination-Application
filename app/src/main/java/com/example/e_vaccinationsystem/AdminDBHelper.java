package com.example.e_vaccinationsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminDBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Admin Data";

    public AdminDBHelper(Context context) {
        super(context, "Admin Data", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table adminusers(username TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists adminusers");
    }

    public Boolean insertadminuser(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long res = db.insert("adminusers",null,contentValues);
        if (res==-1)
            return false;
        else
            return true;
    }

    public Boolean checkadminusername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from adminusers where username=?",new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkadminusernamepassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from adminusers where username=? and password=?",new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor getAlldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from adminusers",null);
        return res;
    }

    public boolean updateAdminData(String username,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        MyDB.update("adminusers",contentValues,"username = ?",new String[] {username});
        return true;
    }

    public Integer deleteAdminData(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        return MyDB.delete("adminusers","username=?",new String[] {username});
    }
}
