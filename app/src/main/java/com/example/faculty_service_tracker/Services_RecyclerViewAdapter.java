package com.example.faculty_service_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Services_RecyclerViewAdapter extends RecyclerView.Adapter<Services_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<ServicesDataModel> servicesDataModels;

    //Create adapter for Services_RecyclerViewAdapter class
    public Services_RecyclerViewAdapter(Context context, ArrayList<ServicesDataModel> servicesDataModel){
        this.context = context;
        this.servicesDataModels = servicesDataModel;

    }

    @NonNull
    @Override
    public Services_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This where you inflate the layout (Giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recycler_services, parent, false);
        return new Services_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Services_RecyclerViewAdapter.MyViewHolder holder, int position) {
        // assigning values to the views we created in the item_recycler_services layout file
        // based on the position of the recycler view
        holder.imageView.setImageResource(servicesDataModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        // the recycler view just wants to know the number of items you wanted displayed
        return servicesDataModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // grabbing the views from our item_recycler_services layout file
        // looks similar to onCreate method

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgView_service);
        }
    }
}
