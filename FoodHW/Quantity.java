package com.example.javaDay02.model;

public class Quantity {
    private String ID;
    private int quant;

    public Quantity(String  ID, int quant) {
        this.ID = ID;
        this.quant = quant;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }
}
