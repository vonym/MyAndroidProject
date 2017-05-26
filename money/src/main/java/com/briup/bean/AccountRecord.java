package com.briup.bean;

import java.util.Date;

/**
 * Created by vonym on 17-1-10.
 */

public class AccountRecord {
    private String etype;
    private String time;
    private String content;
    private float money;
    private int img;
    private int user_id;

    public AccountRecord(String etype, String time, String content, float money,int img,int user_id) {
        this.etype = etype;
        this.time = time;
        this.content = content;
        this.money = money;
        this.img=img;
        this.user_id=user_id;
    }

    public String getEtype() {
        return etype;
    }

    public void setEtype(String etype) {
        this.etype = etype;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
