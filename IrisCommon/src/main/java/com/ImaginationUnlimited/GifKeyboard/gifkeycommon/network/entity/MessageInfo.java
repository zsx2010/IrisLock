package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity;

import java.io.Serializable;

/**
 * Created by alpha on 16/6/3.
 */
public class MessageInfo implements Serializable {
    private String id;
    private String type;
    private String time;
    private String isRead;
    private String userPhoto;
    private String userName;
    private String commentContent;
    private String commentImage;
    private String gifImage;

    public String getGifImage() {
        return gifImage;
    }

    public String getCommentImage() {
        return commentImage;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public String getIsRead() {
        return isRead;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setCommentImage(String commentImage) {
        this.commentImage = commentImage;
    }

    public void setGifImage(String gifImage) {
        this.gifImage = gifImage;
    }
}
