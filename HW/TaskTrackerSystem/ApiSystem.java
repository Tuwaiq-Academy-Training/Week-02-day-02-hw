package com.example.hw22.TaskTrackerSystem;

public class ApiSystem {
    private String responeMsg;
    private Integer status;

    public ApiSystem(String responeMsg, Integer status) {
        this.responeMsg = responeMsg;
        this.status = status;
    }
    public String getResponeMsg() {
        return responeMsg;
    }

    public void setResponeMsg(String responeMsg) {
        this.responeMsg = responeMsg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
