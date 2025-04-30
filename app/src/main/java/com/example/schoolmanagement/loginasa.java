package com.example.schoolmanagement;

import static com.example.schoolmanagement.R.id;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class loginasa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginasa);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button button2=findViewById(id.student_button);
        Button button=findViewById(R.id.teacherb);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginasa.this,studentlogin.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginasa.this,teacherlogin.class);
                startActivity(intent);
            }
        });

    }
}