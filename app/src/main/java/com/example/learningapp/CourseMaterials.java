package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CourseMaterials extends AppCompatActivity {
    TextView title, desc, notif;
    ImageView image;
    Button enroll;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_materials);
        title = findViewById(R.id.material_course_title);
        desc = findViewById(R.id.material_course_description);
        image = findViewById(R.id.material_course_logo);
        notif = findViewById(R.id.course_notification);
        enroll = findViewById(R.id.enroll_button);
        Intent intent = getIntent();
        String titleToAdd = intent.getStringExtra("title");
        String descriptionToAdd = intent.getStringExtra("description");
        LocalDate deadLine = LocalDate.parse(intent.getStringExtra("deadLine"),formatter);
        int img = intent.getIntExtra("image",0);
        title.setText(titleToAdd);
        image.setImageResource(img);
        desc.setText(descriptionToAdd);
        if(!(isStillUp(deadLine))){
            enroll.setEnabled(false);
            notif.setText(R.string.course_not_available);
        }
        enroll.setOnClickListener(view -> enrollCourse(titleToAdd,descriptionToAdd,img,deadLine));
    }

    public boolean isStillUp(LocalDate date){
        return LocalDate.now().isBefore(date);
    }


    public void enrollCourse(String courseName, String description,int image,LocalDate date){
        Course newCourse = new Course(courseName,description,image,date);
        HomeFragment.enrolledCourses.add(newCourse);
        Toast.makeText(getApplicationContext(),"Course Added",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,HomeActivity.class));
    }


}