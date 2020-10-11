package com.aabane.training;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrainingListAdapter extends RecyclerView.Adapter<TrainingViewHolder> {

    List<Training> Trainings = null;

    public TrainingListAdapter(List<Training> trainings){
        this.Trainings = trainings;
    }
    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainings_adapter,parent,false);
        return new TrainingViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TrainingViewHolder holder, int position) {
        Training myObject = Trainings.get(position);
        holder.bind(myObject);
    }


    public Training getItem(int position) {
        return Trainings.get(position);
    }

    @Override
    public int getItemCount() {
        return Trainings.size();
    }
}
