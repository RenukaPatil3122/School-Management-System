package com.example.schoolmanagement;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class view_class4_syllabus extends AppCompatActivity {

    ImageView imageView4Syllabus;
    DBclass4 databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_class4_syllabus);

        imageView4Syllabus = findViewById(R.id.imageViewSyllabus4);
        databaseHelper = new DBclass4(this);

        String syllabus4ImageString = databaseHelper.getSyllabusImage();
        if (syllabus4ImageString != null) {
            Bitmap syllabus4Image = decodeStringToImage(syllabus4ImageString);
            imageView4Syllabus.setImageBitmap(syllabus4Image);
        } else {
            imageView4Syllabus.setImageResource(android.R.drawable.ic_menu_report_image);
        }
    }

    private Bitmap decodeStringToImage(String imageString) {
        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }
}
