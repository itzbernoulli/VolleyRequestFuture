package com.oyinloyeayodeji.futuresdemo;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class TestWorker extends Worker {

    private static final String TAG = TestWorker.class.getSimpleName();

    public TestWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/todos/1", null, future, future);
        VolleySingleton.getmInstance(getApplicationContext()).addToRequestQueue(request);

        try {

            JSONObject response = future.get(60, TimeUnit.SECONDS); // this will block
            Log.d(TAG, response.toString());
            return Result.success();
        } catch (InterruptedException e) {
            e.printStackTrace();
            // exception handling
            return Result.failure();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return Result.failure();
            // exception handling
        } catch (TimeoutException e) {
            e.printStackTrace();
            return Result.failure();
        }
    }

}
