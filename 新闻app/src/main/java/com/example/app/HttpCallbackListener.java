package com.example.app;

/**
 * Created by vonym on 17-3-15.
 */

public interface HttpCallbackListener {
    public void onSuccess(Object response);

    public void onError(Exception e);
}
