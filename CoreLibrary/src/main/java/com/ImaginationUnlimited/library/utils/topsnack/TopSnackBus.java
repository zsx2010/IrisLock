package com.ImaginationUnlimited.library.utils.topsnack;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by somehui on 16/5/20.
 */
public class TopSnackBus extends EventBus {
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
