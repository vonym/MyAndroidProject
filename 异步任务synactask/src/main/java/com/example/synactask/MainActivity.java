package com.example.synactask;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.ps);
        textView = (TextView) findViewById(R.id.tv);
    }

    public void start(View view) {
        MyAsynacTask myAsynacTask = new MyAsynacTask(progressBar, textView);
        myAsynacTask.execute(1000);
    }
}
