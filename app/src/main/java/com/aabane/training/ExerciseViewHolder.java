package com.aabane.training;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ExerciseViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView Weight;
    private TextView Reps;
    private ImageView imageView;
    public ExerciseViewHolder(@NonNull View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.Exercise_name_et);
        Weight = (TextView)  itemView.findViewById(R.id.Weight_et);
        Reps = (TextView)  itemView.findViewById(R.id.Reps_et);
        imageView = (ImageView) itemView.findViewById(R.id.Exercise_image);
    }
    public void bind(TrainingExercice Exercise){
        name.setText(Exercise.getExercise_name());
        Bitmap Image = Exercise.getImageAsBitmap();
        imageView.setImageBitmap(Image);
        Weight.setText(Weight.getText().toString()+Exercise.getMax_Weight());
        Reps.setText(Reps.getText().toString()+Exercise.getReps());
    }
}
