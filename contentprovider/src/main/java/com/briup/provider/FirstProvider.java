package com.briup.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.briup.db.SqliteHelper;

/**
 * Created by vonym on 17-1-6.
 */

public class FirstProvider extends ContentProvider {
    private SqliteHelper helper;
    private SQLiteDatabase db;

    /**
     * 该方法只执行一次
     *
     * @return
     */
    @Override
    public boolean onCreate() {
        helper = new SqliteHelper(getContext());
        Log.v("FirstProvider", "执行了onCreate（）");
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.v("FirstProvider", "执行了query（）");
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("tbl_user", projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.v("FirstProvider", "执行了getType（）");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.v("FirstProvider", "执行了insert（）");
        db = helper.getWritableDatabase();
        db.insert("tbl_user", null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.v("FirstProvider", "执行了delete（）");
        db = helper.getWritableDatabase();
        int i = db.delete("tbl_user", selection, selectionArgs);
        return i;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.v("FirstProvider", "执行了update（）");
        db = helper.getWritableDatabase();
        int i = db.update("tbl_user", values, selection, selectionArgs);
        return i;
    }
}
