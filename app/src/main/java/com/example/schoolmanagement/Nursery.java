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

public class Nursery extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    ImageView imageViewSyllabus;
    Button buttonSelectImage, buttonUploadSyllabus;
    DBNurserySyllabus databaseHelpers;
    private Bitmap selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursery);

        imageViewSyllabus = findViewById(R.id.imageviewNSyllabus);
        buttonSelectImage = findViewById(R.id.buttonselectNImage);
        buttonUploadSyllabus = findViewById(R.id.buttonuploadNSyllabus);
        databaseHelpers = new DBNurserySyllabus(this);

        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        buttonUploadSyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedImage != null) {
                    String imageString = encodeImageToString(selectedImage);
                    boolean isInserted = databaseHelpers.insertOrUpdateSyllabusImage(imageString);
                    if (isInserted) {
                        Toast.makeText(Nursery.this, "Syllabus uploaded/updated successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Nursery.this, "Failed to upload/update syllabus.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Nursery.this, "Please select an image.", Toast.LENGTH_SHORT).show();
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
                selectedImage = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageViewSyllabus.setImageBitmap(selectedImage);
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