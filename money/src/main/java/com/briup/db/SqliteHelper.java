package com.briup.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vonym on 17-1-5.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    public SqliteHelper(Context context) {
        super(context, "briup.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tbl_user(_id integer primary key autoincrement,name,psw)");
        db.execSQL("create table tbl_account(_id integer primary key autoincrement,name,price,img,color,user_id references tbl_user(_id))");
        db.execSQL("create table tbl_record(_id integer primary key autoincrement,type,time,content,money,img,account_id references tnl_account(_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
