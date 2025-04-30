package com.example.schoolmanagement;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Playgroup_syllabus extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    ImageView imageViewplaySyllabus;
    Button buttonSelectplayImage, buttonUploadplaySyllabus;
    DBPlaySyllabus databaseHelpers;
    private Bitmap PLAYselectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playgroup_syllabus);

        imageViewplaySyllabus = findViewById(R.id.imageViewplaysyllabus);
        buttonSelectplayImage = findViewById(R.id.buttonselectplayImage);
        buttonUploadplaySyllabus = findViewById(R.id.buttonUploadplaysyllabus);
        databaseHelpers = new DBPlaySyllabus(this);

        buttonSelectplayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        buttonUploadplaySyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PLAYselectedImage != null) {
                    String imageString = encodeImageToString(PLAYselectedImage);
                    boolean isInserted = databaseHelpers.insertOrUpdatePLAYSyllabusImage(imageString);
                    if (isInserted) {
                        Toast.makeText(Playgroup_syllabus.this, "Syllabus uploaded/updated successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Playgroup_syllabus.this, "Failed to upload/update syllabus.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Playgroup_syllabus.this, "Please select an image.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select syllabus Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                PLAYselectedImage = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageViewplaySyllabus.setImageBitmap(PLAYselectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String encodeImageToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
}