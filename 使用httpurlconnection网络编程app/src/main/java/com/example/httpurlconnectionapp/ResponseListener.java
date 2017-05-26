package com.example.httpurlconnectionapp;

/**
 * Created by vonym on 17-3-14.
 */

public interface ResponseListener {
    /**
     * 成功时回调
     */

    public void onResult(Object obj);

    /**
     * 失败时回调
     */
    public void onError(String s);
}
