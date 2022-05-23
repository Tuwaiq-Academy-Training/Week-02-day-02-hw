package com.example.h02w02.models;

import java.util.Date;

public class Food {
    private String name, id;
    private double price;
    private int qty;
    private Date date;

    public Food(String name, String id, double price, int qty, Date date) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.qty = qty;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
