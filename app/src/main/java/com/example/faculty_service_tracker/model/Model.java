package com.example.faculty_service_tracker.model;

import android.app.Application;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.faculty_service_tracker.model.api.API;
import com.example.faculty_service_tracker.model.api.APIListener;
import com.example.faculty_service_tracker.model.api.WebAPI;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final Application mApplication;
    private static Model sInstance = null;
    private final API api;
    private User mUser;
    private List<Teacher> mTeachers;
    private List<Service> mServices;

    public static Model getInstance(Application application){
        if(sInstance == null){
            sInstance = new Model(application);
        }
        return sInstance;
    }

    //constructor
    private Model(Application application) {
        mApplication = application;
        api = new WebAPI(mApplication, this);
        mTeachers = new ArrayList<>();
        mServices = new ArrayList<>();
    }

    public List<Teacher> getTeachers() { return mTeachers; }
    public List<Service> getServices() { return mServices; }
    public Application getApplication() {
        return mApplication;
    }

    public void login(String email, String password, APIListener listener){
        api.login(email, password, listener);
    }

    public void update_info(int acc_id, String full_name, String gender){
        api.update_info(acc_id, full_name, gender);
    }

    public void change_pass(int acc_id, String old_pass, String new_pass){
        api.change_pass(acc_id, old_pass, new_pass);
    }

    public void create_acc(String full_name, String email, String pass, String position, String gender, int type){
        api.create_acc(full_name, email, pass, position, gender, type);
    }

    public void confirm_service(int teacher_id, int service_id, int total_credits, String starting_date, String ending_date){
        api.confirm_service(teacher_id, service_id, total_credits,starting_date, ending_date);
    }

    public void upload_profile(int acc_id, ImageView img){
        api.upload_profile(acc_id, img);
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        this.mUser = user;
    }

    public void loadTeachers(APIListener listener) { api.loadTeachers(listener); }

    public void loadServices(APIListener  listener, int teacher_id){ api.loadServices(listener, teacher_id);}
}
