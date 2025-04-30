package com.example.schoolmanagement;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class noticepage extends AppCompatActivity {

    private EditText editNoticeTitle, editNoticeContent;
    private Button buttonSubmitNotice;
    private DBNotice dbNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticepage);

        // Initialize the views
        editNoticeTitle = findViewById(R.id.edit_notice_title);
        editNoticeContent = findViewById(R.id.edit_notice_content);
        buttonSubmitNotice = findViewById(R.id.button_submit_notice);

        // Initialize the database helper
        dbNotice = new DBNotice(this);

        // Set onClickListener for the submit button
        buttonSubmitNotice.setOnClickListener(view -> {
            String title = editNoticeTitle.getText().toString().trim();
            String content = editNoticeContent.getText().toString().trim();

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(noticepage.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Insert notice into the database
                insertNotice(title, content);
            }
        });
    }

    private void insertNotice(String title, String content) {
        SQLiteDatabase db = dbNotice.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);

        long result = db.insert("notices", null, values);
        if (result == -1) {
            Toast.makeText(this, "Failed to add notice", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Notice added successfully", Toast.LENGTH_SHORT).show();
            editNoticeTitle.setText("");
            editNoticeContent.setText("");
        }
        db.close();
    }
}
