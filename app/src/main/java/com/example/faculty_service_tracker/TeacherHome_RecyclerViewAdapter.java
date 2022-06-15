package com.example.faculty_service_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeacherHome_RecyclerViewAdapter extends RecyclerView.Adapter<TeacherHome_RecyclerViewAdapter.MyViewHolder> {
    private final SelectListener selectListener;

    Context context;
    ArrayList<TeacherHomeDataModel> teacherHomeDataModels;

    public TeacherHome_RecyclerViewAdapter(Context context, ArrayList<TeacherHomeDataModel> teacherHomeDataModels,
                                           SelectListener selectListener){
        this.context = context;
        this.teacherHomeDataModels = teacherHomeDataModels;
        this.selectListener = selectListener;

    }

    // This is where you inflate the layout (Giving a look to our item_recycler_teacher_home)
    @NonNull
    @Override
    public TeacherHome_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recycler_teacher_home, parent, false);

        return new TeacherHome_RecyclerViewAdapter.MyViewHolder(view, selectListener);
    }

    // assigning values to the views we created in the item_recycler_teacher_home layout file
    // based on the position of the recycler view
    @Override
    public void onBindViewHolder(@NonNull TeacherHome_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.imageView.setImageResource(teacherHomeDataModels.get(position).getImage());
        holder.tvName.setText(teacherHomeDataModels.get(position).getTeacherNames());
        holder.tvPosition.setText(teacherHomeDataModels.get(position).getPositions());
        holder.tvCredit.setText(teacherHomeDataModels.get(position).getCredits());


    }

    // the recycler view just wants to know the number of items you want displayed
    @Override
    public int getItemCount() {
        return teacherHomeDataModels.size();
    }
    //inner class we created and grabbing the views from our item_recycler_teacher_home layout file and assigning them to variables
    // seems like onCreate method
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvName, tvPosition, tvCredit;

        public MyViewHolder(@NonNull View itemView, SelectListener selectListener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView3);
            tvName = itemView.findViewById(R.id.textView3);
            tvPosition = itemView.findViewById(R.id.textView5);
            tvCredit = itemView.findViewById(R.id.textView7);


            //Click Listener coming form SelectListener.java
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selectListener != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            selectListener.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
