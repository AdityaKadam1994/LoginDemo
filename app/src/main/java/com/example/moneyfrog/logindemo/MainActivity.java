package com.example.moneyfrog.logindemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Dbhelper dbhelper= new Dbhelper(this);

    TextView tv;
    EditText username,password;
    Typeface typeface;
    String stripe;
    int i1,i2;
    Spannable spannable;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button button;
    final static String prefs = "prefer";
    String get_username,a,b,passes,get_pass;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv=(TextView)findViewById(R.id.register);
        password=(EditText) findViewById(R.id.tv_pass);
        username=(EditText) findViewById(R.id.tv_name);
        button=(Button)findViewById(R.id.btn_log);

        typeface = Typeface.createFromAsset(getAssets(), "lobster.ttf");

        tv.setTypeface(typeface);
        username.setTypeface(typeface);
        password.setTypeface(typeface);
        button.setTypeface(typeface);
        //Link to Register

        stripe="Dont Have Account? [Register]";
        i1=stripe.indexOf("[");
        i2=stripe.indexOf("]");
        tv.setText(stripe,TextView.BufferType.SPANNABLE);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        spannable=(Spannable)tv .getText();
        ClickableSpan span=new ClickableSpan() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,Register.class));

            }
        };

        spannable.setSpan(span,i1,i2+1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //Login Validation

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(view.getId()==R.id.btn_log) {
                    if (username.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter Username", Toast.LENGTH_LONG).show();
                    }
                   else if (password.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_LONG).show();

                    }

                    a = username.getText().toString();
                    b = password.getText().toString();
                    passes = dbhelper.searchPass(a);


                    if (b.equals(passes)){
                        sharedPreferences=getSharedPreferences(prefs, Context.MODE_PRIVATE);
                        editor=sharedPreferences.edit();

                        get_username=username.getText().toString();
                        get_pass=password.getText().toString();


                        Intent i=new Intent(getApplicationContext(),Welcome.class);
                        startActivity(i);
                        editor.putString("username",get_username);
                        editor.putString("password",get_pass);
                        editor.apply();

                    }

                    else {
                        Toast.makeText(getApplicationContext(),"Username or password wrong",Toast.LENGTH_LONG).show();

                    }


                }


            }
        });






    }
}
