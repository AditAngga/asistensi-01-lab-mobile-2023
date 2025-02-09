package com.example.tprak8.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteHelper {

    // menyimpan proses CRUD ke method
    private static final String DATABASE_TABLE = DatabaseContract.TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;
    private static volatile NoteHelper INSTANCE;

    private NoteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static SQLiteDatabase getDatabase() {
        return database;
    }

    public static String getDatabaseTable() {
        return DATABASE_TABLE;
    }

    public static NoteHelper getInstance(Context context) {

        if (INSTANCE == null) {

            synchronized (SQLiteOpenHelper.class) {

                if (INSTANCE == null) {

                    INSTANCE = new NoteHelper(context);
                }
            }
        }

        return INSTANCE;
    }

    public void open() throws SQLException {

        database = databaseHelper.getWritableDatabase();
    }

    public void close() {

        databaseHelper.close();

        if (database.isOpen()) {

            database.close();
        }
    }

    public long insert(ContentValues values) {

        return database.insert(DATABASE_TABLE, null, values);
    }

    public int update(String id, ContentValues values) {

        return database.update(DATABASE_TABLE, values, DatabaseContract.NoteColumns._ID
                + " = ?", new String[]{id});
    }

    public int deleteById(String id) {

        return database.delete(DATABASE_TABLE, DatabaseContract.NoteColumns._ID + " = "
                + id, null);
    }
}
