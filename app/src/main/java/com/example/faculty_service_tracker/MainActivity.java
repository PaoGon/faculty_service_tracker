package com.example.faculty_service_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    /*LANDING PAGE!*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Create button to TeacherHomePage*/
        Button login_btn =findViewById(R.id.login_btn);
        login_btn.setOnClickListener(view -> landing_page2());

    }



    /*Login button for TeacherHomePage*/
    private void landing_page2() {
        Intent intent = new Intent(this, TeacherHomePage.class);
        startActivity(intent);
    }




}