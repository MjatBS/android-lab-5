package com.example.lab4;


import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import java.util.function.Supplier;

public class ListenerFactory {
    private static ListenerFactory instance = null;
    public static ListenerFactory getInstance(){
        if(instance == null){
            instance = new ListenerFactory();
        }
        return instance;
    }
    private ListenerFactory(){}

    public View.OnClickListener createCancelButtonListener(AppCompatActivity activity) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                activity.setResult(Activity.RESULT_CANCELED, returnIntent);
                activity.finish();
            }
        };
        return listener;
    }

    public View.OnClickListener createSaveButtonListener(AppCompatActivity activity, String fieldUpdate, Supplier<String> newValue){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("fieldUpdate", fieldUpdate);
                returnIntent.putExtra("newValue", newValue.get());
                activity.setResult(Activity.RESULT_OK, returnIntent);
                activity.finish();
            }
        };
        return listener;
    }

    public View.OnClickListener createEditButtonListener(AppCompatActivity from, Class to, ActivityResultLauncher<Intent> launcher){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(from, to);
                launcher.launch(intent);
            }
        };
        return listener;
    }

}