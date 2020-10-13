package com.aabane.training;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class TrainingExercice {
    private int Training_id;
    private int id;
    private String Exercise_name;
    private  int Max_Weight;
    private  int Reps;
    private byte[] image;
    //Default constructor (used to extract data from the database
    public TrainingExercice(int Training_id,int id,String Exercise_name,int max_Weight,int Reps,byte[] Image){
        this.Training_id=Training_id;
        this.id = id;
        this.Exercise_name = Exercise_name;
        this.Max_Weight = max_Weight;
        this.Reps = Reps;
        this.image = Image;
    }
    //This constructor is dedicated to the extraction of informations from the user
    public TrainingExercice(int Training_id, String Exercise_name, int max_Weight, int Reps, Bitmap Image){
        this.Training_id=Training_id;
        this.Exercise_name = Exercise_name;
        this.Max_Weight = max_Weight;
        this.Reps = Reps;
        this.image = getBitmapAsByteArray(Image);
    }

    public byte[] getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public int getMax_Weight() {
        return Max_Weight;
    }

    public int getReps() {
        return Reps;
    }

    public int getTraining_id() {
        return Training_id;
    }

    public String getExercise_name() {
        return Exercise_name;
    }
    public Bitmap getImageAsBitmap(){
        return BitmapFactory.decodeByteArray(image,0,image.length);
    }

    //Convert image to byte
    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
