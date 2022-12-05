package com.example.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class CoursesActivity extends AppCompatActivity {
    String [] courses = {"Java", "React" , "Angular", "Kotlin","Lua", "Rust"};
    int [] courseLogos = {R.drawable.java_logo, R.drawable.react_logo, R.drawable.angular_logo, R.drawable.kotlin_logo, R.drawable.lua_logo, R.drawable.rust_logo};
    ListView courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        courseList = findViewById(R.id.course_list);
        CoursesAdapter adapter = new CoursesAdapter(getApplicationContext(),courses,courseLogos);
        courseList.setAdapter(adapter);
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),CourseMaterials.class);
                intent.putExtra("title",courses[i]);
                intent.putExtra("description","Course description");
                intent.putExtra("image",courseLogos[i]);
                startActivity(intent);
            }
        });
    }
}