package com.example.iostream;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.briup.bean.User;
import com.briup.db.SqliteHelper;
import com.briup.db.UserDao;
import com.example.iostream.R;

/**
 * Created by vonym on 16-12-30.
 */

public class SeventhActivity extends Activity {
    //private SqliteHelper helper;
    //private SQLiteDatabase db;
    private EditText mEt_userName, mEt_passwd;
    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seventh_layout);
        //实例化数据库帮助类，并没有在应用程序中创建数据库文件
        //helper = new SqliteHelper(this, "briup.db", null, 1);
        //实例化数据库对象，在应用程序中创建数据库文件
        //db = helper.getReadableDatabase();
        mEt_userName = (EditText) findViewById(R.id.seventh_et_username);
        mEt_passwd = (EditText) findViewById(R.id.seventh_et_passwd);
        dao = new UserDao(this);
    }

    /**
     * 登录按钮监听
     *
     * @param view
     */
    public void login(View view) {
        String userName = mEt_userName.getText().toString();
        String passwd = mEt_passwd.getText().toString();
        User user = dao.queryDataByName(userName);
        if (user == null) {
            ToastUtils.showToast(this, "该用户不存在");
        } else {
            if (user.getPasswd().equals(passwd)) {
                ToastUtils.showToast(this, "登录成功!");
            } else {
                ToastUtils.showToast(this, "密码错误!!!");
            }
        }
    }

    /**
     * 注册按钮监听
     *
     * @param view
     */
    public void register(View view) {
        String userName = mEt_userName.getText().toString();
        String passwd = mEt_passwd.getText().toString();
        User user = dao.queryDataByName(userName);
        if (user == null) {
            dao.addData(new User(userName, passwd));
            ToastUtils.showToast(this, "注册成功");
        } else {
            ToastUtils.showToast(this, "注册失败");
        }
    }
}
