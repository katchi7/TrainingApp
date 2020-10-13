package com.aabane.training;

public class TrainingExerciseHolder {
    private static TrainingExercice Exercise;

    public static void setExercise(TrainingExercice exercise) {
        Exercise = exercise;
    }

    public static TrainingExercice getExercise() {
        return Exercise;
    }
}
