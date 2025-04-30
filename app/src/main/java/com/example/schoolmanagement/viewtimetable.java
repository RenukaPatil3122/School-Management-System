package com.example.schoolmanagement;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class viewtimetable extends AppCompatActivity {

    ImageView imageviewTimetable;
    DatabaseHelpers databaseHelpers;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtimetable);

        imageviewTimetable = findViewById(R.id.imageViewTimetable);
        databaseHelpers = new DatabaseHelpers(this);

        String TimetableImageString = databaseHelpers.getTimetableImage();
        if (TimetableImageString != null) {
            Bitmap timetableImage = decodeStringToImage(TimetableImageString);
            imageviewTimetable.setImageBitmap(timetableImage);
        } else {
            imageviewTimetable.setImageResource(android.R.drawable.ic_menu_report_image);
        }
    }

    private Bitmap decodeStringToImage(String imageString) {
        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }
}