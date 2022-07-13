package com.example.faculty_service_tracker.model.api;

import com.example.faculty_service_tracker.model.Service;
import com.example.faculty_service_tracker.model.Teacher;
import com.example.faculty_service_tracker.model.User;

import java.util.List;

public class AbstractAPIListener implements APIListener{
    @Override
    public void onlogin(User user) { }

    @Override
    public void onTeachersLoaded(List<Teacher> teachers) { }

    @Override
    public void onServicesLoaded(List<Service> services) { }

    @Override
    public void onServiceCreated(int service_id) { }

}
