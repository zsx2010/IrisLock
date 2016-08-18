package com.wcsn.irislock.admin.bean;

/**
 * Created by suiyue on 2016/8/14 0014.
 */
public class UserInfo {
    private String sex;
    private String phone;
    private String address;
    private String street;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
