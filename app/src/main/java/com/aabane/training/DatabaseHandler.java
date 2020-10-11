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
    private static String Exercise_Table = "exercise";
    private static String Exercise_id = "exercise_id";
    private static String Exercise_Name = "exercise_name";
    private static String Exercise_Weight = "exercise_weight";
    private static String Exercise_reps = "exercise_reps";
    private static String Exercise_Image = "exercise_image";
    private static String Create_Table_Exercise_Query = "CREATE TABLE " + Exercise_Table + " (" +
            Exercise_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Exercise_Name + " VARCHAR(50)," +
            Exercise_Image + " BLOB," +
            Exercise_Weight + " NUMBER(9),"+
            Training_id + " INTEGER,"+
            Exercise_reps + " INTEGER," +
            "FOREIGN KEY ("+Training_id+") REFERENCES "+Training_Table+"("+Training_id+") );";
    private static String DELETE_Training_Query = "DROP TABLE " + Training_Table +";";
    private static String DELETE_Exercise_Query = "DROP TABLE " + Exercise_Table +";";

    public  DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Create_Table_Training_Query);
        sqLiteDatabase.execSQL(Create_Table_Exercise_Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(DELETE_Training_Query);
        sqLiteDatabase.execSQL(Create_Table_Training_Query);
        sqLiteDatabase.execSQL(Create_Table_Exercise_Query);
    }
}
