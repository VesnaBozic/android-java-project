package com.androidth.javaandroidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class UserProfile extends AppCompatActivity {
    Db DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        EditText name  = (EditText) findViewById(R.id.name);
        EditText surname  = (EditText) findViewById(R.id.surname);
        EditText username  = (EditText) findViewById(R.id.username);
        EditText password  = (EditText) findViewById(R.id.password);
        MaterialButton editbtn = (MaterialButton) findViewById(R.id.editbtn);
        MaterialButton deletebtn = (MaterialButton) findViewById(R.id.deletebtn);
        TextView goBack = (TextView) findViewById(R.id.goBack);
        DB = new Db (this);
        name.setText(getIntent().getStringExtra("name"));
        surname.setText(getIntent().getStringExtra("surname"));
        username.setText(getIntent().getStringExtra("username"));
        password.setText(getIntent().getStringExtra("password"));


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homePage =new Intent(getApplicationContext(),HomePage.class);
                startActivity(homePage);
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String userName = getIntent().getStringExtra("username");
            Boolean checkData = DB.deleteUserData(userName);
            if (checkData == true) {
                Toast.makeText( UserProfile.this, "User deleted", Toast.LENGTH_SHORT).show();
                Intent main_activity =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(main_activity);
            }
            else {
                Toast.makeText( UserProfile.this, "User not deleted", Toast.LENGTH_SHORT).show();
            }
            }
        });

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = getIntent().getStringExtra("username");
                Boolean checkData = DB.updateUserData(username.getText().toString(), name.getText().toString(), surname.getText().toString(), password.getText().toString());
                Toast.makeText( UserProfile.this, "User updated", Toast.LENGTH_SHORT).show();

            }
        });
         }


}