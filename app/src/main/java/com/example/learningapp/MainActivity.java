package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button loginButton, registrationButton;
    EditText email , password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);
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
        if(emailText.equals("admin") && passwordText.equals("admin")){
            Intent login = new Intent(this, HomeActivity.class);
            login.putExtra("name",emailText);
            login.putExtra("major","CISI4");
            startActivity(login);
        }
        else{
            Toast.makeText(getApplicationContext(),"Verify your credentials and try again",
                    Toast.LENGTH_LONG).show();
        }
    }
}