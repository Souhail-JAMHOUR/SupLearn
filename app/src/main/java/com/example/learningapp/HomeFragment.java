package com.example.learningapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    public static ArrayList<Course> enrolledCourses = new ArrayList<>();
    TextView notification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        notification = view.findViewById(R.id.home_notificaton);
        ListView listView = (ListView) view.findViewById(R.id.enrolled_courses);
        if(enrolledCourses.isEmpty()){
            notification.setText("No Enrolled Course");
        }
        CoursesAdapter adapter = new CoursesAdapter(getContext(),enrolledCourses);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),CourseMaterials.class);
                intent.putExtra("title",enrolledCourses.get(i).getName());
                intent.putExtra("description",enrolledCourses.get(i).getDescription());
                intent.putExtra("image",enrolledCourses.get(i).getLogo());
                intent.putExtra("deadLine",enrolledCourses.get(i).getDeadline().toString());
                startActivity(intent);
            }
        });
        return view;
    }
}