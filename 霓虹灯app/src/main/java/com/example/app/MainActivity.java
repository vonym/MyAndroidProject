package com.example.app;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private FrameLayout mFl_1, mFl_2, mFl_3;
    private Message message;
    private int img_position = 0;
    private int[] imgs = {Color.RED, Color.BLACK, Color.YELLOW};
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mFl_1.setBackgroundColor(imgs[msg.arg1 % imgs.length]);
            mFl_2.setBackgroundColor(imgs[(msg.arg1 + 1) % imgs.length]);
            mFl_3.setBackgroundColor(imgs[(msg.arg1 + 2) % imgs.length]);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFl_1 = (FrameLayout) findViewById(R.id.fm_1);
        mFl_2 = (FrameLayout) findViewById(R.id.fm_2);
        mFl_3 = (FrameLayout) findViewById(R.id.fm_3);

    }

    public void start(View view) {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    message = Message.obtain();
                    message.arg1 = img_position;
                    handler.sendMessage(message);
                    img_position++;
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (img_position >= 3) {
                        img_position = 0;
                    }
                }
            }
        }.start();
    }
}
