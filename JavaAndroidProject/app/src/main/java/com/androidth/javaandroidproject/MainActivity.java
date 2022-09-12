package com.androidth.javaandroidproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    Db DB;
    String userName;
    String name;
    String surname;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username  = findViewById(R.id.username);
        EditText password  = findViewById(R.id.password);
        MaterialButton loginBtn = findViewById(R.id.loginbtn);
        TextView register = findViewById(R.id.register);
        DB = new Db (this);

        loginBtn.setOnClickListener(view -> {

            Cursor res = DB.getUserData(username.getText().toString());
            if (res.getCount() == 0) {
                Toast.makeText(MainActivity.this, "Wrong username or password !", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent hp =new Intent(getApplicationContext(),HomePage.class);

                while(res.moveToNext()) {
                    userName = res.getString(0);
                    name = res.getString(1);
                    surname = res.getString(2);
                    pass = res.getString(3);
                    hp.putExtra("name", name);
                    hp.putExtra("username", userName);
                    hp.putExtra("surname", surname);
                    hp.putExtra("password", pass);

                }
                if (pass.equals(password.getText().toString())) {
                    startActivity(hp);
                }
                else {
                    Toast.makeText(MainActivity.this, "Wrong username or password !", Toast.LENGTH_SHORT).show();
                }

            }

        });
        register.setOnClickListener(v -> {
            // starting background task to update product
            Intent reg =new Intent(getApplicationContext(),Register.class);
            startActivity(reg);
        });


    }


}