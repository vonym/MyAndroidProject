package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by vonym on 16-12-21.
 */

public class FirstReceiver extends BroadcastReceiver {
    //onReceive()在广播接收器接受到相应的广播调用
    //ANR（application no respose）应用程序无相应
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "我是地一个Receiver", Toast.LENGTH_SHORT).show();
    }
}
