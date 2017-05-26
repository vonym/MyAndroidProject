package com.example.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.DownLoadAdapter;
import com.example.bean.FileInfo;
import com.example.service.DownLoadService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DownLoadReceiver downLoadReceiver;
    private ListView listView;
    private List<FileInfo> list;
    private DownLoadAdapter downLoadAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv);
        list = new ArrayList<FileInfo>();
        FileInfo fileInfo1 = new FileInfo(0,
                "http://10.0.3.2:8888/huadong.mp3", "huadong.mp3", 0, 0);
        FileInfo fileInfo2 = new FileInfo(1,
                "http://10.0.3.2:8888/areyouok.3gp", "areyouok.3gp", 0, 0);
        FileInfo fileInfo3 = new FileInfo(2,
                "http://10.0.3.2:8888/img_small.jpg", "img_small.jpg", 0, 0);
        list.add(fileInfo1);
        list.add(fileInfo2);
        list.add(fileInfo3);
        downLoadAdapter = new DownLoadAdapter(this, list);
        listView.setAdapter(downLoadAdapter);
        downLoadReceiver = new DownLoadReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownLoadService.ACTION_UPDATE);
        intentFilter.addAction(DownLoadService.ACTION_FINISH);
        registerReceiver(downLoadReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        if (downLoadReceiver != null) {
            unregisterReceiver(downLoadReceiver);
        }
        super.onDestroy();
    }

    class DownLoadReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownLoadService.ACTION_UPDATE.equals(intent.getAction())) {
                int finished = intent.getIntExtra("finished", 0);
                int id = intent.getIntExtra("id", 0);
                downLoadAdapter.updateProgress(id, finished);
            } else if (DownLoadService.ACTION_FINISH.equals(intent.getAction())) {
                FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileinfo");
                // 设置进度条为0
                downLoadAdapter.updateProgress(fileInfo.getId(), 0);
                Toast.makeText(MainActivity.this, list.get(fileInfo.getId()).getFileName() + "下载完毕", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
