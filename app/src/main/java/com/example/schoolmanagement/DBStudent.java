package com.example.schoolmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBStudent extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "School.db";
    public static final String TABLE_NAME = "students";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "ROLL_NO";
    public static final String COL_4 = "FATHERS_NAME";
    public static final String COL_5 = "MOTHERS_NAME";
    public static final String COL_6 = "AGE";
    public static final String COL_7 = "CLASS";
    public static final String COL_8 = "BIRTH_DATE";
    public static final String COL_9 = "EMAIL";
    public static final String COL_10 = "PASSWORD";

    public DBStudent(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "ROLL_NO TEXT, " +
                "FATHERS_NAME TEXT, " +
                "MOTHERS_NAME TEXT, " +
                "AGE INTEGER, " +
                "CLASS TEXT, " +
                "BIRTH_DATE TEXT, " +
                "EMAIL TEXT, " +
                "PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to insert a new student
    public boolean insertStudent(String name, String rollNo, String fathersName, String mothersName, int age, String studentClass, String birthDate, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, rollNo);
        contentValues.put(COL_4, fathersName);
        contentValues.put(COL_5, mothersName);
        contentValues.put(COL_6, age);
        contentValues.put(COL_7, studentClass);
        contentValues.put(COL_8, birthDate);
        contentValues.put(COL_9, email);
        contentValues.put(COL_10, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // returns true if data is inserted successfully
    }

    // Method to check student credentials during login
    public boolean checkStudent(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE NAME = ? AND PASSWORD = ?", new String[]{name, password});
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }
}