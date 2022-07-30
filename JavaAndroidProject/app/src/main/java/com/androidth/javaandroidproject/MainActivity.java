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
                    Toast.makeText(MainActivity.this, "No data", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()) {
                        buffer.append("Username " + res.getString(0)+"\n" );
                        buffer.append("Name " + res.getString(1)+"\n" );
                        buffer.append("Surname " + res.getString(2)+"\n" );
                        buffer.append("Password " + res.getString(3)+"\n" );
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("User Details");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }




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