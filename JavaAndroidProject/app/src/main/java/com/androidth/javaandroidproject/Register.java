package com.androidth.javaandroidproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        EditText name  = findViewById(R.id.name);
        EditText surname  = findViewById(R.id.surname);
        EditText username  = findViewById(R.id.username);
        EditText password  = findViewById(R.id.password);
        MaterialButton registerBtn = findViewById(R.id.registerbtn);
        TextView login =  findViewById(R.id.login);
        DB = new Db (this);

        login.setOnClickListener(v -> {
            // starting background task to update product
           Intent main_activity =new Intent(getApplicationContext(),MainActivity.class);
           startActivity(main_activity);
        });

        registerBtn.setOnClickListener(v -> {
            String usernameTxt = username.getText().toString();
            String nameTxt = name.getText().toString();
            String surnameTxt = surname.getText().toString();
            String passwordTxt = password.getText().toString();

            Boolean checkInsertData = DB.insertUserData(usernameTxt, nameTxt, surnameTxt, passwordTxt);
            if (checkInsertData) {
                Toast.makeText(Register.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                Intent main_activity =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(main_activity);
            }
            else {
                Toast.makeText(Register.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}