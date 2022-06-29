package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class services_page extends AppCompatActivity {

    ArrayList<ServicesDataModel> servicesDataModel = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_page);

        /* Create button for AdminHomePage()*/
        ImageView btn = findViewById(R.id.img_home_btn);
        btn.setOnClickListener(view -> teacher_home_page());

        /* Create button for Login to teacher_notif_page() */
        ImageView btn_login = findViewById(R.id.img_notif_btn);
        btn_login.setOnClickListener(view -> teacher_notif_page());


        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        setUpServicesDataModel();

        //create adapter before you setup your data models
        Services_RecyclerViewAdapter adapter = new Services_RecyclerViewAdapter(this,servicesDataModel);
        //after you create adapter, you need to attached this from recycler view
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpServicesDataModel(){
        int [] servicesImages = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic2, R.drawable.pic3, R.drawable.pic1,

                R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic1, R.drawable.pic2, R.drawable.pic3};

        for (int i = 0; i <servicesImages.length; i++){
            servicesDataModel.add(new ServicesDataModel (servicesImages[i]));
        }
    }
    //  button for teacher_notif_page()
    private void teacher_notif_page() {
        Intent intent = new Intent(this, teacher_notif_page.class);
        startActivity(intent);
    }

    // Home button for teacher home page()
    private void teacher_home_page(){
        Intent intent = new Intent(this, AdminHomePage.class);
        startActivity(intent);
    }

}