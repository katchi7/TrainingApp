package com.aabane.training;


import android.os.Handler;
import android.os.Message;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class DatabaseLoadingHandler extends Handler {
    public static final int TRAINING_LOADING_MESSAGE = 0;
    public static final int ALL_TRAINING_LOADING_MESSAGE = 1;
    public static final int ALL_EXERCISES_LOADING_MESSAGE = 2;
    @Override
    public void handleMessage(Message message){
        super.handleMessage(message);
        if(message.arg1 == TRAINING_LOADING_MESSAGE){
            HashMap<String,Object> Map = (HashMap<String,Object>) message.obj;
            Render((Training)Map.get("training"),(TextView)Map.get("textview"),(ImageView)Map.get("imageview") );
        }
        if(message.arg1 == ALL_TRAINING_LOADING_MESSAGE){
            TrainingListAdapter Adapter = (TrainingListAdapter) message.obj;
            Adapter.notifyDataSetChanged();

        }
        if(message.arg1 == ALL_EXERCISES_LOADING_MESSAGE){
            ExerciseListAdapter Adapter = (ExerciseListAdapter) message.obj;
            Adapter.notifyDataSetChanged();
        }

    }
    public void Render(Training training, TextView Name, ImageView Training_Image_View){
        Training_Image_View.setImageBitmap(training.getBitmapImage());
        Name.setText(training.getName());
    }
}
