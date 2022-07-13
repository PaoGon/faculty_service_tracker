package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faculty_service_tracker.model.Model;

public class AdminProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        Model model = Model.getInstance(AdminProfile.this.getApplication());

        ImageView back = findViewById(R.id.imgView_back_btn);
        ImageView profile = findViewById(R.id.imProfile);
        TextView name = findViewById(R.id.profile_name);
        Button add_acc = findViewById(R.id.btn_AddAccount2);
        Button edit_profile = findViewById(R.id.btn_AdminEditProfile);
        Button logout = findViewById(R.id.btn_adminLogout);

        name.setText(model.getUser().getFull_name());


        GlideApp.with(this)
                .load(model.getUser().getProfile_dir())
                .circleCrop()
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(profile);

        back.setOnClickListener(view -> finish());
        add_acc.setOnClickListener(view -> create_account_form());
        edit_profile.setOnClickListener(view -> edit_profile_page());
        logout.setOnClickListener(view -> logout());
    }


    private void create_account_form(){
        Intent intent = new Intent(this, CreateAccountForm.class);
        startActivity(intent);
    }

    private void logout(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void edit_profile_page(){
        Intent intent = new Intent(this, profile_details_page.class);
        startActivity(intent);
    }

}