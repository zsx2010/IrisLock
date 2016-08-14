package com.wcsn.irislock.login.bean;

import com.wcsn.irislock.admin.bean.UserInfo;

/**
 * Created by suiyue on 2016/6/17 0017.
 */
public class AdminInfo {

    private String name;

    private com.wcsn.irislock.admin.bean.UserInfo mUserInfo;

    public com.wcsn.irislock.admin.bean.UserInfo getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        mUserInfo = userInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "mUserInfo=" + mUserInfo +
                ", name='" + name + '\'' +
                '}';
    }
}
