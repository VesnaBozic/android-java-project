package com.androidth.javaandroidproject;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

public class Weather extends AppCompatActivity {


    TextView temperatureLabel;
    TextView weatherImage;
    Typeface weatherFont;
    EditText location;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        temperatureLabel = findViewById(R.id.tekst);
        weatherImage = findViewById(R.id.weatherIcon);
        weatherFont = Typeface.createFromAsset(getAssets(), "weathericons-regular-webfont.ttf");
        weatherImage.setTypeface(weatherFont);
        location = findViewById(R.id.location);
        searchBtn = findViewById(R.id.searchBtn);


        searchBtn.setOnClickListener(view -> {
            String getLocation = location.getText().toString();
            updateWeather(getLocation);
        });

    }

    String getIcon(int code) {
        int hundreds = code / 100 ;

        if (code == 800) {
            return "\uf00d";
        }
        else if (code == 781) {
            return "\uf056";
        }
        else if (code == 762) {
            return "\uf0c8";
        }

        switch (hundreds)  {
            case 2:
                return "\uf005";
            case 3 :
            case 5 :
                return "\uf00b";
            case 6 :
                return "\uf00a";
            case 7 :
                return "\uf003";
            case 8 :
                return "\uf002";
            default:
                return "\uf041";
        }
    }

    void updateWeather(String location){
        String apiUrl = String.format("https://api.openweathermap.org/data/2.5/weather?q=%1$s&appid=8b32af0cef6daf3aa2882463cf64d057&units=metric", location);

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, apiUrl, null, response -> {
            try {
                double temp = response.getJSONObject("main").getDouble("temp");
                String temperatureFormatted = getString(R.string.temp_format, temp);
                temperatureLabel.setText(temperatureFormatted);
                int weatherIconCode = response.getInt("cod");
                String iconWeather = getIcon(weatherIconCode);
                weatherImage.setText(iconWeather);

            } catch (JSONException e) {
                Toast errorMsg = Toast.makeText(Weather.this, e.getMessage(), Toast.LENGTH_LONG);
                errorMsg.show();
            }

        }, error -> {
            Toast errorMsg = Toast.makeText(Weather.this, error.getMessage(), Toast.LENGTH_LONG);
            errorMsg.show();
        });

        queue.add(request);
    }
}