package com.example.faculty_service_tracker.create_service_form;

import static android.app.Activity.RESULT_OK;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.faculty_service_tracker.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class TerminalReportFragment extends Fragment {

    private  final int IMG_REQUEST = 1;

    ImageView btnPickImageFromGallery;
    ImageView terminal_report_img;
    Bitmap bitmap;
    FormViewModel formViewModel;

    public TerminalReportFragment() {
        // Required empty public constructor
    }

    public static TerminalReportFragment newInstance() {
        TerminalReportFragment fragment = new TerminalReportFragment();

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
        return inflater.inflate(R.layout.fragment_terminal_report, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Initialize view model
        formViewModel = new ViewModelProvider(requireActivity()).get(FormViewModel.class);

        //Initializing views
        btnPickImageFromGallery = view.findViewById(R.id.upload_photo_termReport);
        terminal_report_img = view.findViewById(R.id.TR_img);

        btnPickImageFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        view.findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formViewModel.setTrImg(terminal_report_img);
            }
        });
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Pick an image"),IMG_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){

            Uri imageData = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageData);
                terminal_report_img.setImageBitmap(bitmap);
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}