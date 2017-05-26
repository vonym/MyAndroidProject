package com.example.app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.txt);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        textView.setText("设备编号：" + telephonyManager.getDeviceId() + "\n网络运营商名称："
                + (telephonyManager.getDeviceSoftwareVersion() != null ? telephonyManager.getDeviceSoftwareVersion() : "未知")
                + "\n网络类型：" + telephonyManager.getNetworkOperatorName() + "\nSIM卡国家："
                + telephonyManager.getSimCountryIso() + "\nSIM卡序列号：" + telephonyManager.getSimSerialNumber()
                + "\nSIM卡状态：" + telephonyManager.getSimState());

    }
}
