package com.aabane.training;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddExerciseActivity extends AppCompatActivity {
    public static final String TRAINING_ID = "com.aabane.training.trainingid";
    public static final String TRAINING_NAME = "com.aabane.training.trainingname";
    private static final int RESULT_LOAD_IMG = 1;
    private ImageButton back ;
    Button add;
    Button getImage;
    TextView image_status;

    Bitmap Training_Image;
    private Training training;
    EditText Name_et;
    EditText Weight_et;
    EditText Reps_et;
    int Training_id;
    boolean image_loaded = false;
    TrainingExercice trainingExercice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        Training_id = i.getIntExtra(TRAINING_ID,0);

        Name_et= findViewById(R.id.TrainingName_et);
        Weight_et= findViewById(R.id.Weight_et);
        Reps_et= findViewById(R.id.Reps_et);
        back = findViewById(R.id.back);
        add = findViewById(R.id.add);
        getImage = findViewById(R.id.Load);
        //Setting listeners
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Return(RESULT_CANCELED);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Empty(Name_et)&&!Empty(Weight_et)&&!Empty(Weight_et)&&image_loaded){
                    //TODO Create a Training Exercice Object and store it to the database
                    //Creating the object
                    trainingExercice = new TrainingExercice(Training_id,Name_et.getText().toString().trim(),Integer.parseInt(Weight_et.getText().toString().trim()),Integer.parseInt(Reps_et.getText().toString().trim()),Training_Image);
                    TrainingExerciseHolder.setExercise(trainingExercice);
                    Return(RESULT_OK);
                }
                else Toast.makeText(getApplicationContext(),"Missing data",Toast.LENGTH_LONG).show();


            }
        });

        getImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);

            }
        });
        image_status = findViewById(R.id.image_status);

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
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                Training_Image = getResizedBitmap(selectedImage,750);
                image_status.setText("Image Loaded");
                image_status.setTextColor(getResources().getColor(R.color.green));
                image_loaded=true;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(AddExerciseActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(AddExerciseActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
    public boolean Empty(View view){
        if(((EditText)view).getText().toString().trim().length()==0) return true;
        return false;
    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}