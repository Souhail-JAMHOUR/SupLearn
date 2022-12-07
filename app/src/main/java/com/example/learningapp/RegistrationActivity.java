package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    Button register;
    CheckBox check ;
    EditText studentId,email,password, repeatedPassword;
    DbManager dbManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dbManager = new DbManager(RegistrationActivity.this);
        register = findViewById(R.id.Button_register);
        check = findViewById(R.id.legal_term_check);
        studentId = findViewById(R.id.student_id);
        email = findViewById(R.id.student_email);
        password = findViewById(R.id.first_password);
        repeatedPassword = findViewById(R.id.repeated_password);

        register.setOnClickListener(v->{
            // add the logic to register the user and then send him to the login page
            String retrievedEmail = email.getText().toString();
            String retrievedId = studentId.getText().toString();
            String retrievedPassword = password.getText().toString();    // maybe log him directly after the registration
            registrationDone(retrievedId,retrievedEmail,retrievedPassword);
        });
    }
    public void registrationDone(String studentId, String studentEmail, String studentPassword){
        Intent registrationDone = new Intent(this,MainActivity.class);
        if (checkDone(check)){
           if(studentId.isEmpty() && studentEmail.isEmpty() && studentPassword.isEmpty()){
               Toast.makeText(getApplicationContext(),"Please enter valid information",Toast.LENGTH_LONG).show();
               return;
           }
           if(!(repeatedPassword.getText().toString().equals(studentPassword))){
               Toast.makeText(getApplicationContext(),"Passwords don't match",Toast.LENGTH_LONG).show();
               return;
           }
           dbManager.addNewUser(studentId,studentEmail,studentPassword);
            Toast.makeText(getApplicationContext(),"Registration done.",Toast.LENGTH_LONG).show();
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