package com.wcsn.irislock.utils.bean;

/**
 * Created by suiyue on 2016/8/21 0021.
 */
public class WiFiInfo {
    private String ssid;
    private String password;
    private int volume;

    public WiFiInfo(String ssid, String password) {
        this.ssid = ssid;
        this.password = password;
    }

    public WiFiInfo(String ssid, String password, int volume) {
        this.ssid = ssid;
        this.password = password;
        this.volume = volume;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "WiFiInfo{" +
                "ssid='" + ssid + '\'' +
                ", password='" + password + '\'' +
                ", volume=" + volume +
                '}';
    }
}
