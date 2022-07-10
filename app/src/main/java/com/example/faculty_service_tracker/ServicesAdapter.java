package com.example.faculty_service_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faculty_service_tracker.model.Service;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.MyViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public final View mView;
        public final ImageView service_pic;
        public final TextView date;
        public final TextView event_name;
        public final TextView venue;
        public Service mServices;

        public MyViewHolder(@NonNull View view) {
            super(view);
            mView = view;
            service_pic = view.findViewById(R.id.service_pic);
            date = view.findViewById(R.id.serv_date);
            event_name = view.findViewById(R.id.serv_event);
            venue = view.findViewById(R.id.serv_venue);
        }
    }

    private final ServiceFragment mFragment;
    private final List<Service> mServices;

    //Create adapter for ServicesAdapter class
    public ServicesAdapter(ServiceFragment serviceFragment, List<Service> services){
        this.mFragment = serviceFragment;
        this.mServices = services;
    }

    @NonNull
    @Override
    public ServicesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recycler_services, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Service service = mServices.get(position);

        GlideApp.with(this.mFragment)
                .load(service.getImage())
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(holder.service_pic);

        holder.date.setText(service.getStarting_date());
        holder.event_name.setText(service.getEvent_name());
        holder.venue.setText(service.getVenue());

        holder.mView.setOnClickListener(view -> mFragment.onItemSelected(service));
    }

    @Override
    public int getItemCount() { return mServices.size(); }
}
