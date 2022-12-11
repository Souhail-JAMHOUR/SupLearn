package com.example.learningapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class CoursesFragment extends Fragment
{
    ArrayList<Course> coursesArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Course[] courses = {
                new Course("Java",view.getResources().getString(R.string.java), R.drawable.java_logo,LocalDate.of(2023,5,11)),
                new Course("React",view.getResources().getString(R.string.react), R.drawable.react_logo, LocalDate.of(2023,1,20)),
                new Course("Angular", view.getResources().getString(R.string.angular), R.drawable.angular_logo, LocalDate.of(2022,12,30)),
                new Course("Kotlin", view.getResources().getString(R.string.kotlin), R.drawable.kotlin_logo, LocalDate.of(2022,2,10)),
                new Course("Rust", view.getResources().getString(R.string.rust), R.drawable.rust_logo, LocalDate.of(2022,9,1))
        };
        ListView listView = (ListView) view.findViewById(R.id.course_list_frag);
        Collections.addAll(coursesArrayList,courses);
        CoursesAdapterFragments adapter = new CoursesAdapterFragments(getContext(),coursesArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),CourseMaterials.class);
                intent.putExtra("title",coursesArrayList.get(i).getName());
                intent.putExtra("description",coursesArrayList.get(i).getDescription());
                intent.putExtra("image",coursesArrayList.get(i).getLogo());
                intent.putExtra("deadLine",coursesArrayList.get(i).getDeadline().toString());
                startActivity(intent);
            }
        });
    }
}