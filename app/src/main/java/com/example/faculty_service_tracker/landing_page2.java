package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class landing_page2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page2);

        /*Teacher and Admin button*/
        Button btn_teacher =findViewById(R.id.btn_teacher);
        Button btn_admin =findViewById(R.id.btn_admin);
        btn_teacher.setOnClickListener(view -> login_page());
        btn_admin.setOnClickListener(view -> login_page());


    }

    private void login_page(){
        Intent intent = new Intent(this, login_page.class);
        startActivity(intent);
    }


}