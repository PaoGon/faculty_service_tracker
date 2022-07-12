package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faculty_service_tracker.model.Model;

public class ProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Model model = Model.getInstance(ProfilePage.this.getApplication());

        ImageView back = findViewById(R.id.imgView_back_btn);
        ImageView profile_pic = findViewById(R.id.imProfile);
        TextView name = findViewById(R.id.tvProfileName);
        Button edit_profile = findViewById(R.id.btn_EditProfile);
        Button logout = findViewById(R.id.btn_LogOut);

        name.setText(model.getUser().getFull_name());

        GlideApp.with(this)
                .load(model.getUser().getProfile_dir())
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(profile_pic);

        back.setOnClickListener(view -> finish());
        logout.setOnClickListener(view -> logout());
        edit_profile.setOnClickListener(view -> edit_profile_page());
    }

    private void logout(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void edit_profile_page(){
        Log.i("profile_details", "hello");
        Intent intent = new Intent(this, profile_details_page.class);
        startActivity(intent);

    }
}