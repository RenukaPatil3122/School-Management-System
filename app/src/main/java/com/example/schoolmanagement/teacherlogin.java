package com.example.schoolmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class teacherlogin extends AppCompatActivity {

    private EditText teacherNameEditText, classTeacherEditText, ageEditText, birthDateEditText, experienceEditText, teacherIdEditText, emailEditText, passwordEditText, rePasswordEditText;
    private Button nextButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherlogin);

        // Initialize EditText fields
        teacherNameEditText = findViewById(R.id.TeacherNameEditText);
        classTeacherEditText = findViewById(R.id.ClassTeacherEditText);
        ageEditText = findViewById(R.id.ageEditText); // Make sure IDs match your XML
        birthDateEditText = findViewById(R.id.birthDateEditText); // Make sure IDs match your XML
        experienceEditText = findViewById(R.id.experienceEditText); // Make sure IDs match your XML
        teacherIdEditText = findViewById(R.id.TeacherIdEditText);
        emailEditText = findViewById(R.id.emailEditText); // Make sure IDs match your XML
        passwordEditText = findViewById(R.id.TeditTextPassword);
        rePasswordEditText = findViewById(R.id.rePasswordEditText);
        nextButton = findViewById(R.id.next);
        databaseHelper = new DatabaseHelper(this);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input data
                String name = teacherNameEditText.getText().toString().trim();
                String className = classTeacherEditText.getText().toString().trim();
                String age = ageEditText.getText().toString().trim();
                String birthDate = birthDateEditText.getText().toString().trim();
                String experience = experienceEditText.getText().toString().trim();
                String id = teacherIdEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String rePassword = rePasswordEditText.getText().toString().trim();

                // Validate input
                if (name.isEmpty() || className.isEmpty() || age.isEmpty() || birthDate.isEmpty() || experience.isEmpty() || id.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                    Toast.makeText(teacherlogin.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(rePassword)) {
                    Toast.makeText(teacherlogin.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Insert data into the database
                boolean isInserted = databaseHelper.insertTeacher(name, className, age, birthDate, experience, email, password);

                if (isInserted) {
                    // Navigate to the next activity
                    Intent intent = new Intent(teacherlogin.this, tlogin.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(teacherlogin.this, "Failed to register", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }}
