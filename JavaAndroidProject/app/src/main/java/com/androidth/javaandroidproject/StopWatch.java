package com.androidth.javaandroidproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

public class StopWatch extends AppCompatActivity {

    private Chronometer chronometer;
    private boolean running;
    private long stopTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        Button startBtn = findViewById(R.id.startBtn);
        Button stopBtn = findViewById(R.id.stopBtn);
        Button resetBtn = findViewById(R.id.resetBtn);
        Button saveBtn = findViewById(R.id.saveBtn);
        Button goBackBtn = findViewById(R.id.returnBtn);
        chronometer = findViewById(R.id.chronometer);


        startBtn.setOnClickListener(view -> {
            if (!running) {
                chronometer.setBase(SystemClock.elapsedRealtime() - stopTimer);
                chronometer.start();
                running = true;
            }
        });

        stopBtn.setOnClickListener(view -> {
            if (running) {
                chronometer.stop();
                stopTimer = SystemClock.elapsedRealtime() - chronometer.getBase();
                running = false;
            }
        });

        resetBtn.setOnClickListener(view -> {
            chronometer.setBase(SystemClock.elapsedRealtime());
            stopTimer  = 0;
        });

        saveBtn.setOnClickListener(view -> {

        });

        goBackBtn.setOnClickListener(view -> {
            Intent homePage =new Intent(getApplicationContext(),HomePage.class);
            startActivity(homePage);
        });
    }


}