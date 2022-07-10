package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faculty_service_tracker.model.Model;


public class CreateAccountForm extends AppCompatActivity {
    int type;
    String gender, acc_position;
    String[] items = {"Professor","Instructor","Assistant Professor"};

    RadioButton rd_btn_gender, rd_btn_type;
    RadioGroup rd_group_gender, rd_group_type;

    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_form);

        Model model = Model.getInstance(CreateAccountForm.this.getApplication());

        EditText first_name = findViewById(R.id.tvFName);
        EditText mid_name = findViewById(R.id.tvMidName);
        EditText surname = findViewById(R.id.tvSurname2);

        EditText acc_email = findViewById(R.id.tvEmail2);

        rd_group_gender = findViewById(R.id.rd_gr_gender);
        rd_group_type = findViewById(R.id.rd_gr_type);

        autoCompleteTxt = findViewById(R.id.SelectPosition);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);

        TextView head = findViewById(R.id.tvProfile);
        Button create_acc = findViewById(R.id.btn_CreateAccount);
        ImageView back_btn = findViewById(R.id.imgView_back_btn);

        head.setText(R.string.createAccc);

        autoCompleteTxt.setOnItemClickListener((parent, view, position, id) -> {
            acc_position = parent.getItemAtPosition(position).toString();
        });

        create_acc.setOnClickListener(view -> {
            if(type == 1){acc_position = " ";}
            model.create_acc(
                    first_name.getText().toString() + " " + mid_name.getText().toString() + " " + surname.getText().toString(),
                    acc_email.getText().toString(),
                    surname.getText().toString() + "12345678",
                    acc_position,
                    gender,
                    type
            );
        });
        back_btn.setOnClickListener(view -> finish());
    }


    public void checked_radio_gender(View view) {
        int radio_id = rd_group_gender.getCheckedRadioButtonId();
        rd_btn_gender = findViewById(radio_id);
        String rd_val = rd_btn_gender.getText().toString();
        Toast.makeText(this, "radio: " + rd_val, Toast.LENGTH_SHORT).show();

        if(rd_val.equals("Male")){
            gender =  "M";
        }
        else{
            gender = "F";
        }
    }

    public void checked_radio_type(View view) {
        int radio_id = rd_group_type.getCheckedRadioButtonId();
        rd_btn_type = findViewById(radio_id);
        String rd_val = rd_btn_type.getText().toString();
        Toast.makeText(this, "radio: " + rd_val, Toast.LENGTH_SHORT).show();

        if(rd_val.equals("Admin")){
            type =  1;
        }
        else{
            type = 0;
        }
    }
}