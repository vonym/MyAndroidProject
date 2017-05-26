package com.example.iostream;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * sharePreference
 * Created by vonym on 16-12-29.
 */

public class ThirdAcitivity extends Activity {
    private EditText mEt_save, mEt_readData;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEt_save = (EditText) findViewById(R.id.main_et_writedata);
        mEt_readData = (EditText) findViewById(R.id.main_et_readdata);
        //实例化一个SharedPreferences对象
        /**
         * user_info:文件名
         * MODE_APPEND:文件创建模式
         */
        sp = getSharedPreferences("user_info", Context.MODE_APPEND);
    }

    public void write(View view) {
        String content = mEt_save.getText().toString();
        //创建一个可以写入数据的对象
        SharedPreferences.Editor edit = sp.edit();
        //写入数据
        edit.putString("content", content);
        edit.putString("username", "briup");
        edit.putBoolean("isLogin", false);
        //它不是同步的，先提交到内存，然后再提交到磁盘上
        //edit.apply();
        //提交数据，提交到内存的同时，提交到磁盘上
        edit.commit();
    }

    public void read(View view) {
        /**
         * 第一个参数key：
         * 第二参数：如果拿到的key为空，默认值
         */
        String content = sp.getString("content", "");
        String contens = sp.getString("contents", "===========");
        boolean isLogin = sp.getBoolean("isLogin", false);
        mEt_readData.setText(content + "  " + contens + "     " + isLogin + "     ");
    }
}
