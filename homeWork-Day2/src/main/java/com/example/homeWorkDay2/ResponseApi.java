package com.example.homeWorkDay2;

public class ResponseApi {

    private String responeMsg;
    private Integer status;

    public ResponseApi(String responeMsg) {
        this.responeMsg = responeMsg;

    }
    public String getResponeMsg() {
        return responeMsg;
    }

    public void setResponeMsg(String responeMsg) {
        this.responeMsg = responeMsg;
    }

}
