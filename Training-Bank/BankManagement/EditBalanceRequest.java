package com.example.hw22.BankManagement;

public class EditBalanceRequest {
    private String id;
    private Integer balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public int getSum(){
        return balance + balance;
    }
}
