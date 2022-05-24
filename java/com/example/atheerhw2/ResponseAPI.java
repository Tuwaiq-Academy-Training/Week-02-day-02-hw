package com.example.atheerhw2;

public class ResponseAPI {
    String Mssg;
    public ResponseAPI(String Mssg) {
        this.Mssg=Mssg;
    }

    public String getMssg() {
        return Mssg;
    }

    public void setMssg(String mssg) {
        Mssg = mssg;
    }
}
