package com.androidth.javaandroidproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Register extends AppCompatActivity {
    Db DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText name  = (EditText) findViewById(R.id.name);
        EditText surname  = (EditText) findViewById(R.id.surname);
        EditText username  = (EditText) findViewById(R.id.username);
        EditText password  = (EditText) findViewById(R.id.password);
        MaterialButton registerbtn = (MaterialButton) findViewById(R.id.registerbtn);
        TextView login = (TextView) findViewById(R.id.login);
        DB = new Db (this);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // starting background task to update product
               Intent main_activity =new Intent(getApplicationContext(),MainActivity.class);
               startActivity(main_activity);
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String usernameTxt = username.getText().toString();
                String nameTxt = name.getText().toString();
                String surnameTxt = surname.getText().toString();
                String passwordTxt = password.getText().toString();

                Boolean checkInsertData = DB.insertUserData(usernameTxt, nameTxt, surnameTxt, passwordTxt);
                if (checkInsertData == true) {
                    Toast.makeText(Register.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    Intent main_activity =new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(main_activity);
                }
                else {
                    Toast.makeText(Register.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}