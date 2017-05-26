package com.example.servicebestpractice;

/**
 * 回调接口
 * 用于对下载过程中各种状态进行监听和回调
 * Created by vonym on 17-4-9.
 */

public interface DownloadListener {
    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
