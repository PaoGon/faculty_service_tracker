package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faculty_service_tracker.model.Model;

public class ChangePassword extends AppCompatActivity {
    EditText old_pass, new_pass, re_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_page);

        Model model = Model.getInstance(ChangePassword.this.getApplication());

        ImageView back_btn = findViewById(R.id.imgView_back_btn);
        TextView head = findViewById(R.id.tvEditProfile);
        old_pass = findViewById(R.id.tvOldPassword);
        new_pass = findViewById(R.id.tv_NewPassword);
        re_pass = findViewById(R.id.re_pass);
        Button submit = findViewById(R.id.change_pass_btn);

        head.setText(R.string.chang_pass);

        submit.setOnClickListener(view -> change_pass(model));
        back_btn.setOnClickListener(view -> finish());
    }

    private void change_pass(Model model){
        if(new_pass.getText().toString().equals(re_pass.getText().toString())){
            //send to REST api

            model.change_pass(
                    model.getUser().getAcc_id(),
                    old_pass.getText().toString(),
                    new_pass.getText().toString()
            );
            finish();
        }
        else{
            Toast.makeText(this, "New Password Does Not Match", Toast.LENGTH_SHORT).show();
        }

    }
}