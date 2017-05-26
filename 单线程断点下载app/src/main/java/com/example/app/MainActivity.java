package com.example.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.app.Bean.FileInfo;
import com.example.app.service.DownLoadService;
import com.example.app.service.DownLoadTask;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private ProgressBar progressBar;
    private Button button;
    private boolean is_start=false;
    private FileInfo fileInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileInfo = new FileInfo(0, "http://119.29.143.94/movie.mp4", "movie.mp4", 0, 0);
        initVview();


    }

    private void initVview() {
        textView = (TextView) findViewById(R.id.tv);
        textView.setText(fileInfo.getFileName());
        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setMax(100);
        button = (Button) findViewById(R.id.start_btn);
        button.setOnClickListener(this);
        IntentFilter intentFilte = new IntentFilter();
        intentFilte.addAction(DownLoadTask.ACTION_UPDATE);
        registerReceiver(receiver, intentFilte);
    }

    @Override
    public void onClick(View v) {
        if (!is_start) {
            Intent intent = new Intent(this, DownLoadService.class);
            intent.setAction(DownLoadService.ACTION_START);
            intent.putExtra("fileinfo", fileInfo);
            button.setText("暂停");
            is_start = true;
            startService(intent);
        } else {
            button.setText("开始");
            is_start = false;
            Intent intent = new Intent(this, DownLoadService.class);
            intent.setAction(DownLoadService.ACTION_PAUSE);
            intent.putExtra("fileinfo", fileInfo);
            startService(intent);
        }
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(DownLoadTask.ACTION_UPDATE)) {
                int progress = intent.getIntExtra("finished", 0);
                progressBar.setProgress(progress);
            }
        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
