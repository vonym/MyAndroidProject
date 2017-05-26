package com.example.app;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textViw;
    private SensorManager manager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViw = (TextView) findViewById(R.id.tv);
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        List<Sensor> list = manager.getSensorList(Sensor.TYPE_ALL);
        /*String str = "";
        for (Sensor s : list) {
            str += s.getName()+"\n";
        }
        textViw.setText(str);*/
    }

    /**
     * 传感器数据变化时回调
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        textViw.setText("X:" + event.values[0] + "\nY:" + event.values[1] + "\nZ:" + event.values[2]);
    }

    /**
     * 传感器精度变化时回调
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterListener(this);
    }
}
