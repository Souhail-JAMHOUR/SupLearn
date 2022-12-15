package com.example.learningapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    // this should get courses from db
    public static ArrayList<Course> enrolledCourses = new ArrayList<>();
    TextView id, name, notification;
    Button btnNoCourses;
    DbManager dbManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
//        Intent intent = getActivity().getIntent();
//        dbManager = new DbManager(getContext());
//        ArrayList<String> info = dbManager.getUserInfo(MainActivity.usermail);
//        String courses = dbManager.getUserInfo(HomeActivity.userEmail).get(6);
//        String courses = info.get(6);
//        if (!(courses.isEmpty())){
//            String[] coursesList = courses.split(",");
//            for(String course : coursesList){
//                enrollCourse(course);
//            }
//        }
        btnNoCourses = view.findViewById(R.id.btn_nocourses);
        btnNoCourses.setVisibility(View.GONE);
        notification = view.findViewById(R.id.home_notificaton);
        ListView listView = (ListView) view.findViewById(R.id.enrolled_courses);
        btnNoCourses.setOnClickListener(view1 -> goToCourses());
        if (enrolledCourses.isEmpty()) {
            notification.setText("No Enrolled Course");
            btnNoCourses.setVisibility(View.VISIBLE);
        }
        CoursesAdapter adapter = new CoursesAdapter(getContext(), enrolledCourses);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), CourseMaterials.class);
                intent.putExtra("title", enrolledCourses.get(i).getName());
                intent.putExtra("description", enrolledCourses.get(i).getDescription());
                intent.putExtra("image", enrolledCourses.get(i).getLogo());
                intent.putExtra("deadLine", enrolledCourses.get(i).getDeadline().toString());
                startActivity(intent);
            }
        });
        return view;
    }

    public void goToCourses() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        intent.putExtra("Frag", "courses");
        startActivity(intent);
    }



}