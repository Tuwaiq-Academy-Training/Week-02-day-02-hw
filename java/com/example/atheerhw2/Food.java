package com.example.atheerhw2;

import java.time.LocalDate;


public class Food {
    private Integer ID;
    private  String Name ;
    private  double Price ;
    private LocalDate Date ;
    private Integer Quantity;

    public Food(Integer ID, String name, double price, LocalDate Date, Integer quantity) {
        this.ID = ID;
        Name = name;
        Price = price;
        Date = Date;
        Quantity = quantity;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public LocalDate getExpiryDate() {
        return Date;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        Date = expiryDate;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }
}
