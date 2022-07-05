package com.example.faculty_service_tracker.model.api;

import com.example.faculty_service_tracker.model.Service;
import com.example.faculty_service_tracker.model.Teacher;
import com.example.faculty_service_tracker.model.User;

import java.util.List;

public interface APIListener {
    void onlogin(User user);
    void onTeachersLoaded(List<Teacher> teachers);
    void onServicesLoaded(List<Service> services);
}
