package com.example.faculty_service_tracker;

public class TeacherHomeDataModel {
    int image;
    String teacherNames;
    String positions;
    String credits;

    //Constructor
    public TeacherHomeDataModel(int image, String teacherNames, String positions, String credits) {
        this.image = image;
        this.teacherNames = teacherNames;
        this.positions = positions;
        this.credits = credits;
    }

    //Getter
    public int getImage() {
        return image;
    }

    public String getTeacherNames() {
        return teacherNames;
    }

    public String getPositions() {
        return positions;
    }

    public String getCredits() {
        return credits;
    }
}
