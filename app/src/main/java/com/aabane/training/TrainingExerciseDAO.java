package com.aabane.training;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TrainingExerciseDAO extends BaseDAO {
    private static String Exercise_Table = "exercise";
    private static String Exercise_id = "exercise_id";
    private static String Exercise_Name = "exercise_name";
    private static String Exercise_Weight = "exercise_weight";
    private static String Training_id = "training_id";
    private static String Exercise_reps = "exercise_reps";
    private static String Exercise_Image = "exercise_image";
    private static String SELECT_EXERCISE_QUERY = "SELECT * FROM "+ Exercise_Table + " WHERE " + Exercise_id +" = ?";
    private static String UPDATE_EXERCISE_WEIGHT_QUERY = "UPDATE "+ Exercise_Table +" SET "+Exercise_Weight+" =?"+ " WHERE " + Exercise_id +" = ?";
    private static String UPDATE_EXERCISE_REPS_QUERY = "UPDATE "+ Exercise_Table +" SET "+Exercise_reps+" =?"+ " WHERE " + Exercise_id +" = ?";
    public TrainingExerciseDAO(Context pContext) {
        super(pContext);
    }
    public void addExercise(TrainingExercice Exercise){
        ContentValues values = new ContentValues();
        values.put(Exercise_Name,Exercise.getExercise_name());
        values.put(Training_id,Exercise.getTraining_id());
        values.put(Exercise_reps,Exercise.getReps());
        values.put(Exercise_Weight,Exercise.getMax_Weight());
        values.put(Exercise_Image,Exercise.getImage());
        mDb.insert(Exercise_Table,null,values);
    }
    public TrainingExercice getExercise(int ExerciseId){
        Cursor c = mDb.rawQuery(SELECT_EXERCISE_QUERY,new String[]{""+ExerciseId});
        c.moveToFirst();
        TrainingExercice Exercise = new TrainingExercice(c.getInt(c.getColumnIndex(Training_id)),
                                    c.getInt(c.getColumnIndex(Exercise_id)),
                                    c.getString(c.getColumnIndex(Exercise_Name)),
                                    c.getInt(c.getColumnIndex(Exercise_Weight)),
                                    c.getInt(c.getColumnIndex(Exercise_reps)),
                                    c.getBlob(c.getColumnIndex(Exercise_Image)));
        c.close();
        return Exercise;
    }
    //TODO IMPLEMENT THIS SHITTTTTTTTT
    public List<TrainingExercice> getTrainingExercises(int TrainingId){
        ArrayList<TrainingExercice> Exercises = new ArrayList<>();
        Cursor c = mDb.rawQuery("select * from " + Exercise_Table+" WHERE "+Training_id + " =?" , new String[]{""+TrainingId});
        //TODO Loop to load all the Database items
        c.moveToFirst();
        int i = c.getCount();
        if(i==0) return Exercises;
        Log.e("TESTING",i+" rows");
        int k = i;
        while(i!=0) {
            int index = c.getColumnIndexOrThrow(Exercise_Name);
            String name = c.getString(index);
            index = c.getColumnIndexOrThrow(Training_id);
            int training_id = c.getInt(index);
            index = c.getColumnIndexOrThrow(Exercise_id);
            int exercise_id = c.getInt(index);
            index = c.getColumnIndexOrThrow(Exercise_Image);
            byte[] Image = c.getBlob(index);
            index = c.getColumnIndexOrThrow(Exercise_Weight);
            int weight = c.getInt(index);
            index = c.getColumnIndexOrThrow(Exercise_reps);
            int reps = c.getInt(index);
            Exercises.add(new TrainingExercice(training_id,exercise_id,name,weight,reps,Image));
            c.moveToNext();
            i--;
            Log.e("TESTING",i+" rows");
        }
        c.close();
        Log.d("Test","Added "+ k + " Exercises");
        return Exercises;

    }

    public void UpdateExerciseMaxWeight(int ExerciseId,int MaxWidth){
        mDb.rawQuery(UPDATE_EXERCISE_WEIGHT_QUERY,new String[]{""+MaxWidth,""+ExerciseId});
    }
    public void UpdateExerciseReps(int ExerciseId,int Reps){
        mDb.rawQuery(UPDATE_EXERCISE_REPS_QUERY,new String[]{""+Reps,""+ExerciseId});
    }

}
