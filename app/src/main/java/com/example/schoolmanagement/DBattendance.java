package com.example.schoolmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DBattendance extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DBAttendance";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ATTENDANCE = "Attendance";

    public DBattendance(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_ATTENDANCE + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "class TEXT, "
                + "date TEXT, "
                + "student TEXT, "
                + "present INTEGER)";
        db.execSQL(createTable);
        Log.d("DBattendance", "Table created: " + TABLE_ATTENDANCE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTENDANCE);
        onCreate(db);
    }

    public void insertAttendance(String selectedClass, String selectedDate, HashMap<String, Boolean> attendanceMap) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ATTENDANCE, "class = ? AND date = ?", new String[]{selectedClass, selectedDate}); // Clear previous records for that date

        for (String student : attendanceMap.keySet()) {
            ContentValues values = new ContentValues();
            values.put("class", selectedClass);
            values.put("date", selectedDate);
            values.put("student", student);
            values.put("present", attendanceMap.get(student) ? 1 : 0); // Store 1 for present, 0 for absent
            db.insert(TABLE_ATTENDANCE, null, values);
        }
        db.close();
        Log.d("DBattendance", "Attendance inserted for class: " + selectedClass + " on date: " + selectedDate);
    }

    public ArrayList<String[]> getAttendance(String selectedClass, String selectedDate) {
        ArrayList<String[]> attendanceList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Log.d("DBattendance", "Fetching attendance for Class: " + selectedClass + ", Date: " + selectedDate);

        // Retrieve attendance records
        Cursor cursor = db.rawQuery("SELECT student, present FROM " + TABLE_ATTENDANCE + " WHERE class = ? AND date = ?", new String[]{selectedClass, selectedDate});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String student = cursor.getString(0);
                int present = cursor.getInt(1);
                attendanceList.add(new String[]{student, present == 1 ? "Present" : "Absent"});
            }
            cursor.close();
        } else {
            Log.d("DBattendance", "Cursor is null. No data found for the provided class and date.");
        }
        db.close();
        return attendanceList;
    }

    public void logTableColumns() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("PRAGMA table_info(" + TABLE_ATTENDANCE + ")", null);
        Log.d("DB Columns", "Columns in " + TABLE_ATTENDANCE + ":");
        while (cursor.moveToNext()) {
            Log.d("DB Columns", "Column Name: " + cursor.getString(1));
        }
        cursor.close();
        db.close();
    }
}
