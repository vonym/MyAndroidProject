package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bean.Book;
import com.example.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vonym on 17-1-3.
 */

public class BookDao {
    private SqliteHelper helper;
    private SQLiteDatabase db;

    public BookDao(Context context) {
        helper = new SqliteHelper(context);
    }

    //添加图书
    public void addBook(Book book) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", book.getName());
        values.put("price", book.getPrice());
        db.insert("tb1_book", null, values);
        Log.v("BookDao", "插入数据成功");
    }

    //删除图书
    public void deleteBook(String name) {
        db = helper.getWritableDatabase();
        db.delete("tb1_book", "name=?", new String[]{name});
        Log.v("BookDao", "删除数据成功");
    }

    //根据书名查询书的id
    public int findIdByName(String name) {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("tb1_book", new String[]{"_id"}, "name=?", new String[]{name}, null, null, null);
        int id = 0;
        while (cursor.moveToNext())
            id = cursor.getInt(0);
        return id;
    }

    public List<Book> findBookByName(String name) {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("tb1_book", null, "name=?", new String[]{name}, null, null, null);
        List<Book> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String book_name = cursor.getString(1);
            double book_price = cursor.getDouble(2);
            Book book = new Book(book_name, book_price);
            list.add(book);
        }
        return list;
    }

    //修改图书
    public void changeBook(String oldName, Book newBook) {
        db = helper.getWritableDatabase();
        int id = findIdByName(oldName);
        ContentValues values = new ContentValues();
        values.put("name", newBook.getName());
        values.put("price", newBook.getPrice());
        db.update("tb1_book", values, "_id=?", new String[]{id + ""});
        Log.v("BookDao", "修改数据成功");
    }

    //查询图书
    public List<Book> findAllBook() {
        List<Book> list = new ArrayList<>();
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("tb1_book", null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            double price = cursor.getDouble(cursor.getColumnIndex("price"));
            Book book = new Book(name, price);
            list.add(book);
        }
        return list;
    }

    public boolean isExist(String name) {
        for (Book book : findAllBook()) {
            if (name.equals(book.getName())) {
                return true;
            }
        }
        return false;
    }
}
