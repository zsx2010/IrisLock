package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity;

/**
 * Created by someHui on 4/19/16.
 */
public class UserInfo {
    String id;
    String name;
    String avatarUrl;

    public UserInfo(String id, String name, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
