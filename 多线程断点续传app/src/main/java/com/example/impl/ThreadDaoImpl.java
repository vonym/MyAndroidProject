package com.example.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bean.ThreadInfo;
import com.example.dao.ThreadDao;
import com.example.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ThreadDaoImpl implements ThreadDao {

    private DBHelper dbHelper;

    public ThreadDaoImpl(Context context) {
        dbHelper = DBHelper.getInstance(context);
    }

    @Override
    public synchronized void insertThread(ThreadInfo threadInfo) {
        // TODO Auto-generated method stub
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(
                "insert into thread_info(thread_id,url,start,end,finished) values(?,?,?,?,?)",
                new Object[]{threadInfo.getThread_Id(), threadInfo.getUrl(),
                        threadInfo.getStart(), threadInfo.getEnd(),
                        threadInfo.getFinished()});
        db.close();
    }

    @Override
    public synchronized void updateThread(String url, int thread_id, int finished) {
        // TODO Auto-generated method stub
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(
                "update thread_info set finished=? where url=? and thread_id=?",
                new Object[]{finished, url, thread_id});
        db.close();
    }

    @Override
    public synchronized void deleteThread(String url) {
        // TODO Auto-generated method stub
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from thread_info where url=?",
                new Object[]{url});
        db.close();
    }

    @Override
    public List<ThreadInfo> selectThread(String url) {
        List<ThreadInfo> list = new ArrayList<ThreadInfo>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from thread_info where url=?",
                new String[]{url});
        while (cursor.moveToNext()) {
            ThreadInfo threadInfo = new ThreadInfo();
            threadInfo.setThread_Id(cursor.getInt(cursor
                    .getColumnIndex("thread_id")));
            threadInfo.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            threadInfo.setStart(cursor.getInt(cursor.getColumnIndex("start")));
            threadInfo.setEnd(cursor.getInt(cursor.getColumnIndex("end")));
            threadInfo.setFinished(cursor.getInt(cursor
                    .getColumnIndex("finished")));
            list.add(threadInfo);
        }
        cursor.close();
        db.close();
        return list;
    }

    @Override
    public boolean isExists(String url, int thread_id) {
        // TODO Auto-generated method stub
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from thread_info where url=? and thread_id=?",
                new String[]{url, thread_id + ""});
        boolean exists = cursor.moveToNext();
        return exists;
    }
}
