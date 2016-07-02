package com.example.moneyfrog.logindemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by moneyfrog on 25/6/16.
 */
public class Splash extends AppCompatActivity {
    Intent intent;
    final static String prefs = "prefer";
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashh);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {


        sharedPreferences = getSharedPreferences(prefs, MODE_PRIVATE);
        String storedUsername = (sharedPreferences.getString("username", ""));
            String storedPassword = (sharedPreferences.getString("password", ""));
            if (!storedUsername .equalsIgnoreCase("") || !storedPassword .equalsIgnoreCase("")) {
                intent  = new Intent(Splash.this,Welcome.class);
                startActivity(intent);
                finish();

            }else {
                intent  = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
            }

                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);


        }

    }



