package com.example.schoolmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class studentlogin extends AppCompatActivity {

    private EditText etName, etRollNo, etFathersName, etMothersName, etAge, etClass, etBirthDate, etEmail, etPassword, etRePassword;
    private Button btnNext;
    private DBStudent dbStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);

        etName = findViewById(R.id.etName);
        etRollNo = findViewById(R.id.etRollNo);
        etFathersName = findViewById(R.id.etFathersName);
        etMothersName = findViewById(R.id.etMothersName);
        etAge = findViewById(R.id.etAge);
        etClass = findViewById(R.id.etClass);
        etBirthDate = findViewById(R.id.etBirthDate);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRePassword = findViewById(R.id.etRePassword);
        btnNext = findViewById(R.id.btnNext);

        dbStudent = new DBStudent(this);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String rollNo = etRollNo.getText().toString().trim();
                String fathersName = etFathersName.getText().toString().trim();
                String mothersName = etMothersName.getText().toString().trim();
                String age = etAge.getText().toString().trim();
                String studentClass = etClass.getText().toString().trim();
                String birthDate = etBirthDate.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String rePassword = etRePassword.getText().toString().trim();

                if (name.isEmpty() || rollNo.isEmpty() || fathersName.isEmpty() || mothersName.isEmpty() ||
                        age.isEmpty() || studentClass.isEmpty() || birthDate.isEmpty() ||
                        email.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                    Toast.makeText(studentlogin.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(rePassword)) {
                    Toast.makeText(studentlogin.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = dbStudent.insertStudent(name, rollNo, fathersName, mothersName, Integer.parseInt(age), studentClass, birthDate, email, password);
                    if (isInserted) {
                        Toast.makeText(studentlogin.this, "Student Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(studentlogin.this, login.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(studentlogin.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}