package com.example.springhw22;

public class Quantity {
    private int ID;
    private int quantity;

    public Quantity(int ID, int quantity) {
        this.ID = ID;
        this.quantity = quantity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuant(int quantity) {
        this.quantity = quantity;
    }
}
