package com.example.HomeWork22;

import java.util.Date;

public class Food {
    private String ID;
    private String name;
    private int price;
    private Date date;
    private double Quantity;

    public Food() {
    }

    public Food(String ID, String name, int price, Date date, double quantity) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.date = date;
        this.Quantity = quantity;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }
}
