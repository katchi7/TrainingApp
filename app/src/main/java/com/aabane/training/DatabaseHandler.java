package com.aabane.training;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static String Training_Table = "training";
    private static String Training_id = "training_id";
    private static String Training_Name = "training_name";
    private static String Training_Image = "training_image";
    private static String Create_Table_Training_Query = "CREATE TABLE " + Training_Table + " (" +
            Training_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Training_Name + " VARCHAR(50), " +
            Training_Image + " BLOB);";
    public  DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Create_Table_Training_Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Create_Table_Training_Query);
    }
}
