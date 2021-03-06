package com.aabane.training;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;


public class MyIntentService extends IntentService {

    private static final String ACTION_STORE_TRAINING = "com.aabane.training.action.StoreTraining";
    private static final String ACTION_STORE_TRAINING_EXERCISE = "com.aabane.training.action.StoreTrainingExercise";
    private static final String EXTRA_PARAM1 = "com.aabane.training.extra.PARAM1";

    public MyIntentService() {
        super("MyIntentService");
    }


    public static void startActionStoreTraining(Context context) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_STORE_TRAINING);
        context.startService(intent);
    }
    public static void startActionStoreExercise(Context context) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_STORE_TRAINING_EXERCISE);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_STORE_TRAINING.equals(action)) {
                final Training param1 = (Training) TrainingsHolder.getTraining();
                handleActionStoreTraining(param1);
            }
            if (ACTION_STORE_TRAINING_EXERCISE.equals(action)) {
                final TrainingExercice param1 = (TrainingExercice) TrainingExerciseHolder.getExercise();
                handleActionStoreExercise(param1);
            }
        }
    }


    private void handleActionStoreTraining(Training param1) {
        TrainingDAO training_database_handler = new TrainingDAO(getApplicationContext());
        training_database_handler.open();
        training_database_handler.add(param1);
        training_database_handler.close();
        Toast.makeText(getApplicationContext(),"Stored",Toast.LENGTH_LONG).show();
    }
    private void handleActionStoreExercise(TrainingExercice param1) {
        TrainingExerciseDAO training_database_handler = new TrainingExerciseDAO(getApplicationContext());
        training_database_handler.open();
        training_database_handler.addExercise(param1);
        training_database_handler.close();
        Toast.makeText(getApplicationContext(),"Stored Exercise: "+param1.getExercise_name(),Toast.LENGTH_LONG).show();
    }


}
