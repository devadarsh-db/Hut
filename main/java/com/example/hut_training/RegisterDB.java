package com.example.hut_training;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class RegisterDB extends SQLiteOpenHelper {

    public static final String DBNAME = "Register.db";
    public RegisterDB(Context context) {
        super(context,"Register.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqldb) {
        sqldb.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqldb, int i,int i1) {
        sqldb.execSQL("drop Table if exists users");
        onCreate(sqldb);
    }

    public boolean insertdata(String username, String password) {
        SQLiteDatabase sqldb = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("username",username);
        cv.put("password",password);
        long result =sqldb.insert("users", null, cv);
        //if not inserted successfully, then value of result will be -1
        if(result==-1){
            return false;
        }
        else{
            return true;
        }

    }
    public Cursor getAllData() {
        SQLiteDatabase sqldb = this.getWritableDatabase();
        Cursor result = sqldb.rawQuery("SELECT * FROM users", null);
        return result;

    }


}
