package com.example.moneyfrog.logindemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    TextView textView,u;
    Typeface typeface;
    SharedPreferences sharedPreferences;
    final static String pref="prefer";
    String welcome_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        textView=(TextView)findViewById(R.id.newuser);
        u=(TextView)findViewById(R.id.wc);
        typeface=Typeface.createFromAsset(getAssets(),"lobster.ttf");
        textView.setTypeface(typeface);



        textView.setTypeface(typeface);
        sharedPreferences=getSharedPreferences(pref,MODE_PRIVATE);
        welcome_user=(sharedPreferences.getString("username",""));
        textView.setText(welcome_user);








    }

    public void onBackPressed() {
    }

}
