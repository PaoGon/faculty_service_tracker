package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.faculty_service_tracker.model.Service;

import java.util.ArrayList;

public class services_page extends AppCompatActivity implements ServiceFragment.onFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_page);

        Intent intent = getIntent();
        int teacher_id = intent.getIntExtra("teacher_id", 0);
        Toast.makeText(this, "id: " + teacher_id , Toast.LENGTH_SHORT).show();

        /* Create button for AdminHomePage()*/
        ImageView btn = findViewById(R.id.img_home_btn);
        btn.setOnClickListener(view -> teacher_home_page());

        /* Create button for Login to teacher_notif_page() */
        ImageView btn_login = findViewById(R.id.img_notif_btn);
        btn_login.setOnClickListener(view -> teacher_notif_page());

        FrameLayout container = findViewById(R.id.service_container);
        if(container != null){
            Fragment fragment = ServiceFragment.newInstance(teacher_id);
            FragmentManager fragmentManager =getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.service_container, fragment);
            fragmentTransaction.commit();
        }


    }

    //  button for teacher_notif_page()
    private void teacher_notif_page() {
        Intent intent = new Intent(this, teacher_notif_page.class);
        startActivity(intent);
    }

    // Home button for teacher home page()
    private void teacher_home_page(){
        //Intent intent = new Intent(this, AdminHomePage.class);
        //startActivity(intent);
        NavUtils.navigateUpFromSameTask(services_page.this);
    }

    @Override
    public void onItemSelected(Service service) {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();

    }
}