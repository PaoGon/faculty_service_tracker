package com.example.faculty_service_tracker.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    public static User getUser(JSONObject jsonObject) throws JSONException{
        String status = jsonObject.getString("success");
        Boolean acc_type = jsonObject.getBoolean("is_admin");
        String token = jsonObject.getString("token");

        User user = new User(status, acc_type, token);

        return user;
    }
    private String status;
    private Boolean acc_type;
    private String token;

    public User(String status, Boolean acc_type, String token) {
        this.status = status;
        this.acc_type = acc_type;
        this.token = token;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public Boolean getAcc_type() { return acc_type; }

    public void setAcc_type(Boolean acc_type) { this.acc_type = acc_type; }

    public String getToken() { return token; }

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