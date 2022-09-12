package com.androidth.javaandroidproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



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
        return result != 1;
    }

    public Boolean updateUserData(String username, String name, String surname, String password ) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("surname", surname);
        contentValues.put("password", password);
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select * from UserDetails where username = ?", new String[] {username} );
        if (cursor.getCount() > 0) {

        long result = DB.update("UserDetails", contentValues, "username=?", new String[] {username});
            return result != 1;
        } else {
            return true;
        }
    }

    public Boolean deleteUserData(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();

        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select * from UserDetails where username = ?", new String[] {username} );
        if (cursor.getCount() > 0) {

            long result = DB.delete("UserDetails","username=?", new String[] {username});
            return result != -1;
        } else {
            return true;
        }
    }


    public Cursor getUserData(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from UserDetails where username = ?", new String[] {username} );
    }
}
