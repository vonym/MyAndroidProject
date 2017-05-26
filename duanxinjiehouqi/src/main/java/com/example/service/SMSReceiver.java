package com.example.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "来短信了", Toast.LENGTH_SHORT).show();
        nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Object[] objects = (Object[]) intent.getExtras().get("pdus");
        for (Object obj : objects) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
            //发件人号码
            String number = smsMessage.getDisplayOriginatingAddress();
            //短信的内容
            String smsBoby = smsMessage.getDisplayMessageBody();
            if (number.equals("+8618702626523")) {
                abortBroadcast();
                Toast.makeText(context, "拦截", Toast.LENGTH_SHORT).show();
            } else {
                Notification.Builder builder = new Notification.Builder(context);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle(number);
                builder.setContentText(smsBoby);
                builder.setDefaults(Notification.DEFAULT_SOUND);
                Intent intent1 = new Intent(context, SecondActivity.class);
                intent1.putExtra("number", number);
                intent1.putExtra("body", smsBoby);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);
                Notification notification = builder.build();
                nm.notify(1, notification);
            }
        }
    }
}
