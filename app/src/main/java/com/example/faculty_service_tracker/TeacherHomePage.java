package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class TeacherHomePage extends AppCompatActivity {

    /*LANDING PAGE2!*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page2);

        /*Create button for Teacher and Admin*/
        Button btn_teacher =findViewById(R.id.btn_teacher);
        Button btn_admin =findViewById(R.id.btn_admin);
        btn_teacher.setOnClickListener(view -> login_page());
        btn_admin.setOnClickListener(view -> login_page());


    }
        /*Button for LoginPage*/
    private void login_page(){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }


}