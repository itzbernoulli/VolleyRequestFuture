package com.oyinloyeayodeji.futuresdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(TestWorker.class).build();
        WorkManager.getInstance().enqueue(oneTimeWorkRequest);
    }
}
