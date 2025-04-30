package com.example.schoolmanagement;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBclass4 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "class4.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FOURSyllabus = "FOUR_Syllabus";
    private static final String COLUMN_SID = "id";
    private static final String COLUMN_FOURSyllabus_IMAGE = "syllabus_image";

    public DBclass4(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSyllabusTable = "CREATE TABLE " + TABLE_FOURSyllabus + " (" +
                COLUMN_SID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FOURSyllabus_IMAGE + " TEXT)";
        db.execSQL(createSyllabusTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOURSyllabus);
        onCreate(db);
    }

    public boolean insertOrUpdate4SyllabusImage(String syllabus4Image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FOURSyllabus_IMAGE, syllabus4Image);

        Cursor cursor = db.query(TABLE_FOURSyllabus, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            db.update(TABLE_FOURSyllabus, contentValues, null, null);
        } else {
            db.insert(TABLE_FOURSyllabus, null, contentValues);
        }
        cursor.close();
        return true;
    }

    @SuppressLint("Range")
    public String getSyllabusImage() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_FOURSyllabus_IMAGE + " FROM " + TABLE_FOURSyllabus, null);
        if (cursor != null && cursor.moveToFirst()) {
            String image = cursor.getString(cursor.getColumnIndex(COLUMN_FOURSyllabus_IMAGE));
            cursor.close();
            return image;
        }
        return null;
    }
}
