package com.example.springtask.model;

public class ResponseApi {

    private String res_message;

    public ResponseApi(String res_message) {
        this.res_message = res_message;
    }

    public String getRes_message() {
        return res_message;
    }

    public void setRes_message(String res_message) {
        this.res_message = res_message;
    }
}
