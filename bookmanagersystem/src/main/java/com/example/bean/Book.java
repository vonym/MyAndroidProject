package com.example.bean;

import java.io.Serializable;

/**
 * Created by vonym on 17-1-3.
 */

public class Book implements Serializable {
    private String name;
    private double price;

    public Book() {
        super();
    }

    public Book(String name, double price) {
        super();
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
