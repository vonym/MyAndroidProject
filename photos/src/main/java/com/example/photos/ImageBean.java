package com.example.photos;

/**
 * Created by yumao on 17-2-27.
 */

public class ImageBean {
    private String name;
    private int img;
    private String type;

    public ImageBean() {
    }

    public ImageBean(String name, int img, String type) {
        this.name = name;
        this.img = img;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
