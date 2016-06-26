package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.data;

/**
 * Created by suiyue on 16/6/3.
 */
public class DataAnotherPersonalInfo implements BaseData {

    private String name;
    private String userPhoto;
    private String followNum;
    private String fansNum;
    private String level;
    private String desc;
    private String isFollow;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

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

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    @Override
    public String toString() {
        return "DataAnotherPersonalInfo{" +
                "desc='" + desc + '\'' +
                ", name='" + name + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                ", followNum='" + followNum + '\'' +
                ", fansNum='" + fansNum + '\'' +
                ", level='" + level + '\'' +
                ", isFollow='" + isFollow + '\'' +
                '}';
    }
}
