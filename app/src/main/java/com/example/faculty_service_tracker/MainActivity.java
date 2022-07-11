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

        Button login_btn =findViewById(R.id.login_btn);
        login_btn.setOnClickListener(view -> login_page());

    }



    private void login_page() {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }




}