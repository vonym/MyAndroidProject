package com.example.iostream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/**
 * SD卡数据读取
 * Created by vonym on 16-12-29.
 */

public class SecondActivity extends Activity {
    private EditText mEt_writeData, mEt_readData;
    final String FILE_NAME = "/feng.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEt_writeData = (EditText) findViewById(R.id.main_et_writedata);
        mEt_readData = (EditText) findViewById(R.id.main_et_readdata);

    }

    public void write(View view) {
        String content = mEt_writeData.getText().toString();
        //判断当前系统是否有sd卡，并且程序具有读取sd卡的权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //获取SD卡目录
            File SdDir = Environment.getExternalStorageDirectory();
            //获取写入的文件
            File targetFile = new File(SdDir.getAbsolutePath());
            //String targetFile = SdDir.getAbsoluteFile() + FILE_NAME;
            try {
                //输出内容到SD卡目录下指定的文件中
                //FileOutputStream fos=new FileOutputStream(SdDir+FILE_NAME);
                RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
                raf.seek(targetFile.length());
                //开始向文件中写入内容
                raf.write(content.getBytes());
                //关闭流
                raf.close();
                ToastUtils.showToast(this, "写入成功");
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showToast(this, "写入失败");
            }
        } else
            ToastUtils.showToast(this, "SD卡不不存在");
    }

    public void read(View view) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File SdDir = Environment.getExternalStorageDirectory();
            String targetFile = SdDir.getAbsolutePath() + FILE_NAME;
            try {
                FileInputStream fis = new FileInputStream(targetFile);
                BufferedReader buff = new BufferedReader(new InputStreamReader(fis));
                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = buff.readLine()) != null) {
                    sb.append(line);
                }
                buff.close();
                mEt_readData.setText(sb.toString());
                ToastUtils.showToast(this, "读取成功");
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showToast(this, "读取失败");
            }
        } else
            ToastUtils.showToast(this, "SD卡不不存在");
    }
}
