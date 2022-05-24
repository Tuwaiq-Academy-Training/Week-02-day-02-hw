package com.demo.HW2.resturant;

import java.util.Date;

public class Food {
    private int id;
    private String name;
    private int price;
    private Date expiryDate;
    private int quantity;

    public Food(int id, String name, int price, Date expiryDate, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
