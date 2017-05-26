package com.example.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btn(View view) {
        Intent intent = new Intent();
        intent.setAction("briup");
        //无序广播，所有与
        sendBroadcast(intent);
    }

    public void btn2(View view) {
        Intent intent = new Intent();
        IntentFilter filter = new IntentFilter();
        filter.addAction("ecjtu");
        registerReceiver(new SecondReceiver(), filter);
        intent.setAction("ecjtu");
        sendBroadcast(intent);
    }
}
