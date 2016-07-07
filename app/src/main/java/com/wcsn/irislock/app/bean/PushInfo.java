package com.wcsn.irislock.app.bean;

/**
 * Created by suiyue on 2016/7/7 0007.
 */
public class PushInfo<T> {
    private int code;
    private String msg;
    private T date;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PushInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }
}
