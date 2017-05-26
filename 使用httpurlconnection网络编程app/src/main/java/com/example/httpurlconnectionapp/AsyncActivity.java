package com.example.httpurlconnectionapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by vonym on 17-4-3.
 */

public class AsyncActivity extends AppCompatActivity {
    private ProgressBar bar;
    private TextView textView;
    private String url = "http://119.29.143.94/test.xml";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        textView = (TextView) findViewById(R.id.tv);
        bar = (ProgressBar) findViewById(R.id.bar);
    }

    public void start(View view) {
        AsynctaskConn conn = new AsynctaskConn(textView, bar);
        conn.execute(url);
    }
}
