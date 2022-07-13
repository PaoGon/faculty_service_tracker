package com.example.faculty_service_tracker.create_service_form;

public class EventDetailsForm {

    String event_name;
    String venue;
    String sponsor;
    String event_type;
    String starting_date;
    String ending_date;

    public EventDetailsForm(String event_name, String venue, String sponsor, String event_type, String starting_date, String ending_date) {
        this.event_name = event_name;
        this.venue = venue;
        this.sponsor = sponsor;
        this.event_type = event_type;
        this.starting_date = starting_date;
        this.ending_date = ending_date;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getSponsor() {
        return sponsor;
    }

    public String getEnding_date() {
        return ending_date;
    }

    public String getVenue() {
        return venue;
    }

    public String getEvent_type() {
        return event_type;
    }

    public String getStarting_date() {
        return starting_date;
    }

    @Override
    public String toString() {
        return "EventDetailsForm{" +
                "event_name='" + event_name + '\'' +
                ", venue='" + venue + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", event_type='" + event_type + '\'' +
                ", starting_date='" + starting_date + '\'' +
                ", ending_date='" + ending_date + '\'' +
                '}';
    }
}