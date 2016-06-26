package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity;

import java.io.Serializable;

/**
 * Created by alpha on 16/6/1.
 */
public class FollowUser implements Serializable {
    private String id;
    private String userPhoto;
    private String name;
    private String level;
    private String score;
    private String desc;
    private String followNum;
    private String fansNum;
    private String isFollow;

    public String getId() {
        return id;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getScore() {
        return score;
    }

    public String getDesc() {
        return desc;
    }

    public String getFollowNum() {
        return followNum;
    }

    public String getFansNum() {
        return fansNum;
    }

    public boolean getIsFollow() {
        return !isFollow.equals("0");
    }

    public void setIsFollow(boolean isFollow) {
        this.isFollow = isFollow ? "1" : "0";
    }
}
