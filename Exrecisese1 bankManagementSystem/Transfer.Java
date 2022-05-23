package com.example.springday2ex1.model;

public class Transfer {

    private Integer Cin;
    private Integer Cof;
    private Integer amount;


    public Transfer(Integer cin, Integer cof, Integer amount) {
        Cin = cin;
        Cof = cof;
        this.amount = amount;
    }


    public Integer getCin() {
        return Cin;
    }

    public void setCin(Integer cin) {
        Cin = cin;
    }

    public Integer getCof() {
        return Cof;
    }

    public void setCof(Integer cof) {
        Cof = cof;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
