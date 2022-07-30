package com.androidth.javaandroidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class Db extends SQLiteOpenHelper {
    public Db( Context context) {
        super(context, "UserData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserDetails (username TEXT primary key, name TEXTs, surname TEXT, password TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
    DB.execSQL("drop Table if exists UserDetails");
    }

    public Boolean insertUserData(String username, String name, String surname, String password ) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("name", name);
        contentValues.put("surname", surname);
        contentValues.put("password", password);

        long result = DB.insert("UserDetails", null, contentValues);
        if (result == 1) {
            return false;
        }
        else {
           return  true;
        }
    }

    public Boolean updateUserData(String username, String name, String surname, String password ) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("surname", surname);
        contentValues.put("password", password);
        Cursor cursor = DB.rawQuery("Select * from UserDetails where surname = ?", new String[] {surname} );
        if (cursor.getCount() > 0) {

        long result = DB.update("UserDetails", contentValues, "surname=?", new String[] {surname});
        if (result == 1) {
            return false;
        }
        else {
            return  true;
        }} else {
            return true;
        }
    }


    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserDetails", null );
        return cursor;
    }
}
