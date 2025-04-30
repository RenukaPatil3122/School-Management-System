package com.example.schoolmanagement;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class viewhomework extends AppCompatActivity {

    ListView listViewHomework;
    DBhw dbHelper;
    ArrayList<String> homeworkList;
    ArrayAdapter<String> adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewhomework);

        listViewHomework = findViewById(R.id.list_homework);
        dbHelper = new DBhw(this);

        loadHomework();
    }

    private void loadHomework() {
        homeworkList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("homework", null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                homeworkList.add("Title: " + title + "\nContent: " + content);
            }
            cursor.close();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, homeworkList);
        listViewHomework.setAdapter(adapter);
    }
}
