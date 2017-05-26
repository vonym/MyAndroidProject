package com.example.photos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yumao on 17-2-27.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    private int[] imgs = {R.drawable.anni, R.drawable.huli2, R.drawable.lakesi, R.drawable.lvbu,
            R.drawable.jianmo, R.drawable.qinnv, R.drawable.renma, R.drawable.sunwukong};

    public SqliteHelper(Context context) {
        super(context, "yumao.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tbl_img(_id integer primary key autoincrement,name,img,type)");
        db.execSQL("insert into tbl_img(name,img,type) values('安妮'," + imgs[0] + ",'人物')");
        db.execSQL("insert into tbl_img(name,img,type) values('阿狸'," + imgs[1] + ",'人物')");
        db.execSQL("insert into tbl_img(name,img,type) values('拉克丝'," + imgs[2] + ",'风景')");
        db.execSQL("insert into tbl_img(name,img,type) values('吕布'," + imgs[3] + ",'风景')");
        db.execSQL("insert into tbl_img(name,img,type) values('剑魔'," + imgs[4] + ",'动漫')");
        db.execSQL("insert into tbl_img(name,img,type) values('琴女'," + imgs[5] + ",'动漫')");
        db.execSQL("insert into tbl_img(name,img,type) values('人马'," + imgs[6] + ",'动物')");
        db.execSQL("insert into tbl_img(name,img,type) values('悟空'," + imgs[7] + ",'动物')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
