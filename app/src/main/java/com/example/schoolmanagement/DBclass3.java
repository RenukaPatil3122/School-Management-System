package com.example.schoolmanagement;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBclass3 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "class3.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_THREESYLLABUS = "THREE_Syllabus";
    private static final String COLUMN_SID = "id";
    private static final String COLUMN_THREESYLLABUS_IMAGE = "syllabus_image";

    public DBclass3(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSyllabusTable = "CREATE TABLE " + TABLE_THREESYLLABUS + " (" +
                COLUMN_SID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_THREESYLLABUS_IMAGE + " TEXT)";
        db.execSQL(createSyllabusTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THREESYLLABUS);
        onCreate(db);
    }

    public boolean insertOrUpdate3SyllabusImage(String syllabus3Image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_THREESYLLABUS_IMAGE, syllabus3Image);

        Cursor cursor = db.query(TABLE_THREESYLLABUS, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            db.update(TABLE_THREESYLLABUS, contentValues, null, null);
        } else {
            db.insert(TABLE_THREESYLLABUS, null, contentValues);
        }
        cursor.close();
        return true;
    }

    @SuppressLint("Range")
    public String getSyllabusImage() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_THREESYLLABUS_IMAGE + " FROM " + TABLE_THREESYLLABUS, null);
        if (cursor != null && cursor.moveToFirst()) {
            String image = cursor.getString(cursor.getColumnIndex(COLUMN_THREESYLLABUS_IMAGE));
            cursor.close();
            return image;
        }
        return null;
    }
}
