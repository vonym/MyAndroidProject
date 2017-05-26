package com.example.contentreceiver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObservable;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private ContentResolver resolver;
    //uri 的协议 content
    private Uri uri = Uri.parse("content://www.briup.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resolver = getContentResolver();
        /**注册监听
         * uri:uri对像
         * notifyfordescedants:如果为true，表示完全匹配uri，如果为false，表示模糊匹配uri
         * observer：obverser对象
         */
        resolver.registerContentObserver(uri, true, new MyObserver(new Handler()));
    }

    //点击添加按钮的监听
    public void add(View view) {
        ContentValues values = new ContentValues();
        values.put("name", "briup");
        values.put("passwd", "123");
        resolver.insert(uri, values);
    }

    //点击删除按钮的监听
    public void delete(View view) {
        resolver.delete(uri, "_id=?", new String[]{"1"});
    }

    //点击修改按钮的监听
    public void change(View view) {
        ContentValues values = new ContentValues();
        values.put("name", "123");
        values.put("passwd", "123");
        resolver.update(uri, values, "_id=?", new String[]{2 + ""});
    }

    //点击查询按钮的监听
    public void query(View view) {
        Cursor cursor = resolver.query(uri, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String passwd = cursor.getString(cursor.getColumnIndex("passwd"));
            Log.v("MainActivity", name + "  :   " + passwd + "   ");
        }

    }
}
