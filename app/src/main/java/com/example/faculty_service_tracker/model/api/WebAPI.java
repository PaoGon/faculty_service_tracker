package com.example.faculty_service_tracker.model.api;

import java.sql.Timestamp;

import android.app.Application;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.faculty_service_tracker.create_service_form.EventDetailsForm;
import com.example.faculty_service_tracker.model.AppHelper;
import com.example.faculty_service_tracker.model.Service;
import com.example.faculty_service_tracker.model.Teacher;
import com.example.faculty_service_tracker.model.Model;
import com.example.faculty_service_tracker.model.User;
import com.example.faculty_service_tracker.model.VolleyMultipartRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebAPI implements API{
//    public static final String base_url = "https://obscure-falls-02126.herokuapp.com/";
    public static final String base_url = "http://192.168.254.101/FST-REST_API/";

    private final Application mApplication;
    private RequestQueue requestQue;
    private Model mModel;

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public WebAPI(Application application, Model model){
        mApplication = application;
        requestQue = Volley.newRequestQueue(application);
        mModel = model;
    }

    //upload service pic
    public void upload_service_pic(ImageView img, int acc_id, int service_id){
        String url = base_url + "controllers/services/upload_service_pic.php";

        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                String resultResponse = new String(response.data);
                try {
                    JSONObject result = new JSONObject(resultResponse);
                    String status = result.getString("status");
                    String message = result.getString("message");
                    Toast.makeText(mApplication,  message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                String errorMessage = "Unknown error";
                if (networkResponse == null) {
                    if (error.getClass().equals(TimeoutError.class)) {
                        errorMessage = "Request timeout";
                    } else if (error.getClass().equals(NoConnectionError.class)) {
                        errorMessage = "Failed to connect server";
                    }
                } else {
                    String result = new String(networkResponse.data);
                    try {
                        JSONObject response = new JSONObject(result);
                        String status = response.getString("status");
                        String message = response.getString("message");
                        String type = response.getString("type");

                        Log.e("API_Error Status", status);
                        Log.e("API_Error type", type);
                        Log.e("API_Error Message", message);

                        if (networkResponse.statusCode == 404) {
                            errorMessage = "Resource not found";
                        } else if (networkResponse.statusCode == 401) {
                            errorMessage = message+" Please login again";
                        } else if (networkResponse.statusCode == 400) {
                            errorMessage = message+ " Check your inputs";
                        } else if (networkResponse.statusCode == 500) {
                            errorMessage = message+" Something is getting wrong";
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("Error", errorMessage);
                error.printStackTrace();
            }
        }) {

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                params.put("file", new DataPart(timestamp.toString()+".jpg", AppHelper.getFileDataFromDrawable(mApplication, img.getDrawable()), "image/jpeg"));
                Log.i("time", timestamp.toString()+"jpg");
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("acc_id", Integer.toString(acc_id));
                params.put("service_id", Integer.toString(service_id));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + mModel.getUser().getToken());
                return headers;
            }

        };

        requestQue.add(multipartRequest);

    }

    //upload profile picture
    public void upload_profile(int acc_id, ImageView img){
        String url = base_url + "controllers/upload_profile.php";
        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                String resultResponse = new String(response.data);
                try {
                    JSONObject result = new JSONObject(resultResponse);
                    String status = result.getString("status");
                    String message = result.getString("message");
                    Toast.makeText(mApplication,  message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                String errorMessage = "Unknown error";
                if (networkResponse == null) {
                    if (error.getClass().equals(TimeoutError.class)) {
                        errorMessage = "Request timeout";
                    } else if (error.getClass().equals(NoConnectionError.class)) {
                        errorMessage = "Failed to connect server";
                    }
                } else {
                    String result = new String(networkResponse.data);
                    try {
                        JSONObject response = new JSONObject(result);
                        String status = response.getString("status");
                        String message = response.getString("message");
                        String type = response.getString("type");

                        Log.e("API_Error Status", status);
                        Log.e("API_Error type", type);
                        Log.e("API_Error Message", message);

                        if (networkResponse.statusCode == 404) {
                            errorMessage = "Resource not found";
                        } else if (networkResponse.statusCode == 401) {
                            errorMessage = message+" Please login again";
                        } else if (networkResponse.statusCode == 400) {
                            errorMessage = message+ " Check your inputs";
                        } else if (networkResponse.statusCode == 500) {
                            errorMessage = message+" Something is getting wrong";
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("Error", errorMessage);
                error.printStackTrace();
            }
        }) {

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                params.put("file", new DataPart(timestamp.toString()+".jpg", AppHelper.getFileDataFromDrawable(mApplication, img.getDrawable()), "image/jpeg"));
                Log.i("time", timestamp.toString()+"jpg");
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("acc_id", Integer.toString(acc_id));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + mModel.getUser().getToken());
                return headers;
            }

        };

        requestQue.add(multipartRequest);

    }

    //create new account
    public void create_acc(String full_name, String email, String pass, String position, String gender, int type){
        String end_point = base_url + "accounts/signup.php";
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("full_name", full_name);
            jsonObject.put("email", email);
            jsonObject.put("password", pass);
            jsonObject.put("position", position);
            jsonObject.put("gender", gender);
            jsonObject.put("is_admin", type);

            Response.Listener<JSONObject> successListener = response -> {
                try {

                    String status = response.getString("status");
                    String message = response.getString("message");

                    Toast.makeText(mApplication,  message, Toast.LENGTH_SHORT).show();
                    Log.i("API_stat", status);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            };

            Response.ErrorListener errorListener = error -> {
                NetworkResponse networkResponse = error.networkResponse;
                String result = new String(networkResponse.data);
                try {
                    JSONObject response = new JSONObject(result);
                    String status = response.getString("status");
                    String message = response.getString("message");

                    Toast.makeText(mApplication, message, Toast.LENGTH_SHORT).show();
                    Log.e("API_stat", status);
                    Log.e("API_msg", message);

                    Toast.makeText(mApplication, message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(mApplication, "something went wrong", Toast.LENGTH_SHORT).show();
            };

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, end_point, jsonObject, successListener, errorListener){

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
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void confirm_service(int teacher_id, int service_id, int total_credits, String starting_date, String ending_date) {
        String end_point = base_url + "controllers/admin/update_credit.php";
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("teacher_id", teacher_id);
            jsonObject.put("service_id", service_id);
            jsonObject.put("total_credits", total_credits);
            jsonObject.put("starting_date", starting_date);
            jsonObject.put("ending_date", ending_date);

            Response.Listener<JSONObject> successListener = response -> {
                try {
                    String status = response.getString("status");
                    String message = response.getString("message");

                    Toast.makeText(mApplication,  message, Toast.LENGTH_SHORT).show();
                    Log.i("API_stat", status);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            };

            Response.ErrorListener errorListener = error -> {
                NetworkResponse networkResponse = error.networkResponse;
                String result = new String(networkResponse.data);
                try {
                    JSONObject response = new JSONObject(result);
                    String status = response.getString("status");
                    String message = response.getString("message");

                    Toast.makeText(mApplication, message, Toast.LENGTH_SHORT).show();
                    Log.e("API_stat", status);
                    Log.e("API_msg", message);

                    Toast.makeText(mApplication, message, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            };

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, end_point, jsonObject, successListener, errorListener){

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

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //change password
    public void change_pass(int acc_id, String old_pass, String new_pass){
        String end_point = base_url + "controllers/change_pass.php";
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("acc_id", acc_id);
            jsonObject.put("old_pass", old_pass);
            jsonObject.put("new_pass", new_pass);

            Response.Listener<JSONObject> successListener = response -> {
                try {
                    Toast.makeText(mApplication, response.getString("message") , Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            };

            Response.ErrorListener errorListener = error -> {
                NetworkResponse networkResponse = error.networkResponse;
                String result = new String(networkResponse.data);
                try {
                    JSONObject response = new JSONObject(result);
                    String status = response.getString("status");
                    String message = response.getString("message");

                    Toast.makeText(mApplication, message, Toast.LENGTH_SHORT).show();
                    Log.e("API_stat", status);
                    Log.e("API_msg", message);

                    Toast.makeText(mApplication, message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            };

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, end_point, jsonObject, successListener, errorListener){

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
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    //update basic account information
    public void update_info(int acc_id, String full_name, String gender){
        String end_point = base_url + "controllers/update_info.php";
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("acc_id", acc_id);
            jsonObject.put("full_name", full_name);
            jsonObject.put("gender", gender);

            Response.Listener<JSONObject> successListener = response -> {
                try {
                    Toast.makeText(mApplication, response.getString("message") , Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            };

            Response.ErrorListener errorListener = error -> {
                NetworkResponse networkResponse = error.networkResponse;
                String result = new String(networkResponse.data);
                try {
                    JSONObject response = new JSONObject(result);
                    String status = response.getString("status");
                    String message = response.getString("message");

                    Toast.makeText(mApplication, message, Toast.LENGTH_SHORT).show();
                    Log.e("API_stat", status);
                    Log.e("API_msg", message);

                    Toast.makeText(mApplication, message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            };

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, end_point, jsonObject, successListener, errorListener){

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError{
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json; charset=UTF-8");
                    headers.put("Accept", "application/json");
                    headers.put("Authorization", "Bearer " + mModel.getUser().getToken());
                    return headers;
                }
            };
            requestQue.add(request);
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    //create  service
    public void create_service(int teacher_id, EventDetailsForm service_info, APIListener listener){
        String end_point = base_url + "controllers/services/create_services.php";
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("teacher_id", teacher_id);
            jsonObject.put("event_name", service_info.getEvent_name());
            jsonObject.put("starting_date", service_info.getStarting_date());
            jsonObject.put("ending_date", service_info.getEnding_date());
            jsonObject.put("level_of_event", service_info.getEvent_type());
            jsonObject.put("sponsor", service_info.getSponsor());
            jsonObject.put("credit_point", 0);
            jsonObject.put("venue", service_info.getVenue());

            Response.Listener<JSONObject> successListener = response -> {
                try {
                    Toast.makeText(mApplication, response.getString("message") , Toast.LENGTH_SHORT).show();
                    int service_id = response.getInt("service_id");
                    Log.e("service_id", " "+service_id);
                    listener.onServiceCreated(service_id);
                    //serviceViewModel.setService_id(service_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            };

            Response.ErrorListener errorListener = error -> {
                NetworkResponse networkResponse = error.networkResponse;
                String result = new String(networkResponse.data);
                try {
                    JSONObject response = new JSONObject(result);
                    String status = response.getString("status");
                    String message = response.getString("message");

                    Log.e("API_stat", status);
                    Log.e("API_msg", message);
                    Toast.makeText(mApplication, message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            };

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, end_point, jsonObject, successListener, errorListener){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError{
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json; charset=UTF-8");
                    headers.put("Accept", "application/json");
                    headers.put("Authorization", "Bearer " + mModel.getUser().getToken());
                    return headers;
                }
            };
            requestQue.add(request);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    //login
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
                NetworkResponse networkResponse = error.networkResponse;
                String result = new String(networkResponse.data);
                try {
                    JSONObject response = new JSONObject(result);
                    String status = response.getString("status");
                    String message = response.getString("message");

                    Log.e("API_stat", status);
                    Log.e("API_msg", message);
                    Toast.makeText(mApplication, message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
            NetworkResponse networkResponse = error.networkResponse;
            String result = new String(networkResponse.data);
            try {
                JSONObject response = new JSONObject(result);
                String status = response.getString("status");
                String message = response.getString("message");

                Toast.makeText(mApplication, message, Toast.LENGTH_SHORT).show();
                Log.e("API_stat", status);
                Log.e("API_msg", message);

                Toast.makeText(mApplication, message, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
        String end_point = base_url + "controllers/services/get_service.php?teacher_id=" + teacher_id;

        Response.Listener<JSONArray> successListener = response -> {
            try {
                if(response.length() > 0){
                    List<Service> services = Service.getServices(response);

                    Log.d("service_k", " "+response.length());

                    if (listener != null) {
                        listener.onServicesLoaded(services);
                    }
                } else{
                    Log.d("service_", "test");
                    Toast.makeText(mApplication, "errror", Toast.LENGTH_SHORT).show();

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        Response.ErrorListener errorListener = error -> {
            NetworkResponse networkResponse = error.networkResponse;
            String result = new String(networkResponse.data);
            try {
                JSONObject response = new JSONObject(result);
                String status = response.getString("status");
                String message = response.getString("message");

                List<Service> services = Collections.<Service>emptyList();


                Toast.makeText(mApplication, message, Toast.LENGTH_SHORT).show();
                Log.e("API_stat", status);
                Log.e("API_msg", message);

                if (listener != null) {
                    listener.onServicesLoaded(services);
                }
                Toast.makeText(mApplication, message, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        };

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, end_point, null, successListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() {
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
