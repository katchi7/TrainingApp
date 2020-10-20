package com.aabane.training;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseViewHolder> {
    private List<TrainingExercice> Exercise;
    public ExerciseListAdapter(List<TrainingExercice> Exercise){
        this.Exercise = Exercise;
    }
    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_adapter,parent,false);

        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        holder.bind(Exercise.get(position));
    }

    @Override
    public int getItemCount() {
        return Exercise.size();
    }
}
