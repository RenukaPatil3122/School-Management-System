package com.example.schoolmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class tmainpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tmainpage);

        // Timetable button
        Button button = findViewById(R.id.tmb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tmainpage.this, Timetable.class);
                startActivity(intent);
            }
        });

        // Notice button
        Button button1 = findViewById(R.id.tmb1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tmainpage.this, noticepage.class);
                startActivity(intent);
            }
        });

        // Attendance button
        Button button2 = findViewById(R.id.tmb2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tmainpage.this, trackattendance.class);
                startActivity(intent);
            }
        });

        // Homework button
        Button button3 = findViewById(R.id.tmb3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tmainpage.this, AddHomework.class);
                // Optionally pass data if needed
                intent.putExtra("homeworkTitle", "Sample Title");
                intent.putExtra("homeworkDescription", "Sample Description");
                startActivity(intent);
            }
        });


        // Syllabus button
        Button button4 = findViewById(R.id.tmb4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tmainpage.this, select_your_class.class);
                startActivity(intent);
            }
        });

        //Marksheet button (Corrected here)
       /* Button button5 = findViewById(R.id.tmb5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(tmainpage.this, "Marksheet Button Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(tmainpage.this, Marksheet.class);
                startActivity(intent);
            }
        });*/
    }
}
