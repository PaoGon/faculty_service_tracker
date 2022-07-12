package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faculty_service_tracker.model.Model;
import com.example.faculty_service_tracker.model.Teacher;

public class AdminHomePage extends AppCompatActivity implements TeacherFragment.onFragmentInteractionListener{

    //List<Teacher> teachers = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        Model model = Model.getInstance(AdminHomePage.this.getApplication());

        TextView head = findViewById(R.id.head);
        head.setText(R.string.teachers_list);

        ImageView btn_notif = findViewById(R.id.img_notif_btn);
        ImageView profile_pic = findViewById(R.id.profile_ic);
        btn_notif.setOnClickListener(view -> teacher_notif_page());

        GlideApp.with(this)
                .load(model.getUser().getProfile_dir())
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(profile_pic);

        FrameLayout container = findViewById(R.id.teacher_container);
        if(container != null){
            Fragment fragment = TeacherFragment.newInstance();
            FragmentManager fragmentManager =getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.teacher_container, fragment);
            fragmentTransaction.commit();
        }

        profile_pic.setOnClickListener(view -> profile_page());
    }

    // notification button for teacher_notif_page()
    private void teacher_notif_page() {
        Intent intent = new Intent(this, teacher_notif_page.class);
        startActivity(intent);
    }

    private void profile_page(){
        Intent intent = new Intent(this, AdminProfile.class);
        startActivity(intent);
    }


    @Override
    public void onItemSelected(Teacher teacher) {
        Toast.makeText(this, "id: " + teacher.getTeacher_id(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AdminHomePage.this, services_page.class);
        intent.putExtra("teacher_id", teacher.getTeacher_id());
        intent.putExtra("total_credits", teacher.getCredits());
        startActivity(intent);
    }
}