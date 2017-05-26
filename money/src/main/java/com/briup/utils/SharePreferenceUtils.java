package com.briup.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.briup.db.UserDao;

/**
 * Created by vonym on 17-1-6.
 */

public class SharePreferenceUtils {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private boolean isLogin = false;
    private UserDao userDao;

    public SharePreferenceUtils(Context context) {
        preferences = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        editor = preferences.edit();
        userDao = new UserDao(context);
    }

    public boolean isLogin() {
        String name = preferences.getString("name", "");
        String psw = preferences.getString("psw", "");
        if (userDao.isNameExist(name) && userDao.isPswTrue(name, psw)) {
            isLogin = true;
        }
        return isLogin;
    }

    public void put(String key, String valus) {
        editor.putString(key, valus).commit();
    }

    public String getName() {
        return preferences.getString("name", "");
    }

    public String getPsw() {
        return preferences.getString("psw", "");
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
    public boolean getLogin(){
        return isLogin;
    }
}