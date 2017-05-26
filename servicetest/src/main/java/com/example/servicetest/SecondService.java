package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by vonym on 16-12-20.
 */

public class SecondService extends Service {
    private int count = 0;
    private boolean flag = true;

    public class Mybinder extends Binder {
        public int getCount() {
            return count;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (flag) {
                    count++;
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.v("msg", count + "");
                    if (count > 100) {
                        flag = false;
                    }
                }
            }
        }.start();
        return new Mybinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

