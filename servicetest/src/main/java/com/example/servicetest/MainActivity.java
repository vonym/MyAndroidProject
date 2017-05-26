package com.example.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ServiceConnection connection;
    private SecondService.Mybinder mybinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                mybinder = (SecondService.Mybinder) iBinder;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
    }

    public void btn(View view) {
        Intent intent = new Intent(MainActivity.this, FirstServies.class);
        startService(intent);
    }

    public void btn1(View view) {
        Intent intent = new Intent(MainActivity.this, SecondService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void btn2(View view) {
        unbindService(connection);
    }

    public void btn3(View view) {
        Toast.makeText(MainActivity.this, mybinder.getCount() + "", Toast.LENGTH_SHORT).show();
    }
}
