package com.example.localdatapersistenceassigment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesHelper {
    private static final String DATABASE_TABLE = DatabaseContract.TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;
    private static volatile NotesHelper INSTANCE;

    private NotesHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static NotesHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NotesHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public static void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (database.isOpen()) {
            database.close();
        }
    }

    public Cursor queryAll() {
        return database.query(DATABASE_TABLE, null, null, null, null, null, DatabaseContract.NotesColumns._ID + " ASC");
    }

    public Cursor queryById(String id) {
        return database.query(DATABASE_TABLE, null, DatabaseContract.NotesColumns._ID + " = ?", new String[]{id}, null, null, null, null);
    }

    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int update(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, DatabaseContract.NotesColumns._ID + " = ?", new String[]{id});
    }

    public int deleteById(String id) {
        return database.delete(DATABASE_TABLE, DatabaseContract.NotesColumns._ID + " = " + id, null);
    }
}