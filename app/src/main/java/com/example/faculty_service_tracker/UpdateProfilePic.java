package com.example.faculty_service_tracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faculty_service_tracker.model.Model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UpdateProfilePic extends AppCompatActivity {

    private  final int IMG_REQUEST = 1;

    ImageView new_pic, back_btn, select_img;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_pic);

        Model model = Model.getInstance(UpdateProfilePic.this.getApplication());

        TextView head = findViewById(R.id.tvEditProfile);
        Button upload_btn = findViewById(R.id.upload_pic);

        back_btn = findViewById(R.id.imgView_back_btn);
        new_pic = findViewById(R.id.new_profile);
        select_img = findViewById(R.id.select_pic);

        head.setText(R.string.new_profile);

        select_img.setOnClickListener(view -> {
            selectImage();
        });

        upload_btn.setOnClickListener(view -> upload_new_profile(model));

        back_btn.setOnClickListener(view -> finish());
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
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageData);
                new_pic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void upload_new_profile(Model model){
        model.upload_profile(
                model.getUser().getAcc_id(),
                new_pic
        );
    }
}