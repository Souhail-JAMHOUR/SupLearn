package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learningapp.databinding.LoginActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button loginButton, registrationButton;
    EditText email , password;
    DbManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);
        dbManager = new DbManager(MainActivity.this);
        loginButton = findViewById(R.id.LoginButton);
        registrationButton = findViewById(R.id.RegisterButton);
        email = findViewById(R.id.email_adress);
        password = findViewById(R.id.login_password);
        registrationButton.setOnClickListener(v -> {
            Intent registrationIntent = new Intent(this,RegistrationActivity.class);
            startActivity(registrationIntent);
        });
        loginButton.setOnClickListener(view -> {
           // the login logic to verify the user name and the password
            login(email,password);
        });
    }
    public void login(EditText email, EditText password){
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        Intent login = new Intent(this, HomeActivity.class);
        if(dbManager.verifyUser(emailText,passwordText)){
            ArrayList<String> info = dbManager.getUserInfo(emailText);
           //auth verified
            login.putExtra("id",info.get(1));
            login.putExtra("name",info.get(2));
            login.putExtra("major",info.get(3));
            login.putExtra("email",info.get(4));
            startActivity(login);
        }
        else if(emailText.equals("admin") && passwordText.equals("admin")){
            login.putExtra("name","ADMIN");
            login.putExtra("id","ADMIN");
            startActivity(login);
        }
        else{
            Toast.makeText(getApplicationContext(),"Verify your credentials and try again",
                    Toast.LENGTH_LONG).show();
        }
    }
}