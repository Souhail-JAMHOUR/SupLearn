package com.example.learningapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CoursesAdapter extends ArrayAdapter<Course> {
    public Context ctx;
    public LayoutInflater inflater;
    private ArrayList<Course> coursesList;

    CoursesAdapter(Context ctx, ArrayList<Course>coursesList){
        super(ctx,R.layout.activity_courses_row,coursesList);
        this.ctx= ctx;
        this.coursesList = coursesList;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return coursesList.size();
    }

//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }

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