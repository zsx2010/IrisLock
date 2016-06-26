package com.wcsn.irislock.utils;

import android.view.View;

/**
 * Created by somehui on 15/9/6.
 */
public abstract class OnIntentClickListener implements View.OnClickListener{
    public abstract void onClickWithCD(View v);

    private static final int DURATION_MILLS = 500;
    private static final String TAG = "cdClick";
    private static final String DENY = "too quick";

    private static long lastClickTime = -700000000;

    private String strId;

    public OnIntentClickListener() {
    }

    public OnIntentClickListener(String id) {
        strId = id;
    }

    @Override
    public void onClick(View v) {
        long currentTime = currentTime();
        if(currentTime-lastClickTime>DURATION_MILLS){
            onClickWithCD(v);
            lastClickTime = currentTime;
        }

    }
    private long currentTime(){
        long nowTime = System.currentTimeMillis();
        if(lastClickTime>nowTime){//if user changing time at runtime
            lastClickTime = Long.MIN_VALUE;
        }
        return nowTime;
    }
}
