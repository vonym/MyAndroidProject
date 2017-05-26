package com.example.app.service;

import android.content.Context;
import android.content.Intent;

import com.example.app.Bean.FileInfo;
import com.example.app.Bean.ThreadInfo;
import com.example.app.dao.ThreadDao;
import com.example.app.dao.ThreadDaoImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by vonym on 17-4-3.
 */
public class DownLoadTask {
    private Context context;
    private FileInfo fileInfo;
    private ThreadDao threadDao;
    public static final String ACTION_UPDATE = "ACTION_UPDATE";
    private int finished = 0;
    public boolean isPause = false;

    public DownLoadTask(Context context, FileInfo fileInfo) {
        this.context = context;
        this.fileInfo = fileInfo;
        threadDao = new ThreadDaoImpl(context);
    }

    public void download() {
        /**
         * 读取数据库中的线程信息
         */
        List<ThreadInfo> threadInfos = threadDao.getThreads(fileInfo.getUrl());
        ThreadInfo threadInfo;
        if (threadInfos.size() == 0) {
            /**
             * 初始化线程信息
             */
            threadInfo = new ThreadInfo(0, 0, fileInfo.getUrl(), fileInfo.getLength(), 0);
        } else {
            threadInfo = threadInfos.get(0);
        }
        /**
         * 创建子线程进行下载
         */
        new DownLoadThread(threadInfo).start();
    }

    /**
     * 下载线程
     */
    class DownLoadThread extends Thread {
        private ThreadInfo mThreadInfo;

        public DownLoadThread(ThreadInfo threadInfo) {
            this.mThreadInfo = threadInfo;
        }

        @Override
        public void run() {
            /**
             * 像数据库插入线程信息
             */
            if (!threadDao.isExists(mThreadInfo.getUrl(), mThreadInfo.getId())) {
                threadDao.insertThread(mThreadInfo);
            }
            HttpURLConnection conn = null;
            RandomAccessFile raf = null;
            InputStream inputStream = null;
            try {
                URL url = new URL(mThreadInfo.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(8000);
                conn.setReadTimeout(8000);
                /**
                 * 设置下载位置
                 */
                int start = mThreadInfo.getStart() + mThreadInfo.getFinished();
                conn.setRequestProperty("Range", "bytes=" + start + "-" + mThreadInfo.getEnd());
                /**
                 * 设置文件写入位置
                 */
                File file = new File(DownLoadService.PATH, fileInfo.getFileName());
                raf = new RandomAccessFile(file, "rwd");
                raf.seek(start);
                Intent intent = new Intent(ACTION_UPDATE);
                /**
                 * 开始下载
                 */
                finished += mThreadInfo.getFinished();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_PARTIAL) {
                    /**
                     * 读取数据
                     */
                    inputStream = conn.getInputStream();
                    byte[] buffer = new byte[1024 * 4];
                    int len = -1;
                    while ((len = inputStream.read(buffer)) != -1) {
                        /**
                         * 写入文件
                         */
                        raf.write(buffer, 0, len);
                        finished += len;
                        intent.putExtra("fileInfo", fileInfo);
                        intent.putExtra("finished", finished * 100 / fileInfo.getLength());
                        context.sendBroadcast(intent);
                        /**
                         * 在暂停下载的时候，保存下载进度
                         */
                        if (isPause) {
                            threadDao.updateThread(mThreadInfo.getUrl(), mThreadInfo.getId(), finished);
                            return;
                        }
                    }
                    //删除线程信息
                    threadDao.deleteThread(mThreadInfo.getUrl(), mThreadInfo.getId());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.disconnect();
                    raf.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
