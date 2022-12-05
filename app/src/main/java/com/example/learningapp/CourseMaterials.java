package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CourseMaterials extends AppCompatActivity {
    TextView title, desc;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_materials);
        title = findViewById(R.id.material_course_title);
        desc = findViewById(R.id.material_course_description);
        image = findViewById(R.id.material_course_logo);
        Intent intent = getIntent();
        String titleToAdd = intent.getStringExtra("title");
        int img = intent.getIntExtra("image",0);
        title.setText(titleToAdd);
        image.setImageResource(img);
    }
}