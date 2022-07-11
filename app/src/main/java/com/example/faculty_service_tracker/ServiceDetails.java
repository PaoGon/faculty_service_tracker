package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ServiceDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);

        ImageView back = findViewById(R.id.imgView_back_btn);
        TextView head = findViewById(R.id.tvEditProfile);
        TextView s_date = findViewById(R.id.tvInputEvent);
        TextView e_date = findViewById(R.id.tvInputEventDate);
        TextView ven = findViewById(R.id.tvInputVenue);
        TextView l_sponsor = findViewById(R.id.tvInputSponsor);
        TextView lvl_of_event = findViewById(R.id.tvInputLevelofEvent);
        TextView points = findViewById(R.id.tvInputTotalCredits);

        //passed data from previous activity
        Intent intent = getIntent();
        String start_date = intent.getStringExtra("start_date");
        String end_date = intent.getStringExtra("ending_date");
        String venue = intent.getStringExtra("venue");
        String sponsor = intent.getStringExtra("sponsor");
        String lvl_event = intent.getStringExtra("level_of_event");
        int credit = intent.getIntExtra("credit", 0);

        head.setText(R.string.service_page);
        s_date.setText(start_date);
        e_date.setText(end_date);
        ven.setText(venue);
        l_sponsor.setText(sponsor);
        lvl_of_event.setText(lvl_event);
        points.setText(Integer.toString(credit));


        back.setOnClickListener(view -> finish());

    }
}