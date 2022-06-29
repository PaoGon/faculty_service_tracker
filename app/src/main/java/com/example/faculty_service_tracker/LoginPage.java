package com.example.faculty_service_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.faculty_service_tracker.model.Model;
import com.example.faculty_service_tracker.model.User;
import com.example.faculty_service_tracker.model.api.AbstractAPIListener;

public class LoginPage extends AppCompatActivity {

    /*LOGIN PAGE!*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        final EditText email_field = findViewById(R.id.user_email);
        final EditText pass_field = findViewById(R.id.user_pass);

        Button login_btn = findViewById(R.id.btn_login);
        login_btn.setOnClickListener(view -> usr_log(email_field, pass_field));
    }

    private void usr_log(EditText email, EditText pass){
        String usr_email = email.getText().toString();
        String usr_pass = pass.getText().toString();

        final Model model = Model.getInstance(LoginPage.this.getApplication());
        model.login(usr_email, usr_pass, new AbstractAPIListener() {
            @Override
            public void onlogin(User user){
                if(user != null){
                    model.setUser(user);
                    if(model.getUser().getAcc_type()){
                        adminPage();
                    }
                    else{
                        teacherPage();
                    }
                }
                else{
                    Toast.makeText(LoginPage.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        /*button back to landing page2*/
    private void teacherPage(){
        Intent intent = new Intent(LoginPage.this, TeacherHomePage.class);
        startActivity(intent);

    }

    private void adminPage() {
        Intent intent = new Intent(LoginPage.this, AdminHomePage.class);
        startActivity(intent);
    }


}