package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity;

import java.io.Serializable;

/**
 * Created by alpha on 16/5/18.
 */
public class GifComment implements Serializable {
    private String id;
    private String userPhoto;
    private String userName;
    private String userId;
    private String content;
    private String image;
    private String time;
    private int likeNum;
    private int commentNum;
    private int isLike;
    private String atUserId;
    private String atUserName;

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public boolean isLike() {
        return isLike != 0;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public String getAtUserName() {
        return atUserName;
    }

    public String getAtUserId() {
        return atUserId;
    }
}
