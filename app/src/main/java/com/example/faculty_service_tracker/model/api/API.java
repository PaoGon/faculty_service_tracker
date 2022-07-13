package com.example.faculty_service_tracker.model.api;

import android.widget.ImageView;

import com.example.faculty_service_tracker.create_service_form.EventDetailsForm;

public interface API {
    void login(String email, String password, APIListener listener);
    void loadTeachers(APIListener listener);
    void loadServices(APIListener listener, int teacher_id);
    void update_info(int acc_id, String full_name, String gender);
    void change_pass(int acc_id, String old_pass, String new_pass);
    void upload_profile(int acc_id, ImageView img);
    void create_acc(String full_name, String email, String pass, String position, String gender, int type);
    void create_service(int teacher_id, EventDetailsForm service_info, APIListener listener);
    void upload_service_pic(ImageView img, int acc_id, int service_id);
    void confirm_service(int teacher_id, int service_id, int total_credits, String starting_date, String ending_date);
}
