package com.example.schoolmanagement;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class view_class2_syllabus extends AppCompatActivity {

    ImageView imageView2Syllabus;
    DBclass2 databaseHelpers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_class2_syllabus);

        imageView2Syllabus = findViewById(R.id.imageViewSyllabus2);
        databaseHelpers = new DBclass2(this);

        String syllabus2ImageString = databaseHelpers.getSyllabusImage();
        if (syllabus2ImageString != null) {
            Bitmap syllabus2Image = decodeStringToImage(syllabus2ImageString);
            imageView2Syllabus.setImageBitmap(syllabus2Image);
        } else {
            imageView2Syllabus.setImageResource(android.R.drawable.ic_menu_report_image);
        }
    }

    private Bitmap decodeStringToImage(String imageString) {
        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }
}
