package com.example.faculty_service_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeacherNotifPage_RecyclerViewAdapter extends RecyclerView.Adapter<TeacherNotifPage_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<TeacherNotificationDataModel> teacherNotificationDataModels;

    public TeacherNotifPage_RecyclerViewAdapter(Context context, ArrayList<TeacherNotificationDataModel> teacherNotificationDataModels){
        this.context = context;
        this.teacherNotificationDataModels = teacherNotificationDataModels;
    }

    @NonNull
    @Override
    public TeacherNotifPage_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where you inflate the layout (Giving a look to our item_recycler_teacher_notif_page)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recycler_teacher_notif_page, parent, false);

        return new TeacherNotifPage_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherNotifPage_RecyclerViewAdapter.MyViewHolder holder, int position) {
        // assigning values to the views we created in the item_recycler_teacher_notif_page layout file
        // based on the position of the recycler view
        holder.tvName.setText(teacherNotificationDataModels.get(position).getTeacherName());
        holder.imageView.setImageResource(teacherNotificationDataModels.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        // the recycler view number of items you want to displayed
        return teacherNotificationDataModels.size();
    }

    // inner class created
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // grabbing the views from our item_recycler_teacher_notif_page layout file
        // looks like onCreate method

        ImageView imageView;
        TextView tvName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgView_notif);
            tvName = itemView.findViewById(R.id.txtName);

        }
    }
}
