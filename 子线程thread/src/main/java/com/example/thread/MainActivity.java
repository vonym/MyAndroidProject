package com.example.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Message message;
    private MyThread myThread;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.et);

        myThread = new MyThread();
        myThread.start();
    }

    //子类的handler
    class MyThread extends Thread {
        public Handler handler;
        private List<Integer> list = new ArrayList<>();

        @Override
        public void run() {
            Looper.prepare();

            handler = new Handler(Looper.myLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x123) {
                        for (int i = 0; i < msg.arg1; i++) {
                            if (i % 2 == 0) {
                                list.add(i);
                            }
                        }
                        Toast.makeText(MainActivity.this, list.toString(), Toast.LENGTH_SHORT).show();
                        handler.sendEmptyMessage(0x1);
                    }
                }
            };
            Looper.loop();
        }
    }

    public void count(View view) {
        num = Integer.parseInt(editText.getText().toString());
        message = Message.obtain();
        message.what = 0x123;
        message.arg1 = num;
        myThread.handler.sendMessage(message);
    }
}
