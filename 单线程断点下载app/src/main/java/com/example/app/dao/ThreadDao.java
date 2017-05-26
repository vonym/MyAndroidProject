package com.example.app.dao;

import com.example.app.Bean.ThreadInfo;

import java.util.List;

/**
 * Created by vonym on 17-3-14.
 */

public interface ThreadDao {

    /**
     * 插入线程信息
     *
     * @param threadInfo
     */
    public void insertThread(ThreadInfo threadInfo);

    /**
     * 删除线程
     *
     * @param url
     * @param thread_id
     */
    public void deleteThread(String url, int thread_id);

    /**
     * 更新线程下载进度
     *
     * @param url
     * @param thread_id
     * @param finished
     */
    public void updateThread(String url, int thread_id, int finished);

    /**
     * 查询文件所有的线程信息
     *
     * @param url
     * @return
     */
    public List<ThreadInfo> getThreads(String url);

    /**
     * 判断线程是否已经在数据库中存在
     *
     * @param url
     * @param thread_id
     * @return
     */
    public boolean isExists(String url, int thread_id);
}
