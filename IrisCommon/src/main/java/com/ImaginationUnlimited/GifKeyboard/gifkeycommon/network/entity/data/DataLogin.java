package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.data;

/**
 * @author wangheng on 2016-03-29 11:23
 */
public class DataLogin implements BaseData{
    private String id;
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "DataLogin{" +
                "id='" + id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
