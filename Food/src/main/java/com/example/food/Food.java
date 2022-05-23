package com.example.food;

import java.time.LocalDate;
import java.util.Date;

public class Food {
    private String id;
    private String name;
    private Date expiry_dat;
    private int quantity;

    public Food(String id, String name, Date expiry_dat, int quantity) {
        this.id = id;
        this.name = name;
        this.expiry_dat = expiry_dat;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiry_dat() {
        return expiry_dat;
    }

    public void setExpiry_dat(Date expiry_dat) {
        this.expiry_dat = expiry_dat;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
