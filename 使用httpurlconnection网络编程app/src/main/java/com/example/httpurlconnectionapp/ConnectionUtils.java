package com.example.httpurlconnectionapp;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by vonym on 17-3-14.
 */

public class ConnectionUtils {
    public static void SendRequest(final String url, final List<BasicValue> parmas, final ResponseListener listener, final Class<?> clazz) {
        new Thread() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                try {
                    URL u = new URL(url);
                    httpURLConnection = (HttpURLConnection) u.openConnection();
                    if (parmas == null) {
                        //get
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(8000);
                        httpURLConnection.setReadTimeout(8000);
                        httpURLConnection.connect();
                    } else {
                        //post
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setConnectTimeout(8000);
                        httpURLConnection.setReadTimeout(8000);
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.connect();
                        PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
                        for (int i = 0; i < parmas.size(); i++) {
                            printWriter.write("&");
                            printWriter.write(parmas.get(i).getKey() + "=" + parmas.get(i).getValue());
                        }
                        printWriter.flush();
                        printWriter.close();
                        if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            String line = "";
                            StringBuffer stringBuffer = new StringBuffer();
                            while ((line = bufferedReader.readLine()) != null) {
                                stringBuffer.append(line);
                            }
                            if (listener != null) {
                                if (clazz == null) {
                                    listener.onResult(stringBuffer.toString());
                                } else {
                                    Gson gson = new Gson();
                                    gson.fromJson(stringBuffer.toString(),clazz);
                                }
                            }

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
