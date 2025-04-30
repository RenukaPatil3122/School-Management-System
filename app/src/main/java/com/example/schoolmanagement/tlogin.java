package com.example.schoolmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class tlogin extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextPassword;
    private Button TbuttonLogin;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tlogin);

        db = new DatabaseHelper(this);

        editTextName = findViewById(R.id.TloginNameEditText);
        editTextPassword = findViewById(R.id.TeditTextPassword);
        TbuttonLogin = findViewById(R.id.TbuttonLogin);


        TbuttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String password = editTextPassword.getText().toString();

                if (db.checkUser(name, password)) {
                    Intent intent = new Intent(tlogin.this, tmainpage.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(tlogin.this, "Invalid Name or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}