package com.briup.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.briup.bean.User;

/**
 * Created by vonym on 16-12-30.
 */

public class UserDao {
    private SqliteHelper helper;
    private SQLiteDatabase db;

    public UserDao(Context context) {
        helper = new SqliteHelper(context);
    }

    /**
     * 添加数据
     *
     * @param user：用户对象
     */
    public void addData(User user) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("passwd", user.getPasswd());
        db.insert("tb1_user", null, values);
        Log.v("UserDao", "插入数据成功");
    }

    /**
     * 删除数据
     *
     * @param user
     */
    public void deleteData(User user) {
        db = helper.getWritableDatabase();
        db.delete("tb1_user", "name=?", new String[]{user.getName()});
        Log.v("UserDao", "删除数据成功");
    }

    /**
     * 修改数据
     *
     * @param user
     */
    public void updateData(User user) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("passwd", user.getPasswd());
        db.update("tb1_user", values, "name=?", new String[]{user.getName()});
        Log.v("UserDao", "修改数据成功");
    }

    /**
     * 根据用户名查询用户数据
     *
     * @param name
     */
    public User queryDataByName(String name) {
        User user = null;
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("tb1_user", null, "name=?", new String[]{name}, null, null, null);
        while (cursor.moveToNext()) {
            String name1 = cursor.getString(cursor.getColumnIndex("name"));
            String passwd = cursor.getString(cursor.getColumnIndex("passwd"));
            user = new User(name1, passwd);
        }
        Log.v("UserDao", "查询数据成功");
        return user;
    }
}
