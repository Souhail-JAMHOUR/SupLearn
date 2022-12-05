package com.example.learningapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CoursesAdapter extends BaseAdapter {
    public Context ctx;
    public String[] courses;
    public int[] courseImages;
    public LayoutInflater inflater;

    CoursesAdapter(Context ctx, String[]courses, int[] coursesImages){
        this.ctx= ctx;
        this.courseImages = coursesImages;
        this.courses = courses;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return courses.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        convertView = inflater.inflate(R.layout.activity_courses_row,null);
        ImageView image = (ImageView) convertView.findViewById(R.id.course_logo);
        TextView title = convertView.findViewById(R.id.course_title);
        title.setText(courses[i]);
        image.setImageResource(courseImages[i]);
        return convertView;
    }
}