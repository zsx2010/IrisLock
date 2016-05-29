package com.wcsn.irislock.utils;

/**
 * Created by suiyue on 2016/5/28 0028.
 */
public enum NetType
{
    /**
     * 无网络
     */
    NET_NONE(1),
    /**
     * 手机
     */
    NET_MOBILE(2),
    /**
     * wifi
     */
    NET_WIFI(4),
    /**
     * 其他
     */
    NET_OTHER(8);

    NetType(int value) {
        this.value = value;
    }

    public int value;
}
