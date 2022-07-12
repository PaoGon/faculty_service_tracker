package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faculty_service_tracker.model.Model;

public class profile_details_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details_page);

        Model model = Model.getInstance(profile_details_page.this.getApplication());

        ImageView back_btn = findViewById(R.id.imgView_back_btn);
        ImageView profile = findViewById(R.id.imProfile);
        TextView email_add = findViewById(R.id.tv_InputEmail);
        TextView full_name = findViewById(R.id.tvInputName);
        TextView position = findViewById(R.id.tvInputPosition);
        TextView gender = findViewById(R.id.tvInputGender);
        ImageView info_btn = findViewById(R.id.personal_info_btn);
        ImageView update_profile_pic = findViewById(R.id.profile_pic_edit_btn);
        Button change_pass = findViewById(R.id.change_pass);

        GlideApp.with(this)
                .load(model.getUser().getProfile_dir())
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(profile);

        email_add.setText(model.getUser().getEmail());
        full_name.setText(model.getUser().getFull_name());


        if(model.getUser().getAcc_type()) {
            position.setText(R.string.acc_type_admin);
        } else{
            position.setText(model.getUser().getPosition());
        }

        if(model.getUser().getGender().equals("M")){
            gender.setText(R.string.RB_Male);
        } else{
            gender.setText(R.string.RB_Female);
        }

        back_btn.setOnClickListener(view -> finish());
        info_btn.setOnClickListener(view -> check_acc_type(model));
        change_pass.setOnClickListener(view -> chnage_pass());
        update_profile_pic.setOnClickListener(view -> change_profile_pic());
    }



    private void check_acc_type(Model model){
        if(model.getUser().getAcc_type()){
            edit_admin_profile_info();
        }
        else{
            edit_profile_info();
        }
    }

    private void chnage_pass() {
        Intent intent = new Intent(this, ChangePassword.class);
        startActivity(intent);

    }

    private void change_profile_pic(){
        Intent intent = new Intent(this, UpdateProfilePic.class);
        startActivity(intent);
    }

    private void edit_admin_profile_info(){
        Intent intent = new Intent(this, AdminEditProfile.class);
        startActivity(intent);
    }
    private void edit_profile_info(){
        Intent intent = new Intent(this, edit_account_page.class);
        startActivity(intent);
    }

}