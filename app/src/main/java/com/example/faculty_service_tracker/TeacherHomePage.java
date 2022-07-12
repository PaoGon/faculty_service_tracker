package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faculty_service_tracker.create_service_form.create_services;
import com.example.faculty_service_tracker.model.Model;
import com.example.faculty_service_tracker.model.Service;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TeacherHomePage extends AppCompatActivity implements ServiceFragment.onFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home_page);

        Model model = Model.getInstance(TeacherHomePage.this.getApplication());
        TextView head = findViewById(R.id.head);
        head.setText(R.string.txtView_services);

        /* Create button for Login to teacher_notif_page() */
        ImageView btn_notif = findViewById(R.id.img_notif_btn);
        ImageView profile_pic = findViewById(R.id.profile_ic);
        FloatingActionButton create_service = findViewById(R.id.fab);
        btn_notif.setOnClickListener(view -> teacher_notif_page());

        GlideApp.with(this)
                .load(model.getUser().getProfile_dir())
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(profile_pic);

        FrameLayout container = findViewById(R.id.teacher_service_cont);
        if(container != null){
            Fragment fragment = ServiceFragment.newInstance(model.getUser().getAcc_id());
            FragmentManager fragmentManager =getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.teacher_service_cont, fragment);
            fragmentTransaction.commit();
        }

        create_service.setOnClickListener(view -> {
            Intent intent = new Intent(this, create_services.class);
            startActivity(intent);
        });
        profile_pic.setOnClickListener(view -> profile_page());

    }
    private void teacher_notif_page() {
        Intent intent = new Intent(this, teacher_notif_page.class);
        startActivity(intent);
    }

    private void profile_page(){
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(Service service) {
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
    }
}