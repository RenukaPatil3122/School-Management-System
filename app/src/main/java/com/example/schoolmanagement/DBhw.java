package com.example.schoolmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhw extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DBhw.db";
    private static final int DATABASE_VERSION = 1;

    public DBhw(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table for homework
        String CREATE_HOMEWORK_TABLE = "CREATE TABLE homework (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "content TEXT)";
        db.execSQL(CREATE_HOMEWORK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS homework");
        onCreate(db);
    }
}
