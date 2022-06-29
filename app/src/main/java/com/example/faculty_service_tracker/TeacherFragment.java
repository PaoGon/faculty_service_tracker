package com.example.faculty_service_tracker;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faculty_service_tracker.model.Model;
import com.example.faculty_service_tracker.model.Teacher;
import com.example.faculty_service_tracker.model.api.AbstractAPIListener;

import java.util.Arrays;
import java.util.List;

public class TeacherFragment extends Fragment {

    public static TeacherFragment newInstance() {
        TeacherFragment fragment  = new TeacherFragment();
        return fragment;
    }

    private onFragmentInteractionListener mListener;

    public TeacherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        if(view instanceof RecyclerView){
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            DividerItemDecoration decoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(decoration);

            Application application = this.getActivity().getApplication();
            Model model = Model.getInstance(application);
            final List<Teacher> teachers = model.getTeachers();

            final TeachersAdapter adapter = new TeachersAdapter(this, teachers);
            recyclerView.setAdapter(adapter);

            model.loadTeachers(new AbstractAPIListener(){
                @Override
                public void onTeachersLoaded(List<Teacher> loadedTeachers){
                    teachers.clear();
                    teachers.addAll(loadedTeachers);
                    adapter.notifyDataSetChanged();
                }
            });
            Log.i("gg", Arrays.toString(teachers.toArray()));

        }
    }

    public void onItemSelected(Teacher teacher){
        if(mListener != null){
            mListener.onItemSelected(teacher);
        }
    }


    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof onFragmentInteractionListener){
            mListener = (onFragmentInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString() + " must implement onFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach(){
        super.onDetach();
        mListener = null;
    }

    public interface onFragmentInteractionListener{
        void onItemSelected(Teacher teacher);
    }
}