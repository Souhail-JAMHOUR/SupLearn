package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CourseMaterials extends AppCompatActivity {
    TextView title, desc, notif;
    ImageView image;
    Button enroll;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_materials);
        title = findViewById(R.id.material_course_title);
        desc = findViewById(R.id.material_course_description);
        image = findViewById(R.id.material_course_logo);
        notif = findViewById(R.id.course_notification);
        enroll = findViewById(R.id.enroll_button);
        Intent intent = getIntent();
        String userEmail = "souhailjamhour@gmail.com";
        String titleToAdd = intent.getStringExtra("title");
        String descriptionToAdd = intent.getStringExtra("description");
        LocalDate deadLine = LocalDate.parse(intent.getStringExtra("deadLine"),formatter);
        int img = intent.getIntExtra("image",0);
        title.setText(titleToAdd);
        image.setImageResource(img);
        desc.setText(descriptionToAdd);
        if(!(isStillUp(deadLine))){
            enroll.setEnabled(false);
            notif.setText(R.string.course_not_available);
        }
        enroll.setOnClickListener(view -> confirmDecision(userEmail,titleToAdd));
    }

    public boolean isStillUp(LocalDate date){
        return LocalDate.now().isBefore(date);
    }

    public void confirmDecision(String email,String courseName){
        new AlertDialog.Builder(this)
                .setTitle("Confirm Subscription")
                .setMessage("Do you really want to enroll this course?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        enrollCourse(email,courseName);
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void enrollCourse(String email ,String courseName){
        switch (courseName.toLowerCase()){
            case "java":
                addCourse(email,CoursesFragment.java);
                break;
            case "react":
                addCourse(email,CoursesFragment.react);
                break;
            case "angular":
                addCourse(email,CoursesFragment.angular);
                break;
            case "csharp":
                addCourse(email,CoursesFragment.csharp);
                break;
            case "spring":
                addCourse(email,CoursesFragment.spring);
                break;
            case "kotlin":
                addCourse(email,CoursesFragment.kotlin);
                break;
            case "rust":
                addCourse(email,CoursesFragment.rust);
                break;
            default:
                Toast.makeText(getApplicationContext(),"Course not found Try later",Toast.LENGTH_LONG).show();
                break;
        }
    }
    public void addCourse(String email,Course newCourse){
        boolean add = true;
        for(Course course : ProfileFragment.enrolledCourses){
            if (course.getName().equals(newCourse.getName())){
                Toast.makeText(getApplicationContext(),"Course already enrolled check your profile",Toast.LENGTH_LONG).show();
                add = false;
            }
        }
        if (add){
            ProfileFragment.enrolledCourses.add(newCourse);
            Toast.makeText(getApplicationContext(),"Course Added",Toast.LENGTH_LONG).show();
        }
        Intent profile = new Intent(this,HomeActivity.class);
        profile.putExtra("Frag","profile");
        startActivity(profile);
    }


}