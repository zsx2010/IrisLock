package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.data;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.Gif;

import java.util.List;

/**
 * Created by someHui on 4/19/16.
 */
public class DataGifInfo implements BaseData {
    private String id;
    private String imgUrl;
    private String userPhoto;
    private String userId;
    private String userName;
    private int imgWidth;
    private int imgHeight;
    private List<String> tag;//标签数组
    private int isLike;//是否喜欢
    private int isFavorite;
    private int isFollow;
    private int likeNum;  //喜欢的人数
    private int viewNum;  //查看人数
    private int followNum;//follow  number
    private int shareNum;//share  number
    private int commentNum;//comment  number
    private int emojiId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public int getIs_like() {
        return isLike;
    }

    public boolean isLike() {
        return isLike != 0;
    }

    public void setIs_like(int is_like) {
        this.isLike = is_like;
    }

    public boolean isFavourite() {
        return isFavorite != 0;
    }

    public int getIs_favorite() {
        return isFavorite;
    }

    public void setIs_favorite(int is_favorite) {
        this.isFavorite = is_favorite;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getEmojiId() {
        return emojiId;
    }

    public void setEmojiId(int emojiId) {
        this.emojiId = emojiId;
    }


    public boolean isFollowed() {
        return isFollow != 0;
    }

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }

    public void setFollowed(int followed) {
        this.isFollow = followed;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getShareNum() {
        return shareNum;
    }

    public void setShareNum(int shareNum) {
        this.shareNum = shareNum;
    }

    public String getUserName() {
        return userName;
    }
}
