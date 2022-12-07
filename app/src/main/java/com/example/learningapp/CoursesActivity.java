package com.example.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class CoursesActivity extends AppCompatActivity {
    final String lorem = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
    final Course[] courses = {
            new Course("Java",lorem, R.drawable.java_logo),
            new Course("React", lorem, R.drawable.react_logo),
            new Course("Angular", lorem, R.drawable.angular_logo),
            new Course("Kotlin", lorem, R.drawable.kotlin_logo),
            new Course("Rust", lorem, R.drawable.rust_logo)
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