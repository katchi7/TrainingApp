package com.aabane.training;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Trainings extends AppCompatActivity {
    ImageButton back ;
    RecyclerView trainings_rv;
    TrainingListAdapter Adapter;
    public static ArrayList<Training> TrainingsList = new ArrayList<>();
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

        trainings_rv.setLayoutManager(new LinearLayoutManager(this));
        Adapter = new TrainingListAdapter(TrainingsList);
        trainings_rv.setAdapter(Adapter);

    }
    public void Return(){
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Adapter.notifyDataSetChanged();
    }
}