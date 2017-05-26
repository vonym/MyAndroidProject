package com.example.contentprovider;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private ContentResolver resolver;
    //uri 的协议 content
    private Uri uri = Uri.parse("content://www.briup.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resolver = getContentResolver();//onCreate()方法执行

    }

    //点击添加按钮的监听
    public void add(View view) {
        resolver.insert(uri, null);
    }

    //点击删除按钮的监听
    public void delete(View view) {
        resolver.delete(uri, null, null);
    }

    //点击修改按钮的监听
    public void change(View view) {
        resolver.update(uri, null, null, null);
    }

    //点击查询按钮的监听
    public void query(View view) {
        resolver.query(uri, null, null, null, null, null);
    }
}
