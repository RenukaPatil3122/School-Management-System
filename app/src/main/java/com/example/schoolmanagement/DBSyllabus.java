// DBSyllabus.java of class 1
package com.example.schoolmanagement;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSyllabus extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "class1.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ONESYLLABUS = "ONESyllabus";
    private static final String COLUMN_SID = "id";
    private static final String COLUMN_ONESYLLABUS_IMAGE = "syllabus_image";

    public DBSyllabus(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createoneSyllabusTable = "CREATE TABLE " + TABLE_ONESYLLABUS + " (" +
                COLUMN_SID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ONESYLLABUS_IMAGE + " TEXT)";
        db.execSQL(createoneSyllabusTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ONESYLLABUS);
        onCreate(db);
    }

    public boolean insertOrUpdate1SyllabusImage(String syllabus1Image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ONESYLLABUS_IMAGE, syllabus1Image);

        Cursor cursor = db.query(TABLE_ONESYLLABUS, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            int updateResult = db.update(TABLE_ONESYLLABUS, contentValues, null, null);
            cursor.close();
            return updateResult != -1;
        } else {
            long insertResult = db.insert(TABLE_ONESYLLABUS, null, contentValues);
            cursor.close();
            return insertResult != -1;
        }
    }

    public String getSyllabusImage() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ONESYLLABUS, new String[]{COLUMN_ONESYLLABUS_IMAGE}, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String syllabus1Image = cursor.getString(cursor.getColumnIndex(COLUMN_ONESYLLABUS_IMAGE));
            cursor.close();
            return syllabus1Image;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }
}