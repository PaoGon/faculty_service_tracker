package com.example.faculty_service_tracker.model;

import android.app.Application;

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
    }

    public List<Teacher> getTeachers() { return mTeachers; }

    public Application getApplication() {
        return mApplication;
    }

    public void login(String email, String password, APIListener listener){
        api.login(email, password, listener);
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        this.mUser = user;
    }

    public void loadTeachers(APIListener listener) {
        api.loadTeachers(listener);
    }
}
