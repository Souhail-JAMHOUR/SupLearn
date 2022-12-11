package com.example.learningapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;


public class CoursesAdapter extends ArrayAdapter<Course> {
    public Context ctx;
    public LayoutInflater inflater;
    private ArrayList<Course> coursesList;

    CoursesAdapter(Context ctx, ArrayList<Course>coursesList){
        super(ctx,R.layout.activity_home_courses,coursesList);
        this.ctx= ctx;
        this.coursesList = coursesList;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Course course = coursesList.get(i);
        convertView = inflater.inflate(R.layout.activity_home_courses,null);
        ImageView image = (ImageView) convertView.findViewById(R.id.course_image_preview);
        Button unsub = (Button) convertView.findViewById(R.id.unsub_button);
//        TextView title = convertView.findViewById(R.id.course_title);
//        title.setText(course.getName());
        image.setImageResource(course.getLogo());
        unsub.setOnClickListener(view -> {
            HomeFragment.enrolledCourses.remove(i);
            notifyDataSetInvalidated();
            Toast.makeText(getContext(),"Course Removed",Toast.LENGTH_LONG).show();
        });
        return convertView;
    }
}