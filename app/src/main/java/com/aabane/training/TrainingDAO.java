package com.aabane.training;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;


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
        c.moveToFirst();
        int index = c.getColumnIndexOrThrow(Training_Name);
        Log.d("TEST",""+index);
        String name = c.getString(index);
        index = c.getColumnIndexOrThrow(Training_id);
        int id_ = c.getInt(index);
        index = c.getColumnIndexOrThrow(Training_Image);
        byte[] Image = c.getBlob(index);
        Log.d("TEST","id = "+" Name = " + name);
        c.close();
        return new Training(id_,name,Image);

    }
    public ArrayList<Training> LoadAll(){
        ArrayList<Training> Trainings = new ArrayList<>();
        Cursor c = mDb.rawQuery("select * from " + Training_Table , null);
        //TODO Loop to load all the Database items
        c.moveToFirst();
        int i = c.getCount();
        Log.e("TESTING",i+" rows");
        while(i!=0) {
            int index = c.getColumnIndexOrThrow(Training_Name);
            String name = c.getString(1);
            index = c.getColumnIndexOrThrow(Training_id);
            int id = c.getInt(0);
            index = c.getColumnIndexOrThrow(Training_Image);
            byte[] Image = c.getBlob(2);
            Trainings.add(new Training(id,name,Image));
            c.moveToNext();
            i--;
            Log.e("TESTING",i+" rows");
        }
        c.close();
        Log.e("TESTING","id : "+Trainings.get(0).getId() +" name : "+Trainings.get(0).getName());
        return Trainings;

    }

}
