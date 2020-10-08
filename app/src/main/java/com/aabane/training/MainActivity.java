package com.aabane.training;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {
    private MaterialCardView Card;
    public int RESPONSE_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Card = findViewById(R.id.CardAddTraining);
        Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddTraining.class);
                startActivityForResult(i,RESPONSE_CODE);
            }
        });
    }

}