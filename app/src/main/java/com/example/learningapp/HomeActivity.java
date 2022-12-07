package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    Button goToCourses;
    TextView name,major;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        name = findViewById(R.id.student_name);
//        major = findViewById(R.id.student_major);
        name.setText(intent.getStringExtra("email"));
        goToCourses = findViewById(R.id.courses_button);
        goToCourses.setOnClickListener(v -> displayCourses());
    }

    public void displayCourses(){
        Intent intent = new Intent(this, CoursesActivity.class);
        startActivity(intent);

    }
}