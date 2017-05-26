package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库的帮助类
 *
 * @author vonym
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "download.db";
    public static final int DB_VERSION = 1;
    public static final String CREATE_TABLE = "create table thread_info(_id integer primary key autoincrement,thread_id integer,url text, start integer,end integer,finished integer)";
    public static final String DROP_TABLE = "drop table if thread_info exsits";
    private static DBHelper dbHelper = null;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        // TODO Auto-generated constructor stub
    }

    public static DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }

        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL(DROP_TABLE);
        db.execSQL(CREATE_TABLE);

    }

}
