package com.example.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class CoursesActivity extends AppCompatActivity {
    final Course[] courses = {
            new Course("Java",getString(R.string.java), R.drawable.java_logo, LocalDate.of(2023,5,11)),
            new Course("React",getString(R.string.react), R.drawable.react_logo, LocalDate.of(2023,1,20)),
            new Course("Angular", getString(R.string.angular), R.drawable.angular_logo, LocalDate.of(2022,12,30)),
            new Course("Kotlin", getString(R.string.kotlin), R.drawable.kotlin_logo, LocalDate.of(2023,2,10)),
            new Course("Rust", getString(R.string.rust), R.drawable.rust_logo, LocalDate.of(2022,9,1))
    };
    ArrayList<Course> coursesArrayList = new ArrayList<>();
    ListView courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        Collections.addAll(coursesArrayList,courses);
        courseList = findViewById(R.id.course_list);
        CoursesAdapter adapter = new CoursesAdapter(this,coursesArrayList);
        courseList.setAdapter(adapter);
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),CourseMaterials.class);
                intent.putExtra("title",coursesArrayList.get(i).getName());
                intent.putExtra("description",coursesArrayList.get(i).getDescription());
                intent.putExtra("image",coursesArrayList.get(i).getLogo());
                startActivity(intent);
            }
        });
    }
}