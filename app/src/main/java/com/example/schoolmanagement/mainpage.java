package com.example.schoolmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class mainpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        Button button=findViewById(R.id.mb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mainpage.this,viewtimetable.class);
                startActivity(intent);
            }
        });

        Button button1=findViewById(R.id.mb1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mainpage.this,viewnotice.class);
                startActivity(intent);
            }
        });
        Button button2=findViewById(R.id.mb2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mainpage.this,viewattendence.class);
                startActivity(intent);
            }
        });
        Button button3=findViewById(R.id.mb3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mainpage.this,viewhomework.class);
                startActivity(intent);
            }
        });
        Button button4=findViewById(R.id.mb4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mainpage.this,student_syllabus.class);
                startActivity(intent);
            }
        });



    }}