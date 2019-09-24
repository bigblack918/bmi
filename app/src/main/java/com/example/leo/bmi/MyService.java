package com.example.leo.bmi;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private final String TAG = this.getClass().getSimpleName();
    private int count = 0;

    public MyService() {
    }

    private MyBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        public int getCount() {
            return count;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "service onBind called");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "service onUnbind called");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e(TAG, "service onRebind called");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        count += 10;
        Log.e(TAG, "service onCreate called");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "service onStartCommand called");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "service onDestroy called");
    }

}
