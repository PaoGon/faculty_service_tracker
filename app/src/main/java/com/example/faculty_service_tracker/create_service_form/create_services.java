package com.example.faculty_service_tracker.create_service_form;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.faculty_service_tracker.ChangePassword;
import com.example.faculty_service_tracker.R;
import com.example.faculty_service_tracker.model.Model;
import com.example.faculty_service_tracker.model.api.AbstractAPIListener;
import com.kofigyan.stateprogressbar.StateProgressBar;


public class create_services extends AppCompatActivity {

    private FormViewModel formViewModel;
    int created_service_id;

    //Fragment manager
    FragmentManager fragment_manager = getSupportFragmentManager();
    //Description below the step bar
    String[] descriptionData = {"Event\nDetails", "Office\nOrder", "DRA", "Terminal\nReport", "Photo\nBG"};
    ImageView office_order_img, dra_img, tr_img, bg_img;
    EventDetailsForm service_info;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_services);

        Model model = Model.getInstance(create_services.this.getApplication());
        int acc_id = model.getUser().getAcc_id();
        Toast.makeText(create_services.this, ""+acc_id, Toast.LENGTH_SHORT).show();

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
                            + " " + item.getStarting_date()
                            + " " + item.getEnding_date(), Toast.LENGTH_SHORT).show();

            service_info = item;

            // Next button for each fragment
            nextForm(stateProgressBar);


        });

        formViewModel.getOfficeOder().observe(create_services.this, item -> {
            office_order_img = item;
            nextForm(stateProgressBar);
        });

        // button.setOnClickListener(view -> nextForm(stateProgressBar));

        formViewModel.getDra().observe(create_services.this, objDra -> {
            dra_img = objDra;
            nextForm(stateProgressBar);
        });

        formViewModel.getTerminalReport().observe(create_services.this, objTR -> {
            tr_img = objTR;
            nextForm(stateProgressBar);
        });

        formViewModel.getPhotoBg().observe(create_services.this, objPtb -> {
            bg_img = objPtb;
            nextForm(stateProgressBar);
            create_service_req(model, acc_id);
        });

        back.setOnClickListener(view -> finish());
    }

    private void create_service_req(Model model, int acc_id){
        model.create_service(acc_id, service_info, new AbstractAPIListener() {
            @Override
            public void onServiceCreated(int service_id){
                if(service_id != 0){
                    model.upload_service_pic(bg_img, acc_id, service_id);
                }
                else{
                    Toast.makeText(create_services.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void upload_service_pic(Model model, int teacher_id){
        model.upload_service_pic(bg_img, teacher_id, created_service_id);
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