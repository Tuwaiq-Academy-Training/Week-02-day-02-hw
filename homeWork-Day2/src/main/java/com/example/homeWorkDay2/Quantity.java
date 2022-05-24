package com.example.homeWorkDay2;


public class Quantity {
    private int ID;
    private int quant;

    public Quantity(int ID, int quant) {
        this.ID = ID;
        this.quant = quant;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }
}
