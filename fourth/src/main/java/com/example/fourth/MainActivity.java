package com.example.fourth;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

/**
 * Sqlite数据库
 * Created by vonym on 16-12-30.
 */

public class MainActivity extends Activity {
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 第一个参数：文件路径（绝对路径）
         * 第二个参数：Cursor工厂类对象，与ResultSet类似.值一般为空，表示采用系统默认的工厂而不是没有
         */
        db = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + "/my.db", null);
        ToastUtils.showToast(this, "数据库创建成功");
        //可以执行任何sql语句，不返回结果集
        /**
         * Sqlite数据类型：null integer text(文本型) real(浮点型)
         */
        db.execSQL("create table tb1_user(_id integer primary key autoincreament,name text,passwd varchar(10))");//执行没有占位符的sql语句
        ToastUtils.showToast(this, "表创建成功");
        db.execSQL("insert into tb1_user(name,passwd) values (?,?)", new String[]{"zhangsan", "123"});//执行有占位符的sql语句
        addData();
        ToastUtils.showToast(this, "插入成功");
        //执行查询带有占位符的查询语句
        Cursor cursor = db.rawQuery("select * from tb1_user where _id=?", new String[]{"1"});
        queryData();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String passwd = cursor.getString(cursor.getColumnIndex("passwd"));
            Log.v("MainActivity", id + " " + name + "    " + passwd);
        }
        //修改数据库数据
        db.execSQL("update tb1_user set name='lisi' where _id =?", new String[]{"1"});
        updateData();
        //删除数据库数据
        db.execSQL("delete from tb1_user where _id= ?", new String[]{"2"});
    }

    private void updateData() {
        /**
         * 第一个参数table：表的名字
         * 第二个参数values：ContentValues的值
         * 第三个参数whereClause：查询条件
         * 第四个参数whereArgs：占位符
         */
        ContentValues values = new ContentValues();
        values.put("name", "briup");
        values.put("passwd", "345");
        db.update("tb1_user", values, "_id=?", new String[]{"1"});
        deleteData();
    }

    private void deleteData() {
        /**
         * 第一个参数table：表的名字
         * 第二个参数whereClause：查询条件
         * 第三个参数whereArgs：占位符的值
         */
        db.delete("tb1_user", "_id=?", new String[]{"1"});
    }

    private void queryData() {
        /**
         * 第一个参数distinct：是否去除重复的数据
         * 第二个参数table：表名
         * 第三个参数colnums：要查询的字段名，类似于select id，。。。
         * 第四个参数selection： 查询条件，类似于name=？
         * 第五个参数selectionArgs：占位符的值
         * 第六个参数groupBy：分组查询
         * 第七个参数having：组函数的过滤条件
         * 第八个参数orderBy:按照制定的顺序显示数据 _id desc降序      asc升序
         * 第九个参数limit：分页查询 “5,10”
         */
        Cursor cursor = db.query(false, "tb1_user", null, "_id=?", new String[]{"1"}, null, null, "_id desc", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String passwd = cursor.getString(cursor.getColumnIndex("passwd"));
            Log.v("FourthAcvtivity", id + "    " + name + "    " + passwd);
        }
    }

    private void addData() {
        /**
         * 第一个参数：表的名字
         * 第二个参数：字段的值永远为空值
         * 第三个参数：ContentValues对象
         */
        ContentValues values = new ContentValues();
        values.put("name", "briup");
        values.put("psswd", "123");
        db.insert("tb1_user", null, values);
    }
}
