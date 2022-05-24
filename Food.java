package com.example.springhw22;

public class Food {
    //ID , Name , Price , Expiry date, Quantity

    private int id;
    private String name;
    private int price;
    private String expirydate;
    private int quantity;

    public Food(int id, String name, int price, String expirydate, int quantity) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public int getQuantity() {
        return quantity;    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
