package com.aabane.training;

public class TrainingsHolder {
    private static Training training = null;

    public static Training getTraining() {
        return training;
    }

    public static void setTraining(Training training) {
        TrainingsHolder.training = training;
    }

}
