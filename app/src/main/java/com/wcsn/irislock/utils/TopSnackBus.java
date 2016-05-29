package com.wcsn.irislock.utils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by suiyue on 2016/5/29 0029.
 */
public class TopSnackBus extends EventBus{
    private static volatile TopSnackBus defaultInstance;

    public static TopSnackBus getDefault() {
        if (defaultInstance == null) {
            synchronized (EventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new TopSnackBus();
                }
            }
        }
        return defaultInstance;
    }

    public TopSnackBus() {
        super();
    }
}
