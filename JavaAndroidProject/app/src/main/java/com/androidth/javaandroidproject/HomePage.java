 package com.androidth.javaandroidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

 public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        TextView name  = (TextView) findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("name"));
        TextView logout = (TextView) findViewById(R.id.logout);
        MaterialButton profile = (MaterialButton) findViewById(R.id.profile);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main_activity =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(main_activity);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main_activity =new Intent(getApplicationContext(),UserProfile.class);
                startActivity(main_activity);
            }
        });
    }
}