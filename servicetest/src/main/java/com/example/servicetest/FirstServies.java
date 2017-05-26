package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by vonym on 16-12-20.
 */

public class FirstServies extends Service {
    private boolean flag = true;
    private int count = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("msg", "oncreate()");
    }

    @Override
    public int onStartCommand(Intent intent, final int flags, int startId) {
        new Thread() {
            @Override
            public void run() {
                while (flag) {
                    Log.v("msg", (count++) + "");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count > 100) {
                        flag = false;
                    }
                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("msg", "onDestory()");
    }
}
