package com.aabane.training;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class TrainingDAO extends BaseDAO {
    private static String Training_Table = "training";
    private static String Training_id = "training_id";
    private static String Training_Name = "training_name";
    private static String Training_Image = "training_image";
    public TrainingDAO(Context pContext) {
        super(pContext);
    }
    public void add(Training training){
        ContentValues value = new ContentValues();
        value.put(Training_Name,training.getName());
        value.put(Training_Image,training.getImage());
        mDb.insert(Training_Table,null,value);
    }

    public Training Load(int id){
        Cursor c = mDb.rawQuery("select * from " + Training_Table + " where "+ Training_id + " = ? ", new String[]{""+id});
        return new Training(c.getInt(0),c.getString(1),c.getBlob(2));

    }
}
