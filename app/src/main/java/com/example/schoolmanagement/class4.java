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

public class class4 extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 4;
    ImageView imageView4Syllabus;
    Button buttonSelect4Image, buttonUpload4Syllabus;
    DBclass4 databaseHelper;
    private Bitmap selected4Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class4);

        imageView4Syllabus = findViewById(R.id.imageViewSyllabus4);
        buttonSelect4Image = findViewById(R.id.buttonSelect4Image);
        buttonUpload4Syllabus = findViewById(R.id.buttonUpload4Syllabus);
        databaseHelper = new DBclass4(this);

        buttonSelect4Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        buttonUpload4Syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected4Image != null) {
                    String imageString = encodeImageToString(selected4Image);
                    boolean isInserted = databaseHelper.insertOrUpdate4SyllabusImage(imageString);
                    if (isInserted) {
                        Toast.makeText(class4.this, "Syllabus uploaded/updated successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(class4.this, "Failed to upload/update syllabus.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(class4.this, "Please select an image.", Toast.LENGTH_SHORT).show();
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
                selected4Image = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView4Syllabus.setImageBitmap(selected4Image);
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
