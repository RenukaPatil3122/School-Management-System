package com.example.schoolmanagement;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class view_jrkg_syllabus extends AppCompatActivity {

    ImageView imageViewjrkgSyllabus;
    DBjrkg databaseHelpers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jrkg_syllabus);

        imageViewjrkgSyllabus = findViewById(R.id.imageViewJRKGSyllabus);
        databaseHelpers = new DBjrkg(this);

        String syllabusImageString = databaseHelpers.getjrkgSyllabusImage();
        if (syllabusImageString != null) {
            Bitmap syllabusImage = decodeStringToImage(syllabusImageString);
            imageViewjrkgSyllabus.setImageBitmap(syllabusImage);
        } else {
            imageViewjrkgSyllabus.setImageResource(android.R.drawable.ic_menu_report_image);
        }
    }

    private Bitmap decodeStringToImage(String imageString) {
        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }
}