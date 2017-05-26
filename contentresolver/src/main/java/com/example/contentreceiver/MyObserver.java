package com.example.contentreceiver;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

/**
 * Created by vonym on 17-1-6.
 */

public class MyObserver extends ContentObserver {
    private ContentResolver resolver;

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public MyObserver(Handler handler) {
        super(handler);
    }

    public MyObserver(Handler handler, ContentResolver resolver) {
        super(handler);
        this.resolver = resolver;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Cursor cursor = resolver.query(Uri.parse("content://www.briup.com"), null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String passwd = cursor.getString(cursor.getColumnIndex("passwd"));
            Log.v("MyObserver", name + ":" + passwd);
        }
    }
}
