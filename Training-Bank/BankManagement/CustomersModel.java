package com.example.hw22.BankManagement;

public class CustomersModel {
    private String id;
    private String username;
    private Integer balance;

    public CustomersModel(String id, String username, Integer balance) {
        this.id = id;
        this.username = username;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
