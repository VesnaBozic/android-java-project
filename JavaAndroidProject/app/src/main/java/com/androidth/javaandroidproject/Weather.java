package com.androidth.javaandroidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather extends AppCompatActivity {


    TextView temperatureLabel;
    ImageView weatherImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        temperatureLabel = findViewById(R.id.tekst);
        weatherImage = findViewById(R.id.weatherImage);
        updateWeather();
    }

    void updateWeather(){
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=novi+sad&appid=8b32af0cef6daf3aa2882463cf64d057&units=metric";

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, apiUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    double temp = response.getJSONObject("main").getDouble("temp");
                    String temperatureFormatted = getString(R.string.temp_format, temp);
                    temperatureLabel.setText(temperatureFormatted);

                    String icon = response.getJSONArray("weather").getJSONObject(0).getString("icon");
                    String imgUrl = String.format("http://openweathermap.org/img/wn/%1s.png", icon);
                    Glide.with(Weather.this).load(imgUrl).into(weatherImage);
                } catch (JSONException e) {
                    Toast errorMsg = Toast.makeText(Weather.this, e.getMessage(), Toast.LENGTH_LONG);
                    errorMsg.show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast errorMsg = Toast.makeText(Weather.this, error.getMessage(), Toast.LENGTH_LONG);
                errorMsg.show();
            }
        });

        queue.add(request);
    }
}