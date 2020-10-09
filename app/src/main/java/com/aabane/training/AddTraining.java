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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddTraining extends AppCompatActivity {
    private static final int RESULT_LOAD_IMG = 1;
    ImageButton back ;
    Button add;
    Button getImage;
    TextView image_status;
    Bitmap Training_Image;
    EditText Name_et;
    boolean image_loaded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Loading Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_training);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Getting Activity items
        Name_et= findViewById(R.id.TrainingName_et);
        back = findViewById(R.id.back);
        add = findViewById(R.id.add);
        getImage = findViewById(R.id.Load);
        //Setting listeners
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Return();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Empty(Name_et)&&image_loaded){
                    //TODO : Save to the database
                    Return();
                    Toast.makeText(getApplicationContext(),"Training Added",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(),"Missing data",Toast.LENGTH_LONG).show();


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
    }
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                Training_Image = selectedImage;
                image_status.setText("Image Loaded");
                image_status.setTextColor(getResources().getColor(R.color.green));
                image_loaded=true;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(AddTraining.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(AddTraining.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
    public void Return(){
        setResult(RESULT_OK);
        finish();
    }
    public boolean Empty(View view){
        if(((EditText)view).getText().toString().trim().length()==0) return true;
        return false;
    }
}