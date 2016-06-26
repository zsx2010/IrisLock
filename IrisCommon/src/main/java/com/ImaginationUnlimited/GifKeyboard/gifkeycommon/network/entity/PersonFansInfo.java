package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity;

import java.io.Serializable;

/**
 * Created by suiyue on 16/5/27.
 */
public class PersonFansInfo implements Serializable{
    private String id;
    private String isFollow;
    private String name;
    private String userPhoto;
    private String level;
    private String score;
    private String followNum;
    private String fansNum;
    private String desc;


    public String getFansNum() {
        return fansNum;
    }

    public void setFansNum(String fansNum) {
        this.fansNum = fansNum;
    }

    public String getFollowNum() {
        return followNum;
    }

    public void setFollowNum(String followNum) {
        this.followNum = followNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(String isFollow) {
        this.isFollow = isFollow;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "PersonFansInfo{" +
                "desc='" + desc + '\'' +
                ", id='" + id + '\'' +
                ", isFollow='" + isFollow + '\'' +
                ", name='" + name + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                ", level='" + level + '\'' +
                ", score='" + score + '\'' +
                ", followNum='" + followNum + '\'' +
                ", fansNum='" + fansNum + '\'' +
                '}';
    }
}
