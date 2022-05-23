package com.example.hw22.RestaurantsSystem;

public class Food {
    private String id;
    private String name;
    private Integer price;
    private String date;
    private Integer quantity;

    public Food(String id, String name, Integer price, String date, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
