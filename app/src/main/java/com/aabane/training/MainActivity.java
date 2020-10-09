package com.aabane.training;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {
    private MaterialCardView Card_add_training;
    private MaterialCardView Card_start_training;
    public int RESPONSE_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Card_add_training = findViewById(R.id.CardAddTraining);
        Card_start_training = findViewById(R.id.CardStartTraining);

        Card_add_training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddTraining.class);
                startActivityForResult(i,RESPONSE_CODE);
            }
        });

        Card_start_training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Trainings.class);
                startActivityForResult(i,RESPONSE_CODE);
            }
        });

    }

}