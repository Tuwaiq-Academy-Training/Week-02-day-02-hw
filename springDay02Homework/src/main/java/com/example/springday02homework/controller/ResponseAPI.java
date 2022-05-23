package com.example.springday02homework.controller;

public class ResponseAPI {

    private String message;

    public ResponseAPI(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
