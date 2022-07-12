package com.example.faculty_service_tracker.create_service_form;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.example.faculty_service_tracker.R;
import com.kofigyan.stateprogressbar.StateProgressBar;


public class create_services extends AppCompatActivity {

    private FormViewModel formViewModel;

    //Fragment manager
    FragmentManager fragment_manager = getSupportFragmentManager();
    //Description below the step bar
    String[] descriptionData = {"Event\nDetails", "Office\nOrder", "DRA", "Terminal\nReport", "Photo\nBackground"};

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_services);

        ImageView back = findViewById(R.id.back_frag);

        // Next button before for each fragment
        // Button button = findViewById(R.id.btn_next);
        StateProgressBar stateProgressBar = findViewById(R.id.progress_bar);
        stateProgressBar.setStateDescriptionData(descriptionData);

        //ViewModel logic
        formViewModel = new ViewModelProvider(create_services.this).get(FormViewModel.class);

        formViewModel.getEventDetailsForm().observe(create_services.this, item -> {
            Toast.makeText(create_services.this,
                    item.getEvent_name()
                            + " " + item.getVenue()
                            + " " + item.getSponsor()
                            + " " + item.getEvent_type()
                            + " " + item.getStarting_date()
                            + " " + item.getEnding_date(), Toast.LENGTH_SHORT).show();

            // Next button for each fragment
            nextForm(stateProgressBar);


        });

        formViewModel.getOfficeOder().observe(create_services.this, item -> {
            Toast.makeText(create_services.this,
                    item.getImageOfficeOrder(),
                    Toast.LENGTH_SHORT).show();

            nextForm(stateProgressBar);
        });

        // button.setOnClickListener(view -> nextForm(stateProgressBar));

        formViewModel.getDra().observe(create_services.this, objDra -> {
            Toast.makeText(create_services.this,
                    objDra.getImageDRA(),
                    Toast.LENGTH_SHORT).show();

            nextForm(stateProgressBar);
        });

        formViewModel.getTerminalReport().observe(create_services.this, objTR -> {
            Toast.makeText(create_services.this,
                    objTR.getImgViewTerminalReport(),
                    Toast.LENGTH_SHORT).show();

            nextForm(stateProgressBar);
        });

        formViewModel.getPhotoBg().observe(create_services.this, objPtb -> {
            Toast.makeText(create_services.this,
                    objPtb.getImagePhotoBg(),
                    Toast.LENGTH_SHORT).show();

            nextForm(stateProgressBar);
        });

        back.setOnClickListener(view -> finish());
    }


    private void nextForm(StateProgressBar stateProgressBar){

        switch (stateProgressBar.getCurrentStateNumber()){

            case 1:
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                //fragment Office Order
                fragment_manager.beginTransaction()
                        .replace(R.id.fragmentContainerView, OfficeOrderFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name").commit();
                Toast.makeText(create_services.this, "state " + stateProgressBar.getCurrentStateNumber(), Toast.LENGTH_SHORT).show();
                break;

            case 2:
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                //fragment DRA
                fragment_manager.beginTransaction()
                        .replace(R.id.fragmentContainerView, DRAFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name").commit();
                Toast.makeText(create_services.this, "state " + stateProgressBar.getCurrentStateNumber(), Toast.LENGTH_SHORT).show();
                break;

            case 3:
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                //fragment Terminal Report
                fragment_manager.beginTransaction()
                        .replace(R.id.fragmentContainerView, TerminalReportFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name").commit();
                Toast.makeText(create_services.this, "state " + stateProgressBar.getCurrentStateNumber(), Toast.LENGTH_SHORT).show();
                break;

            case 4:
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FIVE);
                //fragment Photo Background
                fragment_manager.beginTransaction()
                        .replace(R.id.fragmentContainerView, PhotoBgFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name").commit();
                Toast.makeText(create_services.this, "state " + stateProgressBar.getCurrentStateNumber(), Toast.LENGTH_SHORT).show();
                break;
        }

    }

}