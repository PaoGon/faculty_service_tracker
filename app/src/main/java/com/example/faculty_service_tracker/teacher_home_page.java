package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class teacher_home_page extends AppCompatActivity implements SelectListener{

    ArrayList<TeacherHomeDataModel> teacherHomeDataModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home_page);


        /* Create button for Login to teacher_notif_page() */
        ImageView btn_login = findViewById(R.id.img_notif_btn);
        btn_login.setOnClickListener(view -> teacher_notif_page());

        // recyclerview on your teacher_home_page.xml
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // your setUpTeacherHomeDataModels
        setUpTeacherHomeDataModels();

        //Create Adapter after you setup the Models or your setUpTeacherHomeDataModels();
        TeacherHome_RecyclerViewAdapter adapter = new TeacherHome_RecyclerViewAdapter(this,
                teacherHomeDataModels, this);
        // attach the adapter to our recyclerView
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void setUpTeacherHomeDataModels(){
        int [] images = {R.drawable.mccoy, R.drawable.pao, R.drawable.waaa,R.drawable.ic_pic5,R.drawable.ic_pic4,
                R.drawable.ic_pic4, R.drawable.ic_pic1, R.drawable.ic_pic3, R.drawable.ic_pic2, R.drawable.ic_pic3};

        String [] teacherNames = getResources().getStringArray(R.array.teacher_home_names_txt);
        String [] positions = getResources().getStringArray(R.array.teacher_home_positions_txt);
        String [] credits = getResources().getStringArray(R.array.teacher_home_credits_txt);

        //if you are passing the array, we need to match it with TeacherHomeDataModel "constructor"
        for (int i = 0; i< teacherNames.length; i++){
            teacherHomeDataModels.add(new TeacherHomeDataModel (images[i],
                    teacherNames[i], positions[i], credits[i]));
        }
    }

    // notification button for teacher_notif_page()
    private void teacher_notif_page() {
        Intent intent = new Intent(this, teacher_notif_page.class);
        startActivity(intent);
    }

    // implement methods on SelectLister
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, services_page.class);
        startActivity(intent);
    }
}