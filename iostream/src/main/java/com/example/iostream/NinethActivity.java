package com.example.iostream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by vonym on 16-12-29.
 */

public class NinethActivity extends Activity {
    private EditText mEt_readData, mEt_writeData;
    private final String FILE_NAME = "feng.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEt_readData = (EditText) findViewById(R.id.main_et_readdata);
        mEt_writeData = (EditText) findViewById(R.id.main_et_writedata);

    }

    public void write(View view) {
        String content = mEt_readData.getText().toString();
        try {
            /**
             * 创建文件输出流
             * FILE_NAME：文件名
             * MODE_APPEND：文件写入模式--
             *              1.MODE_PRIVATE:该文件只允许该应用程序读取
             *              2.MODE_APPEND:该文件采取追加的方式
             *              3.MODE_WORLD_READABLE:允许其他应用程序读该文件
             *              4.MODE_WORLD_WRITEABLE:允许其他应用程序写该文件
             *                  Android4.2之后不推荐使用3.4两种方式
             */
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND);
            fos.write(content.getBytes());
            fos.flush();
            fos.close();
            ToastUtils.showToast(this, "写入成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showToast(this, "写入失败");
        }
    }

    public void read(View view) {
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            mEt_readData.setText(sb.toString());
            ToastUtils.showToast(this, "读取成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showToast(this, "读取失败");
        }
    }

}
