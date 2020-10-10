package com.aabane.training;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrainingViewHolder  extends RecyclerView.ViewHolder{

    private TextView name;
    private TextView Description;
    private ImageView imageView;

    //itemView est la vue correspondante à 1 cellule
    public TrainingViewHolder(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        name = (TextView) itemView.findViewById(R.id.training_name_et);
        Description = (TextView)  itemView.findViewById(R.id.training_description_et);
        imageView = (ImageView) itemView.findViewById(R.id.training_image);
    }


    public void bind(Training training){
        name.setText(training.getName());
        Bitmap Image = training.getBitmapImage();
        imageView.setImageBitmap(Image);
    }
}