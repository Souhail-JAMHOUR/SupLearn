package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import com.example.learningapp.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(new HomeFragment());
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(getIntent().getStringExtra("Frag") != null){
//            if (getIntent().getStringExtra("Frag").equals("profile")){
//                replaceFragment(new ProfileFragment());
//            }
            switch (getIntent().getStringExtra("Frag")){
                case "profile":
                    replaceFragment(new ProfileFragment());
                    binding.bottomNavigationView.setSelectedItemId(R.id.profile);
                    break;
                case "courses":
                    replaceFragment(new CoursesFragment());
                    binding.bottomNavigationView.setSelectedItemId(R.id.courses);
                    break;
            }
        }
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.courses:
                    replaceFragment(new CoursesFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment frag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,frag);
        fragmentTransaction.commit();
    }
}
