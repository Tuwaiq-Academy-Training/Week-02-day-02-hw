package com.demo.HW2.task;

public class TaskAPI {
    private String message;
    private Integer status;

    public TaskAPI(String responeMsg, Integer status) {
        this.message = responeMsg;
        this.status = status;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String responeMsg) {
        this.message = responeMsg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
