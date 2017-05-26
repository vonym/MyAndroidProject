package com.example.tcpipapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
    }

    public void send(View view) {
        /**
         * 1.加权限
         * 2.耗时操作写在子线程中
         * 3.ip不能用127.0.0.1
         * 4.UI操作
         */
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Socket socket = new Socket("192.168.1.107", 9999);
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    final String s = bufferedReader.readLine();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(s);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
