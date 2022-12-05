package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    Button register;
    CheckBox check ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        register = findViewById(R.id.Button_register);
        check = findViewById(R.id.legal_term_check);
        register.setOnClickListener(v->{
            // add the logic to register the user and then send him to the login page
            // maybe log him directly after the registration
            registrationDone();
        });
    }
    public void registrationDone(){
        Intent registrationDone = new Intent(this,MainActivity.class);
        if (checkDone(check)){
            startActivity(registrationDone);
        }
        else{
            Toast.makeText(getApplicationContext(),"Please Confirm the Legal terms",
                    Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkDone(CheckBox box){
        boolean result;
        result = box.isChecked();
        return result;
    }
}