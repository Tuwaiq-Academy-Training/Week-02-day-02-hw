package com.example.springrestaurant.model;
import java.time.LocalDate;

public class Food {
    private int id;
    private String name;
    private double price;
    private LocalDate expirydate;
    private int quantity;

    public Food(int id, String name, double price, LocalDate expirydate, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expirydate = expirydate;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(LocalDate expirydate) {
        this.expirydate = expirydate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}