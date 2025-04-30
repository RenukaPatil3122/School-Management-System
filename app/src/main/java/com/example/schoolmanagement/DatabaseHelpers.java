/* Timetable DB */
package com.example.schoolmanagement;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelpers extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "schoolT.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TIMETABLE = "Timetable";
    private static final String COLUMN_TID = "id";
    private static final String COLUMN_TIMETABLE_IMAGE = "timetable_image";

    public DatabaseHelpers(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTimetableTable = "CREATE TABLE " + TABLE_TIMETABLE + " (" +
                COLUMN_TID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TIMETABLE_IMAGE + " TEXT)";
        db.execSQL(createTimetableTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMETABLE);
        onCreate(db);
    }

    public boolean insertOrUpdateTimetableImage(String timetableImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TIMETABLE_IMAGE, timetableImage);

        Cursor cursor = db.query(TABLE_TIMETABLE, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            int updateResult = db.update(TABLE_TIMETABLE, contentValues, null, null);
            cursor.close();
            return updateResult != -1;
        } else {
            long insertResult = db.insert(TABLE_TIMETABLE, null, contentValues);
            cursor.close();
            return insertResult != -1;
        }
    }

    public String getTimetableImage() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TIMETABLE, new String[]{COLUMN_TIMETABLE_IMAGE}, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String timetableImage = cursor.getString(cursor.getColumnIndex(COLUMN_TIMETABLE_IMAGE));
            cursor.close();
            return timetableImage;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }
}
