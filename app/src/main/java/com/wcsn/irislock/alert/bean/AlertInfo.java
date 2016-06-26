package com.wcsn.irislock.alert.bean;

import java.io.Serializable;

/**
 * Created by suiyue on 2016/6/22 0022.
 */
public class AlertInfo implements Serializable{

    public static int ALERT_BATTERY  =  0;
    public static int ALERT_OPEN_DOOR = 1;
    public static int ALERT_OPEN_ERROR = 2;

    private String time;
    private String week;
    private String alertInfo;
    private int alertType;
    private String alertImage;

    public String getAlertInfo() {
        return alertInfo;
    }

    public void setAlertInfo(String alertInfo) {
        this.alertInfo = alertInfo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }


    public int getAlertType() {
        return alertType;
    }

    public void setAlertType(int alertType) {
        this.alertType = alertType;
    }

    public String getAlertImage() {
        return alertImage;
    }

    public void setAlertImage(String alertImage) {
        this.alertImage = alertImage;
    }

    @Override
    public String toString() {
        return "AlertInfo{" +
                "alertImage='" + alertImage + '\'' +
                ", time='" + time + '\'' +
                ", week='" + week + '\'' +
                ", alertInfo='" + alertInfo + '\'' +
                ", alertType='" + alertType + '\'' +
                '}';
    }
}
