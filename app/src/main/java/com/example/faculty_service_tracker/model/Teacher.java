package com.example.faculty_service_tracker.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    String image;
    String full_name;
    String positions;
    int credits;

    //Constructor
    public Teacher(String image, String teacherNames, String positions, int credits) {
        this.image = image;
        this.full_name = teacherNames;
        this.positions = positions;
        this.credits = credits;
    }

    public static Teacher getTeacher(JSONObject jsonObject) throws JSONException{
        String image = jsonObject.getString("profile_dir");
        String full_name = jsonObject.getString("full_name");
        String position = jsonObject.getString("position");
        int total_credits = jsonObject.getInt("total_credits");

        return new Teacher(image, full_name, position, total_credits);
    }

    public static List<Teacher> getTeachers(JSONArray jsonArray) throws JSONException{
        List<Teacher> teachers = new ArrayList<>();
        for(int indx = 0; indx != jsonArray.length(); indx++){
            JSONObject jsonObject = jsonArray.getJSONObject(indx);

            Teacher teacher = Teacher.getTeacher(jsonObject);
            Log.i("obj", teacher.full_name);
            teachers.add(teacher);
        }
        return teachers;
    }

    //Getter
    public String getImage() { return image; }

    public String getTeacherNames() {
        return full_name;
    }

    public String getPositions() {
        return positions;
    }

    public String getCredits() {
        return Integer.toString(credits);
    }
}
