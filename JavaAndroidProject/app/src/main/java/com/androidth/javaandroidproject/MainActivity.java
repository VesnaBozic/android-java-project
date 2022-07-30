package com.androidth.javaandroidproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.opengl.GLDebugHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

        TextView username  = (TextView) findViewById(R.id.username);
        TextView password  = (TextView) findViewById(R.id.password);
        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        TextView register = (TextView) findViewById(R.id.register);
        DB = new Db (this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = DB.getUserData(username.getText().toString());
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Wrong username or password !", Toast.LENGTH_SHORT).show();
                    return;
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

                    }
                    if (pass.equals(password.getText().toString())) {
                        startActivity(hp);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Wrong username or password !", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // starting background task to update product
                Intent reg =new Intent(getApplicationContext(),Register.class);
                startActivity(reg);
            }
        });


    }


}