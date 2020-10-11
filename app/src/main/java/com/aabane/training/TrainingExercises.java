package com.aabane.training;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.os.Message;
import android.util.Log;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class TrainingExercises extends AppCompatActivity {
    ImageButton back ;
    TextView Name;
    ImageView Training_Image_View;
    Training training = null;
    int Training_Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_exercises);
        Toolbar toolbar = findViewById(R.id.toolbar);
        Name = findViewById(R.id.training_Name_tv);
        Training_Image_View = findViewById(R.id.training_image);
        setSupportActionBar(toolbar);
        back = findViewById(R.id.back);
        Training_Id = getIntent().getIntExtra(Trainings.EXTRA_ID,0);
        Log.d("TEST",""+Training_Id);
        LoadTrainingInfo(Training_Id,Training_Image_View,Name);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Return(RESULT_CANCELED);
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TrainingExercises.this,AddExerciseActivity.class);
                i.putExtra(AddExerciseActivity.TRAINING_NAME,training.getName());
                i.putExtra(AddExerciseActivity.TRAINING_ID,training.getId());
                startActivity(i);
            }
        });
    }
    public void Return(int result){
        setResult(result);
        finish();
    }
    public void LoadTrainingInfo(final int Training_Id, final ImageView Training_Image_View, final TextView Name){
        new Thread(new Runnable() {
            @Override
            public void run() {
                TrainingDAO db = new TrainingDAO(getApplicationContext());
                db.open();
                training = db.Load(Training_Id);
                DatabaseLoadingHandler Handler = new DatabaseLoadingHandler();
                Message message = Handler.obtainMessage();
                HashMap<String,Object> obj =  new HashMap<String,Object>();
                obj.put("training",training);
                obj.put("imageview",Training_Image_View);
                obj.put("textview",Name);
                message.obj = obj;
                message.arg1 = DatabaseLoadingHandler.TRAINING_LOADING_MESSAGE;
                Handler.sendMessage(message);
                db.close();
            }
        }).run();
    }
}