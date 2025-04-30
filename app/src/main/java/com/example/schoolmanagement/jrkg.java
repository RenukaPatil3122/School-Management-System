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

public class jrkg extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    ImageView imageViewjrkgSyllabus;
    Button buttonSelectjrkgImage, buttonUploadjrkgSyllabus;
    DBjrkg databaseHelpers;
    private Bitmap JRKGselectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jrkg);

        imageViewjrkgSyllabus = findViewById(R.id.imageviewJRKGSyllabus);
        buttonSelectjrkgImage = findViewById(R.id.buttonselectjrkgImage);
        buttonUploadjrkgSyllabus = findViewById(R.id.buttonuploadJRKGSyllabus);
        databaseHelpers = new DBjrkg(this);

        buttonSelectjrkgImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        buttonUploadjrkgSyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (JRKGselectedImage != null) {
                    String imageString = encodeImageToString(JRKGselectedImage);
                    boolean isInserted = databaseHelpers.insertOrUpdateJRKGSyllabusImage(imageString);
                    if (isInserted) {
                        Toast.makeText(jrkg.this, "Syllabus uploaded/updated successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(jrkg.this, "Failed to upload/update syllabus.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(jrkg.this, "Please select an image.", Toast.LENGTH_SHORT).show();
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
                JRKGselectedImage = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageViewjrkgSyllabus.setImageBitmap(JRKGselectedImage);
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