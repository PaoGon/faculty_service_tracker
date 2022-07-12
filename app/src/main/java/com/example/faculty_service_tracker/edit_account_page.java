package com.example.faculty_service_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.faculty_service_tracker.model.Model;

public class edit_account_page extends AppCompatActivity {
    String[] items = {"Professor","Instructor","Assistant Professor"};
    String acc_position, full_name, gender;

    RadioButton rd_btn;
    RadioGroup rd_group;
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_page);

        Model model = Model.getInstance(edit_account_page.this.getApplication());

        int acc_id = model.getUser().getAcc_id();

        rd_group = findViewById(R.id.radioGroup);

        ImageView back_btn = findViewById(R.id.imgView_back_btn);

        EditText first_name = findViewById(R.id.tvfirstname);
        EditText last_name = findViewById(R.id.tvSurname);
        EditText mid_name = findViewById(R.id.tvMidName);
        Button submit = findViewById(R.id.btn_save);

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener((parent, view, position, id) -> {
            acc_position = parent.getItemAtPosition(position).toString();
        });

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