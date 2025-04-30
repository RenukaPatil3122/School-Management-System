/*Tlogin DB*/


package com.example.schoolmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Information
    private static final String DATABASE_NAME = "SchoolManagement.db";
    private static final int DATABASE_VERSION = 1;

    // Table Information
    private static final String TABLE_TEACHERS = "teachers";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CLASS = "className"; // Renamed to avoid reserved keyword
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_BIRTH_DATE = "birth_date";
    private static final String COLUMN_EXPERIENCE = "experience";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TEACHERS_TABLE = "CREATE TABLE " + TABLE_TEACHERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_CLASS + " TEXT, " +
                COLUMN_AGE + " TEXT, " +
                COLUMN_BIRTH_DATE + " TEXT, " +
                COLUMN_EXPERIENCE + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(CREATE_TEACHERS_TABLE);
    }

    // Upgrade Table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHERS);
        onCreate(db);
    }

    // Insert Teacher
    public boolean insertTeacher(String name, String className, String age, String birthDate, String experience, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_CLASS, className);
        contentValues.put(COLUMN_AGE, age);
        contentValues.put(COLUMN_BIRTH_DATE, birthDate);
        contentValues.put(COLUMN_EXPERIENCE, experience);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_TEACHERS, null, contentValues);
        db.close();
        return result != -1;
    }

    // Check User
    public boolean checkUser(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID}; // Check if the user exists by ID
        String selection = COLUMN_NAME + " =? AND " + COLUMN_PASSWORD + " =?";
        String[] selectionArgs = {name, password};
        Cursor cursor = db.query(TABLE_TEACHERS, columns, selection, selectionArgs, null, null, null);
        boolean userExists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return userExists;
    }
}
