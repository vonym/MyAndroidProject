package com.example.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.app.Bean.FileInfo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vonym on 17-3-14.
 */

public class DownLoadService extends Service {
    public static final String ACTION_START = "ACTION_START";
    public static final String ACTION_PAUSE = "ACTION_PAUSE";
    private FileInfo fileInfo;
    public static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/huadong/";
    private static final int MSG_INIT = 0x123;
    private DownLoadTask downLoadTask;
    private RandomAccessFile raf = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_INIT:
                    FileInfo fileInfo = (FileInfo) msg.obj;
                    downLoadTask = new DownLoadTask(DownLoadService.this, fileInfo);
                    downLoadTask.download();
                    Log.i("msg", "handleMessage:" + fileInfo.toString());
                    break;
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        fileInfo = (FileInfo) intent.getSerializableExtra("fileinfo");
        if (action.equals(ACTION_START)) {
            InitThread initThread = new InitThread(fileInfo);
            initThread.start();
        } else if (action.equals(ACTION_PAUSE)) {
            if (downLoadTask != null) {
                downLoadTask.isPause = true;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 获取文件长度的线程
     */
    class InitThread extends Thread {
        private FileInfo fileInfo;
        private int length = -1;

        public InitThread(FileInfo fileInfo) {
            this.fileInfo = fileInfo;
        }

        @Override
        public void run() {
            HttpURLConnection httpURLConnection = null;
            //连接网络
            try {
                URL url = new URL(fileInfo.getUrl());
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(8000);
                httpURLConnection.setReadTimeout(8000);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    //获取文件长度
                    length = httpURLConnection.getContentLength();
                }
                if (length < 0) {
                    return;
                }
                //在sd卡中创建一个一样大小的文件
                File file = new File(PATH);
                if (!file.exists()) {
                    file.mkdir();
                }
                File downLoadFile = new File(file, fileInfo.getFileName());
                raf = new RandomAccessFile(downLoadFile, "rwd");
                raf.setLength(length);
                fileInfo.setLength(length);
                handler.obtainMessage(MSG_INIT, fileInfo).sendToTarget();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    httpURLConnection.disconnect();
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
