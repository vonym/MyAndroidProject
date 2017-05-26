package com.example.httpurlconnectionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText et_username, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_psw);
    }

    public void doGet(View view) {
        final String username = et_username.getText().toString();
        final String password = et_password.getText().toString();
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://192.168.1.107:8080/BriupLoginServer/LoginServlet?userName=" + username + "&passWord=" + password);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.connect();
                    Log.v("msg", "连接成功");
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line = "";
                        StringBuffer stringBuffer = new StringBuffer();
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuffer.append(line);
                        }
                        bufferedReader.close();
                        final String s = stringBuffer.toString();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(s);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    connection.disconnect();
                }
            }
        }.start();
    }

    public void doPost(View view) {
        final String username = et_username.getText().toString();
        final String password = et_password.getText().toString();
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://192.168.1.107:8080/BriupLoginServer/LoginServlet");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
                    printWriter.write("userName" + "=" + username + "passWord" + "=" + password);
                    printWriter.flush();
                    printWriter.close();
                    connection.connect();
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line = "";
                        StringBuffer stringBuffer = new StringBuffer();
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuffer.append(line);
                        }
                        bufferedReader.close();
                        final String s = stringBuffer.toString();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(s);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    connection.disconnect();
                }
            }
        }.start();
    }
}
