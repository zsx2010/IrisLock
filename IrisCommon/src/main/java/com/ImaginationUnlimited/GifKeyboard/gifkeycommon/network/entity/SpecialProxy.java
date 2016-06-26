package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity;

import java.io.Serializable;

/**
 * Created by someHui on 16/5/6.
 */
public class SpecialProxy implements Serializable {
    private int id;
    private String name;
    private String desc;
    private String img;

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String image) {
        this.img = image;
    }

    public SpecialProxy() {
        super();
    }
}
