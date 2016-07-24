package com.wcsn.irislock.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import java.io.Serializable;

/**
 * Entity mapped to table "AUTHORIZE".
 */
public class Authorize implements Serializable{

    private Long id;
    /** Not-null value. */
    private String name;
    private boolean isAuthorize;
    private boolean isOpen;
    /** Not-null value. */
    private String openTime;
    /** Not-null value. */
    private String week;
    /** Not-null value. */
    private String authorizeImage;
    /** Not-null value. */
    private String date;
    /** Not-null value. */
    private String time;

    public Authorize() {
    }

    public Authorize(Long id) {
        this.id = id;
    }

    public Authorize(Long id, String name, boolean isAuthorize, boolean isOpen, String openTime, String week, String authorizeImage, String date, String time) {
        this.id = id;
        this.name = name;
        this.isAuthorize = isAuthorize;
        this.isOpen = isOpen;
        this.openTime = openTime;
        this.week = week;
        this.authorizeImage = authorizeImage;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getName() {
        return name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsAuthorize() {
        return isAuthorize;
    }

    public void setIsAuthorize(boolean isAuthorize) {
        this.isAuthorize = isAuthorize;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    /** Not-null value. */
    public String getOpenTime() {
        return openTime;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    /** Not-null value. */
    public String getWeek() {
        return week;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setWeek(String week) {
        this.week = week;
    }

    /** Not-null value. */
    public String getAuthorizeImage() {
        return authorizeImage;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setAuthorizeImage(String authorizeImage) {
        this.authorizeImage = authorizeImage;
    }

    /** Not-null value. */
    public String getDate() {
        return date;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setDate(String date) {
        this.date = date;
    }

    /** Not-null value. */
    public String getTime() {
        return time;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Authorize{" +
                "authorizeImage='" + authorizeImage + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", isAuthorize=" + isAuthorize +
                ", isOpen=" + isOpen +
                ", openTime='" + openTime + '\'' +
                ", week='" + week + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
