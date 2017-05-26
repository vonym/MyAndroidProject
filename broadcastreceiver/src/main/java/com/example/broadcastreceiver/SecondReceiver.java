package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by vonym on 16-12-21.
 */

public class SecondReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "我是第二个动态Receiver", Toast.LENGTH_SHORT).show();
    }
}
