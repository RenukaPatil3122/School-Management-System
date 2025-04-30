package com.example.schoolmanagement;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class view_playsyllabus extends AppCompatActivity {

    ImageView imageViewSyllabus;
    DBPlaySyllabus databaseHelpers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_playsyllabus);

        imageViewSyllabus = findViewById(R.id.imageViewPlaySyllabus);
        databaseHelpers = new DBPlaySyllabus(this);

        String syllabusImageString = databaseHelpers.getPlaySyllabusImage();
        if (syllabusImageString != null) {
            Bitmap syllabusImage = decodeStringToImage(syllabusImageString);
            imageViewSyllabus.setImageBitmap(syllabusImage);
        } else {
            imageViewSyllabus.setImageResource(android.R.drawable.ic_menu_report_image);
        }
    }

    private Bitmap decodeStringToImage(String imageString) {
        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }
}