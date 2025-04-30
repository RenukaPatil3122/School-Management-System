package com.example.schoolmanagement;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBNurserySyllabus extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Syllabus.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NURSERYSYLLABUS = "NurserySyllabus";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NURSERYSYLLABUS_IMAGE = "Nurserysyllabus_image";

    public DBNurserySyllabus(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSyllabusTable = "CREATE TABLE " + TABLE_NURSERYSYLLABUS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NURSERYSYLLABUS_IMAGE + " TEXT)";
        db.execSQL(createSyllabusTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NURSERYSYLLABUS);
        onCreate(db);
    }

    public boolean insertOrUpdateSyllabusImage(String syllabusImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NURSERYSYLLABUS_IMAGE, syllabusImage);

        Cursor cursor = db.query(TABLE_NURSERYSYLLABUS, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            int updateResult = db.update(TABLE_NURSERYSYLLABUS, contentValues, null, null);
            cursor.close();
            return updateResult != -1;
        } else {
            long insertResult = db.insert(TABLE_NURSERYSYLLABUS, null, contentValues);
            cursor.close();
            return insertResult != -1;
        }
    }
    public String getNSyllabusImage() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NURSERYSYLLABUS, new String[]{COLUMN_NURSERYSYLLABUS_IMAGE}, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String syllabusImage = cursor.getString(cursor.getColumnIndex(COLUMN_NURSERYSYLLABUS_IMAGE));
            cursor.close();
            return syllabusImage;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }
}