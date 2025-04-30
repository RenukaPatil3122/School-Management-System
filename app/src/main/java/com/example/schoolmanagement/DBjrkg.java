package com.example.schoolmanagement;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBjrkg extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "jrkgsyllabus.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_JRKGSYLLABUS = "JRKG_Syllabus";
    private static final String COLUMN_JID = "J_id";
    private static final String COLUMN_JRKG_SYLLABUS_IMAGE = "JRKGsyllabus_image";

    public DBjrkg(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createJRKGSyllabusTable = "CREATE TABLE " + TABLE_JRKGSYLLABUS + " (" +
                COLUMN_JID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_JRKG_SYLLABUS_IMAGE + " TEXT)";
        db.execSQL(createJRKGSyllabusTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JRKGSYLLABUS);
        onCreate(db);
    }

    public boolean insertOrUpdateJRKGSyllabusImage(String JRKGsyllabusImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_JRKG_SYLLABUS_IMAGE, JRKGsyllabusImage);

        Cursor cursor = db.query(TABLE_JRKGSYLLABUS, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            int updateResult = db.update(TABLE_JRKGSYLLABUS, contentValues, null, null);
            cursor.close();
            return updateResult != -1;
        } else {
            long insertResult = db.insert(TABLE_JRKGSYLLABUS, null, contentValues);
            cursor.close();
            return insertResult != -1;
        }
    }

    public String getjrkgSyllabusImage() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_JRKGSYLLABUS, new String[]{COLUMN_JRKG_SYLLABUS_IMAGE}, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String JRKGsyllabusImage = cursor.getString(cursor.getColumnIndex(COLUMN_JRKG_SYLLABUS_IMAGE));
            cursor.close();
            return JRKGsyllabusImage;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }
}