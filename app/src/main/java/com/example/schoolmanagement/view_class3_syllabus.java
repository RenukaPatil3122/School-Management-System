package com.example.schoolmanagement;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class view_class3_syllabus extends AppCompatActivity {

    ImageView imageView3Syllabus;
    DBclass3 databaseHelpers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_class3_syllabus);

        imageView3Syllabus = findViewById(R.id.imageViewSyllabus3);
        databaseHelpers = new DBclass3(this);

        String syllabus3ImageString = databaseHelpers.getSyllabusImage();
        if (syllabus3ImageString != null) {
            Bitmap syllabus3Image = decodeStringToImage(syllabus3ImageString);
            imageView3Syllabus.setImageBitmap(syllabus3Image);
        } else {
            imageView3Syllabus.setImageResource(android.R.drawable.ic_menu_report_image);
        }
    }

    private Bitmap decodeStringToImage(String imageString) {
        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }
}
