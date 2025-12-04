package com.billgenrator.BillGenrator.DTO;

public class BillResDTO {
    private ResponseDTO response;
    private String msg;

    public  BillResDTO(){}




    public BillResDTO setMsg(String msg) {
        this.msg = msg;
        return null;
    }
    public ResponseDTO getResponse() {
        return response;
    }

    public void setResponse(ResponseDTO response) {
        this.response = response;
    }

    public String getMsg() {
        return msg;
    }


}
