package com.example.faculty_service_tracker.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Service {
    int teacher_id;
    int service_id;
    int credit_point;
    String event_name;
    String starting_date;
    String ending_date;
    String venue;
    String lvl_of_event;
    String sponsor;
    String image;

    public Service(int teacher_id, int service_id, int credit_point, String event_name, String starting_date, String ending_date, String venue, String lvl_of_event, String sponsor, String image) {
        this.teacher_id = teacher_id;
        this.service_id = service_id;
        this.credit_point = credit_point;
        this.event_name = event_name;
        this.starting_date = starting_date;
        this.ending_date = ending_date;
        this.venue = venue;
        this.lvl_of_event = lvl_of_event;
        this.sponsor = sponsor;
        this.image = image;
    }

    public static Service getService(JSONObject jsonObject) throws JSONException {
        int teacher_id = jsonObject.getInt("teacher_id");
        int service_id = jsonObject.getInt("service_id");
        int credit_point = jsonObject.getInt("credit_point");
        String event_name = jsonObject.getString("event_name");
        String starting_date = jsonObject.getString("starting_date");
        String ending_date = jsonObject.getString("ending_date");
        String venue = jsonObject.getString("venue");
        String lvl_of_event = jsonObject.getString("level_of_event");
        String sponsor = jsonObject.getString("sponsor");
        String image = jsonObject.getString("service_dir");

        return new Service(teacher_id, service_id, credit_point, event_name, starting_date, ending_date, venue, lvl_of_event, sponsor, image);
    }

    public static List<Service> getServices(JSONArray jsonArray) throws JSONException{
        List<Service> services = new ArrayList<>();

        for(int indx = 0; indx != jsonArray.length(); indx++){
            JSONObject jsonObject = jsonArray.getJSONObject(indx);

            Service service = Service.getService(jsonObject);
            services.add(service);
        }
        return services;
    }

    @Override
    public String toString() {
        return "Service{" +
                "teacher_id=" + teacher_id +
                ", service_id=" + service_id +
                ", event_name='" + event_name + '\'' +
                ", starting_date='" + starting_date + '\'' +
                ", ending_date='" + ending_date + '\'' +
                ", venue='" + venue + '\'' +
                ", lvl_of_event='" + lvl_of_event + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public int getTeacher_id() { return teacher_id; }

    public int getCredit_point(){ return credit_point; };

    public int getService_id() { return service_id; }

    public String getEvent_name() { return event_name; }

    public String getStarting_date() { return starting_date; }

    public String getEnding_date() { return ending_date; }

    public String getVenue() { return venue; }

    public String getLvl_of_event() { return lvl_of_event; }

    public String getSponsor() { return sponsor; }

    public String getImage() { return image; }
}
