package com.example.schoolmanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    private EditText etName, etPassword;
    private Button blogin;
    private DBStudent dbStudent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        blogin = findViewById(R.id.blogin);

        dbStudent = new DBStudent(this);

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(login.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isValid = dbStudent.checkStudent(name, password);
                    if (isValid) {
                        Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        // Proceed to the next activity after successful login
                        Intent intent = new Intent(login.this, mainpage.class); // Replace with your dashboard activity
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(login.this, "Invalid Name or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}