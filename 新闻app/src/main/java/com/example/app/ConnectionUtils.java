package com.example.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * 数据获取类
 * Created by vonym on 17-3-14.
 */

public class ConnectionUtils {
    /**
     * 通过（选择的类型）的URL获取json文件
     *
     * @param url
     * @param listener
     */
    public static void getNewsResource(final String url, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL u = new URL(url);
                    connection = (HttpURLConnection) u.openConnection();
                    connection.setReadTimeout(8000);
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.connect();
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuffer stringBuffer = new StringBuffer();
                        String line = "";
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuffer.append(line);
                        }
                        if (listener != null) {
                            listener.onSuccess(stringBuffer.toString());
                        }
                    } else
                        Log.v("msg", "连接失败");
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    /**
     * 通过图片的URL获取图片
     *
     * @param url
     * @param listener
     */
    public static void getBitmap(final String url, final HttpCallbackListener listener) {

       /* URL u = null;
        u = new URL(url);
        bmp = BitmapFactory.decodeStream(u.openStream());*/


        new Thread(new Runnable() {
            @Override
            public void run() {
                URL aURL = null;
                try {
                    aURL = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) aURL.openConnection();
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);
                    Bitmap bm = BitmapFactory.decodeStream(bis);
                    if (listener != null) {
                        listener.onSuccess(bm);
                    }
                    bis.close();
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
