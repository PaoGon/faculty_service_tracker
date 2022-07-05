package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.faculty_service_tracker.model.Teacher;

public class AdminHomePage extends AppCompatActivity implements TeacherFragment.onFragmentInteractionListener{

    //List<Teacher> teachers = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home_page);

        /* Create button for Login to teacher_notif_page() */
        ImageView btn_login = findViewById(R.id.img_notif_btn);
        btn_login.setOnClickListener(view -> teacher_notif_page());

        FrameLayout container = findViewById(R.id.teacher_container);
        if(container != null){
            Fragment fragment = TeacherFragment.newInstance();
            FragmentManager fragmentManager =getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.teacher_container, fragment);
            fragmentTransaction.commit();
        }
    }

    // notification button for teacher_notif_page()
    private void teacher_notif_page() {
        Intent intent = new Intent(this, teacher_notif_page.class);
        startActivity(intent);
    }


    @Override
    public void onItemSelected(Teacher teacher) {
        Toast.makeText(this, "id: " + teacher.getTeacher_id(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AdminHomePage.this, services_page.class);
        intent.putExtra("teacher_id", teacher.getTeacher_id());
        startActivity(intent);
    }
}