package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.data;

/**
 * Created by suiyue on 16/5/20.
 */
public class DataPersonalInfo implements BaseData {
    private String name;
    private String userPhoto;
    private String followNum;
    private String fansNum;
    private String level;
    private String score;
    private String desc;

    private static DataPersonalInfo dataPersonalInfo;

    private DataPersonalInfo() {}

    public static DataPersonalInfo getInatance() {
        if (dataPersonalInfo == null) {
            dataPersonalInfo = new DataPersonalInfo();
        }
        return dataPersonalInfo;
    }

    public String getFollowNum() {
        return followNum;
    }
    public void setFollowNum(String followNum) {
        this.followNum = followNum;
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

    public String getFansNum() {
        return fansNum;
    }

    public void setFansNum(String fansNum) {
        this.fansNum = fansNum;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "DataPersonalInfo{" +
                "desc='" + desc + '\'' +
                ", name='" + name + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                ", followNum='" + followNum + '\'' +
                ", fansNum='" + fansNum + '\'' +
                ", level='" + level + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
