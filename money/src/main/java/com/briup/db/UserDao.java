package com.briup.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.briup.bean.User;
import com.briup.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vonym on 17-1-5.
 */

public class UserDao {
    private SqliteHelper helper;
    private SQLiteDatabase db;
    private List<User> users;

    public UserDao(Context context) {
        helper = new SqliteHelper(context);
        users = new ArrayList<>();
    }

    public int findIdByName(String name) {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("tbl_user", new String[]{"_id"}, "name=?", new String[]{name}, null, null, null);
        int id=0;
        while (cursor.moveToNext()) {
             id= cursor.getInt(cursor.getColumnIndex("_id"));
        }
        return id;
    }

    public void insertUser(User user) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("psw", user.getPsw());
        db.insert("tbl_user", null, values);
    }

    public void updatePsw(String name, String psw) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("psw", psw);
        db.update("tbl_user", values, "name=?", new String[]{name});
    }

    public void updateName(String oldName, String newName) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", newName);
        db.update("tbl_user", values, "name=?", new String[]{oldName});
    }

    public List<User> queryAllUser() {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("tbl_user", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String psw = cursor.getString(cursor.getColumnIndex("psw"));
            User user = new User(name, psw);
            users.add(user);
        }
        return users;
    }

    public boolean isNameExist(String name) {
        for (User user : queryAllUser()) {
            if (user.getName().equals(name))
                return true;
        }
        return false;
    }

    public boolean isPswTrue(String name, String psw) {
        for (User user : queryAllUser()) {
            if (user.getPsw().equals(psw) && user.getName().equals(name))
                return true;
        }
        return false;
    }
}
