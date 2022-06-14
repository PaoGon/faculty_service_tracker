package com.example.faculty_service_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class login_page extends AppCompatActivity {

    /*LOGIN PAGE!*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        /*Create button for back*/
        ImageView ic_back = findViewById(R.id.ic_back);
        ic_back.setOnClickListener(view -> landing_page2());


        /* Create button for Login to teacher_home_page() */
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(view -> teacher_home_page());



    }
        /*button back to landing page2*/
    private void landing_page2(){
        Intent intent = new Intent(this, landing_page2.class);
        startActivity(intent);

    }

    private void teacher_home_page() {
        Intent intent = new Intent(this, teacher_home_page.class);
        startActivity(intent);
    }


}