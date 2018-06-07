package com.zztl.mvpdemo.model;


/**
 * Created by yannable on 2018/1/31.
 */

public class BaseResponseBean<T> {
    public final static int OK = 0;//成功
    public int code;
    public T data;
    public String message;

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }


}
