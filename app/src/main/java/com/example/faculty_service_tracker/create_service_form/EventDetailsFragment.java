package com.example.faculty_service_tracker.create_service_form;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.faculty_service_tracker.R;

import java.util.Calendar;


public class EventDetailsFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private int counter = 0;

    FormViewModel formViewModel;
    TextView eventName, venue, sponsor, startDateText, endDateText;
    RadioGroup radioGroup;
    RadioButton radioButton;

    public EventDetailsFragment() {
        // Required empty public constructor
    }

    public static EventDetailsFragment newInstance() {
        EventDetailsFragment fragment = new EventDetailsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_details, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        formViewModel = new ViewModelProvider(requireActivity()).get(FormViewModel.class);

        ImageView startButton = view.findViewById(R.id.calendar_start);

        view.findViewById(R.id.calendar_end).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePickerDialog();

            }
        });

        view.findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // EventDetailsForm.java
                eventName = getActivity().findViewById(R.id.event_name);
                venue = getActivity().findViewById(R.id.venue);
                sponsor = getActivity().findViewById(R.id.sponsor);

                radioGroup = getActivity().findViewById(R.id.radioGroup);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = getActivity().findViewById(selectedId);

                startDateText.findViewById(R.id.starting_date);
                endDateText.findViewById(R.id.ending_date);

                EventDetailsForm eventDetailsForm = new EventDetailsForm(
                        eventName.getText().toString(),
                        venue.getText().toString(),
                        sponsor.getText().toString(),
                        radioButton.getText().toString(),
                        startDateText.getText().toString(),
                        endDateText.getText().toString()
                );

                formViewModel.setData(eventDetailsForm);
            }
        });
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String date = year + "-" + month + "-" + dayOfMonth;

        if(counter == 0){
            counter+=1;
            startDateText = getActivity().findViewById(R.id.starting_date);
            startDateText.setText(date);
        }else{
            counter-=1;
            endDateText = getActivity().findViewById(R.id.ending_date);
            endDateText.setText(date);
        }
    }
}