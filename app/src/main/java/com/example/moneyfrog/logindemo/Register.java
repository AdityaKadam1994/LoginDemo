package com.example.moneyfrog.logindemo;

import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    Dbhelper dbhelper = new Dbhelper(this);

    EditText name, userrname, pass, add, mail;
    Button button;
    Typeface typeface;
    String namestr, unamestr, passstr, addstr, mailstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.tv_username);
        userrname = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        add = (EditText) findViewById(R.id.address);
        button = (Button) findViewById(R.id.register);
        mail = (EditText) findViewById(R.id.mail);
        typeface = Typeface.createFromAsset(getAssets(), "lobster.ttf");

        //Typeface
        name.setTypeface(typeface);
        userrname.setTypeface(typeface);
        pass.setTypeface(typeface);
        add.setTypeface(typeface);
        button.setTypeface(typeface);
        mail.setTypeface(typeface);


        //Registration Message and validation

        button.setOnClickListener(new View.OnClickListener() {


            //@Override
            public void onClick(View view) {
                if (view.getId() == R.id.register) {

                    namestr = name.getText().toString();
                    unamestr = userrname.getText().toString();
                    passstr = pass.getText().toString();
                    addstr = add.getText().toString();
                    mailstr = mail.getText().toString();


                    if (name.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_LONG).show();
                    }
                   else  if (userrname.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter Username", Toast.LENGTH_LONG).show();
                    }
                    else if (pass.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_LONG).show();
                    }
                    else if (add.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter Address", Toast.LENGTH_LONG).show();
                    }
                    else if (mail.getText().toString().isEmpty()) {

                        Toast.makeText(getApplicationContext(), "Enter Email address", Toast.LENGTH_LONG).show();

                    }
                    else if (!validateEmail(mail.getText().toString())) {
                        mail.setError("Invalid Email address");
                        mail.requestFocus();

                    }
                    else

                {
                    Connections c = new Connections();
                    c.setName(namestr);
                    c.setUname(unamestr);
                    c.setPass(passstr);
                    c.setAdd(addstr);
                    c.setEmail(mailstr);

                    dbhelper.insertConnections(c);


                    Toast.makeText(getApplicationContext(), "You are registered successfully", Toast.LENGTH_LONG).show();


                }
            }
            }

        });


    }

    private boolean validateEmail(String s) {
        String emailpat="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
        Pattern pattern =Pattern.compile(emailpat);
        Matcher matcher=pattern.matcher(s);
        return matcher.matches();


    }
}



