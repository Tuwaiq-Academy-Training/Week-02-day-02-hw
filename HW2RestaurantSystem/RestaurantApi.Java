package com.example.springrestaurant.model;

public class RestaurantApi {

    private String resp_message;

    public RestaurantApi(String resp_message) {
        this.resp_message = resp_message;
    }

    public String getResp_message() {
        return resp_message;
    }

    public void setResp_message(String resp_message) {
        this.resp_message = resp_message;
    }
}
