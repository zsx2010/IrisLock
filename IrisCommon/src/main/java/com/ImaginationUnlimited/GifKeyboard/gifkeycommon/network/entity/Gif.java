package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity;

import android.net.Uri;

import java.io.Serializable;

/**
 * Gif List界面显示的每一个Item
 *
 * @author wangheng on 2016-03-15 12:17
 */
public class Gif implements Serializable {
    private String id;
    private String imgUrl;
    private String userPhoto;
    private String userId;
    private int imgWidth;
    private int imgHeight;
    private String viewNum;
    private String commentNum;

    public int getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Gif{" +
                "id='" + id + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                ", userId='" + userId + '\'' +
                ", imgWidth=" + imgWidth +
                ", imgHeight=" + imgHeight +
                '}';
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static Uri uri(Gif gif) {
        if (gif == null || gif.getImgUrl() == null) {
            return null;
        }
        return Uri.parse(gif.getImgUrl());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gif gif = (Gif) o;

        if (id != null ? !id.equals(gif.id) : gif.id != null) return false;
        return !(imgUrl != null ? !imgUrl.equals(gif.imgUrl) : gif.imgUrl != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
        return result;
    }

    public String getView_num() {
        return viewNum;
    }

    public String getComment_num() {
        return commentNum;
    }

}
