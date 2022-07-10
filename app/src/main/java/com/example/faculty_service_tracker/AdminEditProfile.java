package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.faculty_service_tracker.model.Model;

public class AdminEditProfile extends AppCompatActivity {
     RadioButton rd_btn;
     RadioGroup rd_group;
     String full_name, gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_profile);

        Model model = Model.getInstance(AdminEditProfile.this.getApplication());

        int acc_id = model.getUser().getAcc_id();

        rd_group = findViewById(R.id.radio_edit_profile);

        ImageView back_btn = findViewById(R.id.imgView_back_btn);

        final EditText first_name = findViewById(R.id.tvfirstname);
        final EditText last_name = findViewById(R.id.tvSurname);
        final EditText mid_name = findViewById(R.id.tvMidName);
        Button submit = findViewById(R.id.btn_save);

        full_name = first_name.getText().toString();

        submit.setOnClickListener(view -> {
            model.update_info(
                    acc_id,
                    first_name.getText().toString() + " " + mid_name.getText().toString() + " " + last_name.getText().toString(),
                    gender
            );
            finish();
        });

        back_btn.setOnClickListener(view -> finish());

    }

    public void checkBtn(View view) {
        int radio_id = rd_group.getCheckedRadioButtonId();
        rd_btn =findViewById(radio_id);
        String rd_val = rd_btn.getText().toString();
        Toast.makeText(this, "radio: " + rd_val, Toast.LENGTH_SHORT).show();

        if(rd_val.equals("Male")){
            gender =  "M";
        }
        else{
            gender = "F";
        }
    }
}