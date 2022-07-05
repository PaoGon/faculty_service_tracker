package com.example.faculty_service_tracker;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faculty_service_tracker.model.Model;
import com.example.faculty_service_tracker.model.Service;
import com.example.faculty_service_tracker.model.Teacher;
import com.example.faculty_service_tracker.model.api.AbstractAPIListener;

import java.util.List;

public class ServiceFragment extends Fragment {
    private TextView txtTest;

    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mParam1;

    public ServiceFragment() {
        // Required empty public constructor
    }

    private ServiceFragment.onFragmentInteractionListener mListener;

    public static ServiceFragment newInstance(int param1) {
        ServiceFragment fragment = new ServiceFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        if(view instanceof RecyclerView){
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            //DividerItemDecoration decoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
            //recyclerView.addItemDecoration(decoration);

            Application application = this.getActivity().getApplication();
            Model model = Model.getInstance(application);
            final List<Service> services = model.getServices();

            final ServicesAdapter adapter = new ServicesAdapter(this, services);
            recyclerView.setAdapter(adapter);

            model.loadServices(new AbstractAPIListener(){
                @Override
                public void onServicesLoaded(List<Service> loadedServices){
                    services.clear();
                    services.addAll(loadedServices);
                    adapter.notifyDataSetChanged();
                }
            }, mParam1);
        }
    }


    public void onItemSelected(Service service){
        if(mListener != null){
            mListener.onItemSelected(service);
        }
    }

    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof ServiceFragment.onFragmentInteractionListener){
            mListener = (ServiceFragment.onFragmentInteractionListener) context;
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
        void onItemSelected(Service service);
    }
}