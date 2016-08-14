package com.wcsn.irislock.admin.bean;

/**
 * Created by suiyue on 2016/7/9 0009.
 */
public class TempUserInfo {

    private String name;
    private String startDate;
    private String stopDate;
    private String startTime;
    private String stopTime;
    private String week;

    private UserInfo mUserInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        mUserInfo = userInfo;
    }

    @Override
    public String toString() {
        return "TempUserInfo{" +
                "mUserInfo=" + mUserInfo.toString() +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", stopDate='" + stopDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", stopTime='" + stopTime + '\'' +
                ", week='" + week + '\'' +
                '}';
    }
}
