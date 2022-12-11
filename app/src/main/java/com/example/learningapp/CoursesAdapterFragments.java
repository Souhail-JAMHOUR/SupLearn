package com.example.learningapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CoursesAdapterFragments extends ArrayAdapter<Course> {
    public LayoutInflater inflater;
    private ArrayList<Course> coursesList;

    CoursesAdapterFragments(Context ctx,ArrayList<Course>coursesList){
        super(ctx,R.layout.activity_courses_row,coursesList);
        this.coursesList = coursesList;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Course course = coursesList.get(i);
        convertView = inflater.inflate(R.layout.activity_courses_row,null);
        ImageView image = (ImageView) convertView.findViewById(R.id.course_logo);
        TextView title = convertView.findViewById(R.id.course_title);
        title.setText(course.getName());
        image.setImageResource(course.getLogo());
        return convertView;
    }
}