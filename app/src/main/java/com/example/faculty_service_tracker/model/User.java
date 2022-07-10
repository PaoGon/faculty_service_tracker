package com.example.faculty_service_tracker.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    public static User getUser(JSONObject jsonObject) throws JSONException{
        String status = jsonObject.getString("success");
        String  acc_id = jsonObject.getString("acc_id");
        String email = jsonObject.getString("email");
        Boolean acc_type = jsonObject.getBoolean("is_admin");
        String full_name = jsonObject.getString("full_name");
        String profile_dir = jsonObject.getString("profile_dir");
        String token = jsonObject.getString("token");
        String gender = jsonObject.getString("gender");

        User user = new User(status, acc_id, email, acc_type, full_name, gender, profile_dir, token);

        return user;
    }
    private String status;
    private String acc_id;
    private String email;
    private Boolean acc_type;
    private String full_name;
    private String gender;
    private String profile_dir;
    private String token;

    public User(String status, String acc_id, String email, Boolean acc_type, String full_name, String gender, String profile_dir, String token) {
        this.status = status;
        this.acc_id = acc_id;
        this.email = email;
        this.acc_type = acc_type;
        this.full_name = full_name;
        this.gender = gender;
        this.profile_dir = profile_dir;
        this.token = token;

    }

    public int getAcc_id() { return Integer.parseInt(acc_id); }

    public String getEmail() { return email; }

    public String getStatus() { return status; }

    public String getGender() { return gender; }

    public Boolean getAcc_type() { return acc_type; }

    public String getFull_name() {return full_name;}

    public String getProfile_dir() {return profile_dir;}

    public String getToken() { return token; }

    public void setStatus(String status) { this.status = status; }

    public void setAcc_type(Boolean acc_type) { this.acc_type = acc_type; }

    public void setToken(String token) { this.token = token; }

    //@Override
    //public boolean equals(Object obj){
    //    boolean result = false;

    //    if(obj != null && obj instanceof User){
    //        User that = (User) obj;
    //        if(this.email.equalsIgnoreCase(that.em))
    //    }

    //}
}