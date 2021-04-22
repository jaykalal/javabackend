package com.jayk.amazonapi;

import java.util.List;

public class CustomizedResponse<T>{
    private String msg;
    private T body;

    public CustomizedResponse() {

    }

    public CustomizedResponse(String msg, T body) {
        this.msg = msg;
        this.body = body;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
