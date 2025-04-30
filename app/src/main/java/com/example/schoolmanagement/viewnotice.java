package com.example.schoolmanagement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class viewnotice extends AppCompatActivity {

    private ListView listNotices;
    private DBNotice dbNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewnotice);

        // Initialize the ListView
        listNotices = findViewById(R.id.list_notices);

        // Initialize the database helper
        dbNotice = new DBNotice(this);

        // Load notices from the database
        loadNotices();
    }

    private void loadNotices() {
        ArrayList<String> noticesList = new ArrayList<>();
        SQLiteDatabase db = dbNotice.getReadableDatabase();

        // Query the notices from the database
        Cursor cursor = db.query("notices", null, null, null, null, null, "id DESC");

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
                String notice = title + ": " + content;
                noticesList.add(notice);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // Set the adapter to display the notices
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noticesList);
        listNotices.setAdapter(adapter);
    }
}
