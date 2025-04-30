package com.example.schoolmanagement;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class student_syllabus extends AppCompatActivity {

    private dbanimation dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_syllabus);
        Button button=findViewById(R.id.btn_plygrp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(student_syllabus.this, view_playsyllabus.class);
                startActivity(intent);
            }
        });

        Button button1=findViewById(R.id.btn_nursury);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(student_syllabus.this, view_nursury_syllabus.class);
                startActivity(intent);
            }
        });
        Button button2=findViewById(R.id.btn_jrkg);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(student_syllabus.this, view_jrkg_syllabus.class);
                startActivity(intent);
            }
        });
        Button button3=findViewById(R.id.btn_srkg);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(student_syllabus.this, view_srkg_syllabus.class);
                startActivity(intent);
            }
        });
        Button button4=findViewById(R.id.btn_class1);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(student_syllabus.this, view_class1_syllabus.class);
                startActivity(intent);
            }
        });
        Button button5=findViewById(R.id.btn_class2);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(student_syllabus.this, view_class2_syllabus.class);
                startActivity(intent);
            }
        });
        Button button6=findViewById(R.id.btn_class3);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(student_syllabus.this, view_class3_syllabus.class);
                startActivity(intent);
            }
        });
        Button button7=findViewById(R.id.btn_class4);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(student_syllabus.this, view_class4_syllabus.class);
                startActivity(intent);
            }
        });

        // Initialize the database helper
        dbHelper = new dbanimation(this);

        // Load and apply the animation to the ImageView
        ImageView studentImageView = findViewById(R.id.studentImageView);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.student_animation);
        studentImageView.startAnimation(animation);

        // Insert a record into the database
        insertStudentData("John Doe", "Going to school");
    }

    // Method to insert student data into the database
    private void insertStudentData(String name, String status) {
        // Get a writable database instance
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a ContentValues object to hold the data
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("status", status);

        // Insert the data into the database
        db.insert("student", null, values);

        // Close the database
        db.close();
    }
}