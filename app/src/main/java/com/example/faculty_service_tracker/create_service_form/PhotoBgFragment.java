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


public class PhotoBgFragment extends Fragment {

    private  final int IMG_REQUEST = 1;

    ImageView btnPickImageFromGallery;
    ImageView photo_bg_img;
    Bitmap bitmap;
    FormViewModel formViewModel;


    public PhotoBgFragment() {
        // Required empty public constructor
    }

    public static PhotoBgFragment newInstance() {
        PhotoBgFragment fragment = new PhotoBgFragment();

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
        return inflater.inflate(R.layout.fragment_photo_bg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Initialize view model
        formViewModel = new ViewModelProvider(requireActivity()).get(FormViewModel.class);

        //Initializing views
        btnPickImageFromGallery = view.findViewById(R.id.upload_photo_bg);
        photo_bg_img = view.findViewById(R.id.photo_bg_img);

        btnPickImageFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        view.findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize Convert Image bitmap to String "Method"
                String imgString = imageToString(bitmap);

                //Declare instance of Object
                PhotoBgForm photoBgForm = new PhotoBgForm(imgString);


                formViewModel.setData(photoBgForm);
            }
        });
    }

    //Get Image
    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pick an image"),IMG_REQUEST);
    }

    //Set Image to Bitmap
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){

            Uri imageData = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageData);
                photo_bg_img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Convert Image bitmap to String
    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
}