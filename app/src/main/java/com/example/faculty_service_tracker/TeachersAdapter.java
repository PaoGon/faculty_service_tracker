package com.example.faculty_service_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faculty_service_tracker.model.Teacher;

import java.util.List;

public class TeachersAdapter extends RecyclerView.Adapter<TeachersAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final ImageView profile;
        public final TextView fullName;
        public final TextView position;
        public final TextView creditPoints;
        public Teacher mTeachers;

        public MyViewHolder(@NonNull View view) {
            super(view);
            mView = view;
            profile = view.findViewById(R.id.imageView3);
            fullName = view.findViewById(R.id.textView3);
            position = view.findViewById(R.id.textView5);
            creditPoints = view.findViewById(R.id.textView7);
        }
    }

    private final TeacherFragment mFragment;
    private final List<Teacher> mTeachers;

    public TeachersAdapter(TeacherFragment teacherFragment, List<Teacher> teachers){
        this.mFragment = teacherFragment;
        this.mTeachers = teachers;
    }

    @NonNull
    @Override
    public TeachersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recycler_teacher_home, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Teacher teacher = mTeachers.get(position);

        GlideApp.with(this.mFragment)
                .load(teacher.getImage())
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(holder.profile);

        holder.fullName.setText(teacher.getTeacherNames());
        holder.position.setText(teacher.getPositions());
        holder.creditPoints.setText(teacher.getCredits());

        holder.mView.setOnClickListener(view -> mFragment.onItemSelected(teacher));
    }

    @Override
    public int getItemCount() {
        return mTeachers.size();
    }

}
