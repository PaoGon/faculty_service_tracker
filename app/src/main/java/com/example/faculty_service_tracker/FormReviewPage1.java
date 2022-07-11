package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faculty_service_tracker.model.Model;

public class FormReviewPage1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_review_page1);

        Model model = Model.getInstance(FormReviewPage1.this.getApplication());

        ImageView back = findViewById(R.id.imgView_back_btn);
        TextView head = findViewById(R.id.tvEditProfile);
        Button submit = findViewById(R.id.CSV_button);
        head.setText(R.string.review);

        CardView service_details = findViewById(R.id.service_details);

        Intent intent = getIntent();
        String start_date = intent.getStringExtra("start_date");
        String end_date = intent.getStringExtra("ending_date");
        String venue = intent.getStringExtra("venue");
        String sponsor = intent.getStringExtra("sponsor");
        String lvl_event = intent.getStringExtra("lvl_event");
        int credit = intent.getIntExtra("credit", 0);
        int teacher_id = intent.getIntExtra("teacher_id", 0);
        int service_id = intent.getIntExtra("service_id", 0);
        int total_credit = intent.getIntExtra("total_credit", 0);

        service_details.setOnClickListener(view -> service_details_page(
                start_date,
                end_date,
                venue,
                sponsor,
                lvl_event,
                credit
        ));
        back.setOnClickListener(view -> finish());

        submit.setOnClickListener(view -> {
            if(credit == 0 ){
                model.confirm_service(teacher_id, service_id, total_credit, start_date, end_date);
            }
        });
    }

    private void service_details_page(String start_date, String end_date, String venue, String sponsor, String lvl_event, int credit) {
        Intent intent = new Intent(FormReviewPage1.this, ServiceDetails.class);
        intent.putExtra("start_date", start_date);
        intent.putExtra("ending_date", end_date);
        intent.putExtra("venue", venue);
        intent.putExtra("sponsor", sponsor);
        intent.putExtra("level_of_event", lvl_event);
        intent.putExtra("credit", credit);
        startActivity(intent);
    }

}