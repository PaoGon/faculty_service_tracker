package com.example.faculty_service_tracker.model.api;

import android.app.Application;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.faculty_service_tracker.model.Service;
import com.example.faculty_service_tracker.model.Teacher;
import com.example.faculty_service_tracker.model.Model;
import com.example.faculty_service_tracker.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebAPI implements API{
    public static final String base_url = "http://192.168.254.101/FST-REST_API/";

    private final Application mApplication;
    private RequestQueue requestQue;
    private Model mModel;


    public WebAPI(Application application, Model model){
        mApplication = application;
        requestQue = Volley.newRequestQueue(application);
        mModel = model;
    }

    public void login(String email, String password, final APIListener listener){
        String end_point = base_url + "accounts/login.php";
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

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, end_point, jsonObject, successListener, errorListener);
            requestQue.add(request);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadTeachers(final APIListener listener) {
        String end_point = base_url + "controllers/admin/get_accounts.php";

        Response.Listener<JSONArray> successListener = response -> {
            try{
                List<Teacher> teachers = Teacher.getTeachers(response);
                if(listener != null){
                    listener.onTeachersLoaded(teachers);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        Response.ErrorListener errorListener = error -> {
            Toast.makeText(mApplication, "Server Error" + error, Toast.LENGTH_SHORT).show();
        };

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, end_point, null, successListener, errorListener){
            @Override
            public Map<String, String> getHeaders(){
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=UTF-8");
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer " + mModel.getUser().getToken());
                return headers;
            }
        };
        requestQue.add(request);
    }

    @Override
    public void loadServices(final APIListener listener, int teacher_id) {
        String end_point = base_url + "controllers/services/get_service.php?teacher_id=" + teacher_id ;

        Response.Listener<JSONArray> successListener = response -> {
            try{
                List<Service> services = Service.getServices(response);
                if(listener != null){
                    listener.onServicesLoaded(services);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        Response.ErrorListener errorListener = error -> {
            Toast.makeText(mApplication, "Server Error" + error, Toast.LENGTH_SHORT).show();
        };

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, end_point, null, successListener, errorListener){
            @Override
            public Map<String, String> getHeaders(){
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=UTF-8");
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer " + mModel.getUser().getToken());
                return headers;
            }
        };
        requestQue.add(request);
    }
}
