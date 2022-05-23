package com.example.week2day2.model;

public class ResponseApi {
    private String response_msg;

    public ResponseApi(String response_msg) {
        this.response_msg = response_msg;
    }

    public String getResponse_msg() {
        return response_msg;
    }

    public void setResponse_msg(String response_msg) {
        this.response_msg = response_msg;
    }
}

