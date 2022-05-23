package com.example.assignment6.model;

public class Food {
    private String id;
    private String name;
    private Integer price;
    private String expiryDate;
    private Integer quantity;

    public Food(String id, String name, Integer price, String expiryDate, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
