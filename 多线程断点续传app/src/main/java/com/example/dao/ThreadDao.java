package com.example.dao;

import java.util.List;
import com.example.bean.ThreadInfo;

public interface ThreadDao {
    /**
     * 新增线程信息
     *
     * @param threadInfo
     */
    public void insertThread(ThreadInfo threadInfo);

    /**
     * 修改线程的信息
     *
     * @param url
     * @param thread_id
     */
    public void updateThread(String url, int thread_id, int finished);

    /**
     * 删除线程的信息
     *
     * @param url
     */
    public void deleteThread(String url);

    /**
     * 查询线程信息
     *
     * @param url
     * @return
     */
    public List<ThreadInfo> selectThread(String url);

    /**
     * 判断线程是否存在
     *
     * @param url
     * @param thread_id
     * @return
     */
    public boolean isExists(String url, int thread_id);
}
