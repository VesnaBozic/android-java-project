package com.androidth.javaandroidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username  = (TextView) findViewById(R.id.username);
        TextView password  = (TextView) findViewById(R.id.password);
        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        TextView register = (TextView) findViewById(R.id.register);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                     //  correct login
                    Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL !", Toast.LENGTH_SHORT).show();
                }
                    //   incorrect login
                    else {
                    Toast.makeText(MainActivity.this, "LOGIN FAILED! TRY AGAIN !", Toast.LENGTH_SHORT).show();
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