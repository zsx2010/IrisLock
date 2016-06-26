package com.wcsn.irislock.login.bean;

/**
 * Created by suiyue on 2016/6/17 0017.
 */
public class AdminInfo {
    private String sex;
    private String name;
    private String phone;
    private String address;
    private String street;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "AdminInfo{" +
                "address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
