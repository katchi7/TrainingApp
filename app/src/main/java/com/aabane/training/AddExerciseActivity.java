package com.aabane.training;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageButton;

public class AddExerciseActivity extends AppCompatActivity {
    public static final String TRAINING_ID = "com.aabane.training.trainingid";
    public static final String TRAINING_NAME = "com.aabane.training.trainingname";
    private ImageButton back ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Return(RESULT_CANCELED);
            }
        });
    }
    public void Return(int result){
        setResult(result);
        finish();
    }
}