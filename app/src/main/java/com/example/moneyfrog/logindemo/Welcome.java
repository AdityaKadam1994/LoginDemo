package com.example.moneyfrog.logindemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.StandaloneActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    TextView textView,u;
    Typeface typeface;

    final static String pref="prefer";
    String welcome_user;
    Button log;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    final static String prefs = "prefer";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        textView = (TextView) findViewById(R.id.newuser);
        u = (TextView) findViewById(R.id.wc);
        log = (Button) findViewById(R.id.btn_logout);
        typeface = Typeface.createFromAsset(getAssets(), "lobster.ttf");
        textView.setTypeface(typeface);


        textView.setTypeface(typeface);
        sharedPreferences = getSharedPreferences(pref, MODE_PRIVATE);
        welcome_user = (sharedPreferences.getString("username", ""));
        textView.setText(welcome_user);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences=getSharedPreferences(prefs, Context.MODE_PRIVATE);
                editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();


                Intent i= new Intent(Welcome.this,MainActivity.class);
                    startActivity(i);
                            }
        });
    }
      public void onBackPressed() {

    }

}
