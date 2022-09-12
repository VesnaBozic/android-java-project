package com.androidth.javaandroidproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class UserProfile extends AppCompatActivity {
    Db DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        EditText name  = findViewById(R.id.name);
        EditText surname  = findViewById(R.id.surname);
        EditText username  = findViewById(R.id.username);
        EditText password  = findViewById(R.id.password);
        MaterialButton editBtn = findViewById(R.id.editbtn);
        MaterialButton deleteBtn = findViewById(R.id.deletebtn);
        TextView goBack = findViewById(R.id.goBack);
        DB = new Db (this);
        name.setText(getIntent().getStringExtra("name"));
        surname.setText(getIntent().getStringExtra("surname"));
        username.setText(getIntent().getStringExtra("username"));
        password.setText(getIntent().getStringExtra("password"));


        goBack.setOnClickListener(view -> {
            Intent homePage =new Intent(getApplicationContext(),HomePage.class);
            startActivity(homePage);
        });

        deleteBtn.setOnClickListener(view -> {
        String userName = getIntent().getStringExtra("username");
        Boolean checkData = DB.deleteUserData(userName);
        if (checkData) {
            Toast.makeText( UserProfile.this, "User deleted", Toast.LENGTH_SHORT).show();
            Intent main_activity =new Intent(getApplicationContext(),MainActivity.class);
            startActivity(main_activity);
        }
        else {
            Toast.makeText( UserProfile.this, "User not deleted", Toast.LENGTH_SHORT).show();
        }
        });

        editBtn.setOnClickListener(view -> Toast.makeText( UserProfile.this, "User updated", Toast.LENGTH_SHORT).show());
         }


}