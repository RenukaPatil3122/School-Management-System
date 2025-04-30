package com.example.schoolmanagement;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class view_class1_syllabus extends AppCompatActivity {

    ImageView imageView1Syllabus;
    DBSyllabus databaseHelpers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_class1_syllabus);

        imageView1Syllabus = findViewById(R.id.imageViewSyllabus);
        databaseHelpers = new DBSyllabus(this);

        String syllabus1ImageString = databaseHelpers.getSyllabusImage();
        if (syllabus1ImageString != null) {
            Bitmap syllabus1Image = decodeStringToImage(syllabus1ImageString);
            imageView1Syllabus.setImageBitmap(syllabus1Image);
        } else {
            imageView1Syllabus.setImageResource(android.R.drawable.ic_menu_report_image);
        }
    }

    private Bitmap decodeStringToImage(String imageString) {
        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }
}