package com.example.schoolmanagement;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBsrkg extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "school.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SRKGSYLLABUS = "srkgSyllabus";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SRKGSYLLABUS_IMAGE = "syllabus_image";

    public DBsrkg(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSyllabusTable = "CREATE TABLE " + TABLE_SRKGSYLLABUS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SRKGSYLLABUS_IMAGE + " TEXT)";
        db.execSQL(createSyllabusTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SRKGSYLLABUS);
        onCreate(db);
    }

    public boolean insertOrUpdatesrkgSyllabusImage(String syllabusImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SRKGSYLLABUS_IMAGE, syllabusImage);

        Cursor cursor = db.query(TABLE_SRKGSYLLABUS, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            int updateResult = db.update(TABLE_SRKGSYLLABUS, contentValues, null, null);
            cursor.close();
            return updateResult != -1;
        } else {
            long insertResult = db.insert(TABLE_SRKGSYLLABUS, null, contentValues);
            cursor.close();
            return insertResult != -1;
        }
    }

    public String getsrkgSyllabusImage() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SRKGSYLLABUS, new String[]{COLUMN_SRKGSYLLABUS_IMAGE}, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String srkgsyllabusImage = cursor.getString(cursor.getColumnIndex(COLUMN_SRKGSYLLABUS_IMAGE));
            cursor.close();
            return srkgsyllabusImage;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }
}