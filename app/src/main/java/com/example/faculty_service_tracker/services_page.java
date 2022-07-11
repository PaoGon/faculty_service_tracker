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

import com.example.faculty_service_tracker.model.Model;
import com.example.faculty_service_tracker.model.Service;

import java.util.ArrayList;

public class services_page extends AppCompatActivity implements ServiceFragment.onFragmentInteractionListener{
    int total_credits, teacher_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_page);

        Model model = Model.getInstance(services_page.this.getApplication());

        Intent intent = getIntent();
        teacher_id = intent.getIntExtra("teacher_id", 0);
        total_credits = intent.getIntExtra("total_credits", 0);
        Toast.makeText(this, "id: " + teacher_id , Toast.LENGTH_SHORT).show();

        /* Create button for AdminHomePage()*/
        ImageView home = findViewById(R.id.img_home_btn);

        /* Create button for Login to teacher_notif_page() */
        ImageView notif = findViewById(R.id.img_notif_btn);

        ImageView profile_pic = findViewById(R.id.profile_ic);
        GlideApp.with(this)
                .load(model.getUser().getProfile_dir())
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(profile_pic);

        FrameLayout container = findViewById(R.id.service_container);
        if(container != null){
            Fragment fragment = ServiceFragment.newInstance(teacher_id);
            FragmentManager fragmentManager =getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.service_container, fragment);
            fragmentTransaction.commit();
        }

        home.setOnClickListener(view -> teacher_home_page());
        notif.setOnClickListener(view -> teacher_notif_page());
        profile_pic.setOnClickListener(view -> profile_page());

    }

    //  button for teacher_notif_page()
    private void teacher_notif_page() {
        Intent intent = new Intent(this, teacher_notif_page.class);
        startActivity(intent);
    }

    // Home button for teacher home page()
    private void teacher_home_page(){
        NavUtils.navigateUpFromSameTask(services_page.this);
    }

    private void profile_page(){
        Intent intent = new Intent(this, AdminProfile.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(Service service) {
        Intent intent = new Intent(services_page.this, FormReviewPage1.class);
        intent.putExtra("start_date", service.getStarting_date());
        intent.putExtra("ending_date", service.getEnding_date());
        intent.putExtra("venue", service.getVenue());
        intent.putExtra("sponsor", service.getSponsor());
        intent.putExtra("lvl_event", service.getLvl_of_event());
        intent.putExtra("credit", service.getCredit_point());
        intent.putExtra("teacher_id", teacher_id);
        intent.putExtra("service_id", service.getService_id());
        intent.putExtra("total_credits", total_credits);
        startActivity(intent);

    }
}