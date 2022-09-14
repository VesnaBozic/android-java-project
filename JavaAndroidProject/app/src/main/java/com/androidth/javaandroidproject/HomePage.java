 package com.androidth.javaandroidproject;

 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.TextView;

 import androidx.appcompat.app.AppCompatActivity;

 import com.google.android.material.button.MaterialButton;

 public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        TextView name  = findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("name"));
        TextView logout = findViewById(R.id.logout);
        MaterialButton profile = findViewById(R.id.profile);
        MaterialButton weather_btn = findViewById(R.id.weather);
        MaterialButton stopWatchBtn = findViewById(R.id.stopwatch);

        logout.setOnClickListener(view -> {
            Intent main_activity =new Intent(getApplicationContext(),MainActivity.class);
            startActivity(main_activity);
        });

        profile.setOnClickListener(view -> {
            Intent userProfile =new Intent(getApplicationContext(),UserProfile.class);
            userProfile.putExtra("name", getIntent().getStringExtra("name"));
            userProfile.putExtra("username",getIntent().getStringExtra("username"));
            userProfile.putExtra("surname",getIntent().getStringExtra("surname"));
            userProfile.putExtra("password",getIntent().getStringExtra("password"));
            startActivity(userProfile);
        });



        weather_btn.setOnClickListener(view -> {
            Intent weather = new Intent(getApplicationContext(), Weather.class);
            weather.putExtra("name", getIntent().getStringExtra("name"));
            weather.putExtra("username",getIntent().getStringExtra("username"));
            weather.putExtra("surname",getIntent().getStringExtra("surname"));
            weather.putExtra("password",getIntent().getStringExtra("password"));
            startActivity(weather);
        });

        stopWatchBtn.setOnClickListener(view -> {
            Intent watch = new Intent(getApplicationContext(), StopWatch.class);
            watch.putExtra("name", getIntent().getStringExtra("name"));
            watch.putExtra("username",getIntent().getStringExtra("username"));
            watch.putExtra("surname",getIntent().getStringExtra("surname"));
            watch.putExtra("password",getIntent().getStringExtra("password"));
            startActivity(watch);
        });
    }


}