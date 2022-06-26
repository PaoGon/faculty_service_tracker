package com.example.faculty_service_tracker.model.api;

import android.app.Application;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.faculty_service_tracker.model.Model;
import com.example.faculty_service_tracker.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class WebAPI implements API{
    public static final String base_url = "http://192.168.254.101/FST-REST_API/";

    private final Application mApplication;
    private RequestQueue requestQue;


    public WebAPI(Application application){
        mApplication = application;
        requestQue = Volley.newRequestQueue(application);
    }

    public void login(String email, String password, final APIListener listener){
        String url = base_url + "accounts/login.php";
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);

            Response.Listener<JSONObject> successListener = response -> {

                try {
                    User user = User.getUser(response);
                    listener.onlogin(user);
                } catch (JSONException e) {
                    Toast.makeText(mApplication, "error", Toast.LENGTH_SHORT).show();
                }
            };

            Response.ErrorListener errorListener = error -> {
                Toast.makeText(mApplication, "error", Toast.LENGTH_SHORT).show();
            };

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, successListener, errorListener);
            requestQue.add(request);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
