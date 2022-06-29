package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class teacher_notif_page extends AppCompatActivity {

    ArrayList<TeacherNotificationDataModel> teacherNotificationDataModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_notif_page);

        /*Create button for back*/
        ImageView btn_back = findViewById(R.id.imgView_back_btn);
        btn_back.setOnClickListener(view -> teacher_home_page());

        // Create home button for teacher home_page()
        ImageView btn_home = findViewById(R.id.img_home_btn);
        btn_home.setOnClickListener(view -> teacher_home_page());

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        //setUpTeacherNotificationDataModels();

        TeacherNotifPage_RecyclerViewAdapter adapter = new TeacherNotifPage_RecyclerViewAdapter(this,
                teacherNotificationDataModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    //private void setUpTeacherNotificationDataModels(){
    //    String [] teacherNames = getResources().getStringArray(R.array.teacher_notif_names_txt);
    //    int [] teacherImages = {R.drawable.mccoy, R.drawable.pao, R.drawable.waaa,R.drawable.ic_pic5,R.drawable.ic_pic4,
    //    R.drawable.ic_pic4, R.drawable.ic_pic1, R.drawable.ic_pic3, R.drawable.ic_pic2, R.drawable.ic_pic3};

    //    for(int i = 0; i <teacherImages.length; i++) {
    //        teacherNotificationDataModels.add(new TeacherNotificationDataModel(teacherImages[i], teacherNames[i]));
    //    }
    //}


    private void teacher_home_page() {
        Intent intent = new Intent(this, AdminHomePage.class);
        startActivity(intent);
    }




}