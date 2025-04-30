package com.example.schoolmanagement;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class view_srkg_syllabus extends AppCompatActivity {

    ImageView imageViewsrkgSyllabus;
    DBsrkg databaseHelpers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_srkg_syllabus);

        imageViewsrkgSyllabus = findViewById(R.id.imageViewsrkgSyllabus);
        databaseHelpers = new DBsrkg(this);

        String syllabusImageString = databaseHelpers.getsrkgSyllabusImage();
        if (syllabusImageString != null) {
            Bitmap syllabusImage = decodeStringToImage(syllabusImageString);
            imageViewsrkgSyllabus.setImageBitmap(syllabusImage);
        } else {
            imageViewsrkgSyllabus.setImageResource(android.R.drawable.ic_menu_report_image);
        }
    }

    private Bitmap decodeStringToImage(String imageString) {
        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }
}