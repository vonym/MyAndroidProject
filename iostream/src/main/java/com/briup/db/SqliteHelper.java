package com.briup.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by vonym on 16-12-30.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    /**
     * 子类继承父类时，如果父类不含有无参构造器，子类必须重写父类的构造方法
     *
     * @param context：上下文
     * @param name：数据库名
     * @param factory：cursor工厂对象
     * @param version：数据库版本号
     */
    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    public SqliteHelper(Context context) {
        super(context, "briup.db", null, 2);

    }

    /**
     * 当创建数据库时，执行该方法，仅仅执行一次
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("SqliteHelper", "执行onCreate()方法");
        String sql = "create table tb1_user(_id integer primary key autoincrement,name text,passwd varchar(10))";
        db.execSQL(sql);
    }

    /**
     * 当数据库升级时调用该方法
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("SqliteHelper", "执行onUpgrade()方法");
    }
}
