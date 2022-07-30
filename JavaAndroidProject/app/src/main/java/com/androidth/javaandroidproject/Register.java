package com.androidth.javaandroidproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Register extends AppCompatActivity {
    Db DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView name  = (TextView) findViewById(R.id.name);
        TextView surname  = (TextView) findViewById(R.id.surname);
        TextView username  = (TextView) findViewById(R.id.username);
        TextView password  = (TextView) findViewById(R.id.password);
        TextView registerbtn = (TextView) findViewById(R.id.registerbtn);
        TextView login = (TextView) findViewById(R.id.login);
        DB = new Db (this);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Cursor res = DB.getData();
                if (res.getCount() == 0) {
                    Toast.makeText(Register.this, "No data", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()) {
                        buffer.append("Username" + res.getString(0)+"\n" );
                        buffer.append("Name" + res.getString(1)+"\n" );
                        buffer.append("Surname" + res.getString(2)+"\n" );
                        buffer.append("Password" + res.getString(3)+"\n" );
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setCancelable(true);
                    builder.setTitle("User Details");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }

                // starting background task to update product
//                Intent main_activity =new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(main_activity);
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
//                    Intent main_activity =new Intent(getApplicationContext(),MainActivity.class);
//                    startActivity(main_activity);
                }
                else {
                    Toast.makeText(Register.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}