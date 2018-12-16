package com.server.http.entity;

public class AjaxReult {
    //失败描述
    private String message;

    //返回码，标识请求处理状态 1000为正常返回
    private int code;

    //返回数据
    private Object object;

    public AjaxReult(String message, int code, Object object) {
        this.message = message;
        this.code = code;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
