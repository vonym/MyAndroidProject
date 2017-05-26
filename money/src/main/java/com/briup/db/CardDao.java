package com.briup.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.briup.bean.AccountCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vonym on 17-1-10.
 */

public class CardDao {
    private SqliteHelper helper;
    private SQLiteDatabase db;
    private List<AccountCard> cards;

    public CardDao(Context context) {
        helper = new SqliteHelper(context);
        cards = new ArrayList<>();
    }

    public void insertCard(AccountCard card) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", card.getCardName());
        values.put("price", card.getPrice());
        values.put("img", card.getImg());
        values.put("color", card.getColor());
        values.put("user_id", card.getUser_id());
        db.insert("tbl_account", null, values);
    }

    public void deleteCard(String name) {
        db = helper.getWritableDatabase();
        db.delete("tbl_account", "name=?", new String[]{name});
    }

    public void updateCardPrice(String name, float price) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price", price);
        db.update("tbl_account", values, "name=?", new String[]{name});
    }

    public int findIdByName(String name) {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("tbl_account", new String[]{"_id"}, "name=?", new String[]{name}, null, null, null);
        int id = 0;
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex("_id"));
        }
        return id;
    }

    public String findNameById(int id) {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("tbl_account", new String[]{"name"}, "_id=?", new String[]{id + ""}, null, null, null);
        String name = "";
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("name"));
        }
        return name;
    }

    public List<AccountCard> queryAllCard() {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("tbl_account", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            float price = cursor.getFloat(cursor.getColumnIndex("price"));
            int img = cursor.getInt(cursor.getColumnIndex("img"));
            int color = cursor.getInt(cursor.getColumnIndex("color"));
            int user_id = cursor.getInt(cursor.getColumnIndex("user_id"));
            cards.add(new AccountCard(name, price, img, color, user_id));
        }
        return cards;
    }
}
