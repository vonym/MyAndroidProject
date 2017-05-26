package com.briup.bean;

import java.io.Serializable;

/**
 * Created by vonym on 16-12-30.
 */

public class User implements Serializable {
    private String name;
    private String passwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public User(String name, String passwd) {
        this.name = name;

        this.passwd = passwd;
    }

    public String toString() {
        return super.toString();
    }
}
