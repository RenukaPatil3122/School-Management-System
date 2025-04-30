package com.example.schoolmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBNotice extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "school_management.db";
    private static final int DATABASE_VERSION = 1;

    public DBNotice(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_NOTICES = "CREATE TABLE notices (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT NOT NULL," +
                "content TEXT NOT NULL" +
                ")";
        db.execSQL(CREATE_TABLE_NOTICES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notices");
        onCreate(db);
    }
}
