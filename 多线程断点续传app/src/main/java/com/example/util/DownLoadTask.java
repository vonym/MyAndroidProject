package com.example.util;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;

import com.example.bean.FileInfo;
import com.example.bean.ThreadInfo;
import com.example.impl.ThreadDaoImpl;
import com.example.service.DownLoadService;

public class DownLoadTask {
    private Context context;
    private FileInfo fileInfo;
    private ThreadDaoImpl threadDaoImpl;
    private int finished;
    public boolean isPause = false;
    private int threadCount = 1;
    private List<DownLoadThread> threadList;

    public DownLoadTask(Context context, FileInfo fileInfo, int threadCount) {
        this.context = context;
        this.threadCount = threadCount;
        this.fileInfo = fileInfo;
        threadDaoImpl = new ThreadDaoImpl(context);
    }

    public void downLoad() {
        List<ThreadInfo> list = threadDaoImpl.selectThread(fileInfo.getUrl());
        if (list.size() == 0) {
            int length = fileInfo.getLength() / threadCount;
            for (int i = 0; i < threadCount; i++) {
                ThreadInfo threadInfo = new ThreadInfo(i, fileInfo.getUrl(), i
                        * length, (i + 1) * length - 1, 0);
                if (i == threadCount - 1) {
                    threadInfo.setEnd(fileInfo.getLength());
                }
                list.add(threadInfo);
                threadDaoImpl.insertThread(threadInfo);
            }
        }
        threadList = new ArrayList<DownLoadThread>();
        for (ThreadInfo threadInfo : list) {
            DownLoadThread downLoadThread = new DownLoadThread(threadInfo);
            downLoadThread.start();
            threadList.add(downLoadThread);
        }
    }

    class DownLoadThread extends Thread {
        private ThreadInfo threadInfo;
        private boolean isFinished = false;

        public DownLoadThread(ThreadInfo threadInfo) {
            this.threadInfo = threadInfo;
        }

        @Override
        public void run() {
            HttpURLConnection conn = null;
            InputStream inputStream = null;
            RandomAccessFile raf = null;
            try {
                URL url = new URL(threadInfo.getUrl());
                System.out.println(threadInfo.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                int start = threadInfo.getStart() + threadInfo.getFinished();
                conn.setRequestProperty("Range", "bytes=" + start + "-" + threadInfo.getEnd());
                conn.connect();
                File file = new File(DownLoadService.DOWNLOAD_PATH, fileInfo.getFileName());
                raf = new RandomAccessFile(file, "rwd");
                raf.seek(start);
                finished += threadInfo.getFinished();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    inputStream = conn.getInputStream();
                    byte[] bytes = new byte[1024 * 4];
                    int len = -1;
                    long time = System.currentTimeMillis();
                    while ((len = inputStream.read(bytes)) != -1) {
                        System.out.println("aaa");
                        raf.write(bytes, 0, len);
                        // 整个文件的下载进度
                        finished += len;
                        threadInfo.setFinished(threadInfo.getFinished() + len);
                        if (System.currentTimeMillis() - time > 1000) {
                            time = System.currentTimeMillis();
                            System.out.println("aaa");
                            Intent intent = new Intent();
                            intent.setAction(DownLoadService.ACTION_UPDATE);
                            intent.putExtra("finished", finished * 100 / fileInfo.getLength());
                            intent.putExtra("id", fileInfo.getId());
                            context.sendBroadcast(intent);
                        }
                        if (isPause) {
                            threadDaoImpl.updateThread(threadInfo.getUrl(),
                                    threadInfo.getThread_Id(),
                                    threadInfo.getFinished());
                            return;
                        }
                    }
                    isFinished = true;
                    checkAllFinished();
                }
            } catch (Exception e) {
            } finally {
                try {
                    if (conn != null) {
                        conn.disconnect();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (raf != null) {
                        raf.close();
                    }
                } catch (Exception e2) {
                }
            }
            super.run();
        }
    }

    private synchronized void checkAllFinished() {
        boolean allFinished = true;
        for (DownLoadThread thread : threadList) {
            if (!thread.isFinished) {
                allFinished = false;
                break;
            }
        }
        if (allFinished) {
            // 如果能顺利下载完,那么删除线程信息
            threadDaoImpl.deleteThread(fileInfo.getUrl());
            Intent intent = new Intent();
            intent.setAction(DownLoadService.ACTION_FINISH);
            intent.putExtra("fileinfo", fileInfo);
            context.sendBroadcast(intent);
        }
    }
}
