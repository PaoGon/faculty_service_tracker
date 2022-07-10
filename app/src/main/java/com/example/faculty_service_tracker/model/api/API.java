package com.example.faculty_service_tracker.model.api;

import android.widget.ImageView;

public interface API {
    void login(String email, String password, APIListener listener);
    void loadTeachers(APIListener listener);
    void loadServices(APIListener listener, int teacher_id);
    void update_info(int acc_id, String full_name, String gender);
    void change_pass(int acc_id, String old_pass, String new_pass);
    void upload_profile(int acc_id, ImageView img);
    void create_acc(String full_name, String email, String pass, String position, String gender, int type);
}
