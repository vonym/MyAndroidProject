package com.example.service;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.example.bean.FileInfo;
import com.example.util.DownLoadTask;

public class DownLoadService extends Service {
    public static final int MSG_WHAT = 0x123;
    public static final String DOWNLOAD_PATH = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/BriupDownload/";
    public static final String ACTION_START = "ACTION_START";
    public static final String ACTION_PUASE = "ACTION_PUASE";
    public static final String ACTION_UPDATE = "ACTION_UPDATE";
    public static final String ACTION_FINISH = "ACTION_FINISH";
    private Map<Integer, DownLoadTask> map = new HashMap<Integer, DownLoadTask>();
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == MSG_WHAT) {
                FileInfo fileInfo = (FileInfo) msg.obj;
                System.out.println("得到长度以后的FileInfo:" + fileInfo.toString());
                // 真真正正的下载操作
                DownLoadTask downLoadTask = new DownLoadTask(DownLoadService.this, fileInfo, 3);
                downLoadTask.downLoad();
                map.put(fileInfo.getId(), downLoadTask);
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(ACTION_START)) {
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileinfo");
            System.out.println("fileInfo:" + fileInfo.toString());
            // 启动初始化线程
            new initThread(fileInfo).start();
        } else if (intent.getAction().equals(ACTION_PUASE)) {
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileinfo");
            System.out.println("fileInfo:" + fileInfo.toString());
            // 完成暂停操作
            DownLoadTask downLoadTask = map.get(fileInfo.getId());
            if (downLoadTask != null) {
                downLoadTask.isPause = true;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    // 获取文件大小的线程
    class initThread extends Thread {
        private FileInfo fileInfo;

        public initThread(FileInfo fileInfo) {
            this.fileInfo = fileInfo;
        }

        @Override
        public void run() {
            HttpURLConnection conn = null;
            RandomAccessFile raf = null;
            try {
                // 1.进行网络连接
                URL url = new URL(fileInfo.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                conn.connect();
                int length = -1;
                if (conn.getResponseCode() ==HttpURLConnection.HTTP_OK) {
                    // 2.获取文件大小
                    length = conn.getContentLength();
                    if (length <= 0) {
                        return;
                    }
                    // 3.生成对应大小的文件
                    File dir = new File(DOWNLOAD_PATH);
                    // 3.1判断这个文件夹是否存在
                    if (!dir.exists()) {
                        dir.mkdir();
                    }
                    // 3.2生成对应大小的文件
                    File file = new File(dir, fileInfo.getFileName());
                    raf = new RandomAccessFile(file, "rwd");
                    raf.setLength(length);
                    fileInfo.setLength(length);
                    Message.obtain(handler, MSG_WHAT, fileInfo).sendToTarget();
                }

            } catch (Exception e) {
            } finally {
                try {
                    conn.disconnect();
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            super.run();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
