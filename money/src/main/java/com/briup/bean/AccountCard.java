package com.briup.bean;

/**
 * Created by vonym on 17-1-10.
 */

public class AccountCard {
    private String cardName;
    private float price;
    private int img;
    private int color;
    private int user_id;

    public AccountCard(String cardName, float price,int img,int color,int user_id) {
        this.cardName = cardName;
        this.price = price;
        this.img=img;
        this.color=color;
        this.user_id=user_id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
