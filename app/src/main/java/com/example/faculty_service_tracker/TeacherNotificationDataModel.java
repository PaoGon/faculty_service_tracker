package com.example.faculty_service_tracker;

public class TeacherNotificationDataModel {
    int image;
    String teacherName;


    public TeacherNotificationDataModel(int image, String teacherName) {
        this.image = image;
        this.teacherName = teacherName;
    }

    public int getImage() {
        return image;
    }

    public String getTeacherName() {
        return teacherName;
    }
}
