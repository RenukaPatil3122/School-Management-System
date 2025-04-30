package com.example.schoolmanagement;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBPlaySyllabus extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "playsyllabus.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PLAYSYLLABUS = "PLAY_Syllabus";
    private static final String COLUMN_PID = "P_id";
    private static final String COLUMN_PLAY_SYLLABUS_IMAGE = "PLAYsyllabus_image";

    public DBPlaySyllabus(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createJRKGSyllabusTable = "CREATE TABLE " + TABLE_PLAYSYLLABUS + " (" +
                COLUMN_PID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PLAY_SYLLABUS_IMAGE + " TEXT)";
        db.execSQL(createJRKGSyllabusTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYSYLLABUS);
        onCreate(db);
    }

    public boolean insertOrUpdatePLAYSyllabusImage(String PLAYsyllabusImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PLAY_SYLLABUS_IMAGE, PLAYsyllabusImage);

        Cursor cursor = db.query(TABLE_PLAYSYLLABUS, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            int updateResult = db.update(TABLE_PLAYSYLLABUS, contentValues, null, null);
            cursor.close();
            return updateResult != -1;
        } else {
            long insertResult = db.insert(TABLE_PLAYSYLLABUS, null, contentValues);
            cursor.close();
            return insertResult != -1;
        }
    }

    public String getPlaySyllabusImage() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PLAYSYLLABUS, new String[]{COLUMN_PLAY_SYLLABUS_IMAGE}, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String JRKGsyllabusImage = cursor.getString(cursor.getColumnIndex(COLUMN_PLAY_SYLLABUS_IMAGE));
            cursor.close();
            return JRKGsyllabusImage;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }
}