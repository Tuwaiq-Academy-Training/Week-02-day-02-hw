package com.example.javaDay02.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Food {
    private int ID;
    private String name;
    private double price;
    private String expiryDate;
    private int quantity;

    public Food(int ID, String name, double price, String expiryDate, int quantity) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
