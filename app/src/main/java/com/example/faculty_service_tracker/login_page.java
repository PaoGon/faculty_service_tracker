package com.example.faculty_service_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class login_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        /*onclick back*/
        ImageView ic_back = findViewById(R.id.ic_back);
        ic_back.setOnClickListener(view -> landing_page2());


    }
        /*button back to landing page2*/
    private void landing_page2(){
        Intent intent = new Intent(this, landing_page2.class);
        startActivity(intent);
    }
}