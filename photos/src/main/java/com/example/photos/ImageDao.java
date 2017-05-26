package com.example.photos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yumao on 17-2-27.
 */

public class ImageDao {
    private SqliteHelper helper;
    private SQLiteDatabase db;
    List<ImageBean> list_imgs;

    public ImageDao(Context context) {
        helper = new SqliteHelper(context);
        list_imgs = new ArrayList<>();
    }

    //根据img删除图片
    public void deleteImg(int img) {
        db = helper.getWritableDatabase();
        db.delete("tbl_img", "img=?", new String[]{img + ""});
    }

    //根据img修改type
    public void updateImg(int img, String type) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type", type);
        db.update("tbl_img", values, "img=?", new String[]{img + ""});
    }

    //根据type查询所有的图片
    public List<ImageBean> queryImg(String type) {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("tbl_img", null, "type=?", new String[]{type}, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int img = cursor.getInt(cursor.getColumnIndex("img"));
            list_imgs.add(new ImageBean(name, img, type));
        }
        return list_imgs;
    }
}
