package com.example.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("川普四级GG：发推文单词拼错");
        builder.setContentText("川普四级GG：发推文单词拼错。川普四级GG：发推文单词拼错。川普四级GG：发推文单词拼错");
        builder.setTicker("这是一条新闻通知");
        //Bitmap bitmap= BitmapFactory.decodeResource();
        //从1970/1/1开始计时，到现在的时间，返回的是毫秒数
        System.currentTimeMillis();
        //从手机开始计时，返回的毫秒数
        SystemClock.currentThreadTimeMillis();
        builder.setWhen(System.currentTimeMillis());
        //点击通知跳转到相应的组件
        //参数PendingIntent:延时意图
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, SecondActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        //点击后通知自动消失
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        manager.notify(1, notification);
    }
}
