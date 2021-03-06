package com.aabane.training;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BaseDAO {
    protected final static int VERSION = 5;
    // Le nom du fichier qui représente ma base
    protected final static String NOM = "trainings_database.db";

    protected SQLiteDatabase mDb = null;
    protected DatabaseHandler mHandler;

    public BaseDAO(Context pContext) {
        this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}
