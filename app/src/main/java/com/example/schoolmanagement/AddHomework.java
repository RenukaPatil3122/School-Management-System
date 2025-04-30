package com.example.schoolmanagement;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddHomework extends AppCompatActivity {

    EditText editTitle, editContent, editDeleteTitle;
    Button buttonSubmit, buttonDelete;
    DBhw dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);

        // Initialize views
        editTitle = findViewById(R.id.edit_notice_title);
        editContent = findViewById(R.id.edit_notice_content);
        editDeleteTitle = findViewById(R.id.edit_delete_title);
        buttonSubmit = findViewById(R.id.button_submit_notice);
        buttonDelete = findViewById(R.id.button_delete_notice);

        // Initialize database helper
        dbHelper = new DBhw(this);

        // Set button click listeners
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString().trim();
                String content = editContent.getText().toString().trim();

                // Insert homework into database
                if (!title.isEmpty() && !content.isEmpty()) {
                    insertHomework(title, content);
                } else {
                    Toast.makeText(AddHomework.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleToDelete = editDeleteTitle.getText().toString().trim();

                // Delete homework from database
                if (!titleToDelete.isEmpty()) {
                    deleteHomework(titleToDelete);
                } else {
                    Toast.makeText(AddHomework.this, "Please enter a title to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertHomework(String title, String content) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);

        long newRowId = db.insert("homework", null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Homework added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error adding homework", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteHomework(String title) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted = db.delete("homework", "title = ?", new String[]{title});
        if (rowsDeleted > 0) {
            Toast.makeText(this, "Homework deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Homework not found", Toast.LENGTH_SHORT).show();
        }
    }
}
