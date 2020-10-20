package com.aabane.training;

import android.content.Intent;
import android.os.Bundle;

import com.aabane.training.utils.ItemClickSupport;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Trainings extends AppCompatActivity  {
    public static final String EXTRA_ID = "com.aabane.training.Trainings.id";
    ImageButton back ;
    RecyclerView trainings_rv;
    TrainingListAdapter Adapter;
    public static ArrayList<Training> TrainingsList;
    DatabaseLoadingHandler Handler;
    private static final int ADD_TRAINING_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Return();
            }
        });
        trainings_rv = findViewById(R.id.trainings_rv);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Trainings.this,AddTraining.class);
                startActivityForResult(i,ADD_TRAINING_REQUEST);
            }
        });

        TrainingsList = new ArrayList<>();
        trainings_rv.setLayoutManager(new LinearLayoutManager(this));
        Adapter = new TrainingListAdapter(TrainingsList);
        trainings_rv.setAdapter(Adapter);
        Handler = new DatabaseLoadingHandler();
        configureOnClickRecyclerView();
        LoadTrainings();
    }
    public void Return(){
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) Adapter.notifyDataSetChanged();
    }
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(trainings_rv, R.layout.trainings_adapter)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        OnItemClicked(position);
                    }
                });
    }

    private void OnItemClicked(int position) {
        int id = Adapter.getItem(position).getId();
        Intent i = new Intent(this,TrainingExercises.class);
        i.putExtra(EXTRA_ID,id);
        startActivity(i);
    }
    public void LoadTrainings() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                TrainingDAO db = new TrainingDAO(getApplicationContext());
                db.open();
                ArrayList<Training> training = db.LoadAll();
                TrainingsList.addAll(training);
                Message message = Handler.obtainMessage();
                message.obj = Adapter;
                message.arg1 = DatabaseLoadingHandler.ALL_TRAINING_LOADING_MESSAGE;
                Handler.sendMessage(message);
                db.close();
            }
        }).start();
    }

}