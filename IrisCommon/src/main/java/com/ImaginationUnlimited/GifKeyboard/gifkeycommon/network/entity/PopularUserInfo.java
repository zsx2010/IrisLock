package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity;

import java.io.Serializable;

/**
 * Created by alpha on 16/5/24.
 */
public class PopularUserInfo implements Serializable {
    String id;
    String name;
    String userPhoto;

    public PopularUserInfo(String id, String name, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.userPhoto = avatarUrl;
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

    public String getphotoUrl() {
        return userPhoto;
    }

    public void setphotoUrl(String avatarUrl) {
        this.userPhoto = avatarUrl;
    }
}
