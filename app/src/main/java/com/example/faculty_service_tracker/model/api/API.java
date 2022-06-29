package com.example.faculty_service_tracker.model.api;

public interface API {
    void login(String email, String password, APIListener listener);
    void loadTeachers(APIListener listener);
}
