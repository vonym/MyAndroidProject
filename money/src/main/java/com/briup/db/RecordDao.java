package com.briup.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.briup.bean.AccountRecord;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by vonym on 17-1-10.
 */

public class RecordDao {
    private SqliteHelper helper;
    private SQLiteDatabase db;
    private List<AccountRecord> records;
    private CardDao cardDao;

    public RecordDao(Context context) {
        helper = new SqliteHelper(context);
        records = new ArrayList<>();
        cardDao = new CardDao(context);
    }

    public void insertRecord(AccountRecord record) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type", record.getEtype());
        values.put("time", record.getTime());
        values.put("content", record.getContent());
        values.put("money", record.getMoney());
        values.put("img", record.getImg());
        values.put("account_id", record.getUser_id());
        db.insert("tbl_record", null, values);
    }

    public void deleteRecord(String content) {
        db = helper.getWritableDatabase();
        db.delete("tbl_record", "content=?", new String[]{content});
    }

    public void updateRecord(String content, String name, float money, String time) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("time", time);
        values.put("money", money);
        values.put("account_id", cardDao.findIdByName(name));
        db.update("tbl_record", values, "content=?", new String[]{content});
    }

    public List<AccountRecord> queryAllRecord() throws Exception {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("tbl_record", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            float money = Float.parseFloat((cursor.getString(cursor.getColumnIndex("money"))));
            int img = cursor.getInt(cursor.getColumnIndex("img"));
            int user_id = cursor.getInt(cursor.getColumnIndex("account_id"));
            records.add(new AccountRecord(type, time, content, money, img, user_id));
        }
        return records;
    }
}
