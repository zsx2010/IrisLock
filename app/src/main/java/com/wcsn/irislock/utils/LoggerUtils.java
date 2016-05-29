package com.wcsn.irislock.utils;

import android.util.Log;

import com.wcsn.irislock.app.CoreLibrary;

/**
 * Created by suiyue on 2016/5/28 0028.
 */
public class LoggerUtils {
    // 是否需要打印log，可以在application的onCreate函数里面初始化
    public static final boolean isDebug = CoreLibrary.getInstance().isDebug();

    private static final String TAG = "tag-gifey";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if(isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if(isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if(isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void v(String msg) {
        if(isDebug) {
            Log.v(TAG, msg);
        }
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if(isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if(isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if(isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if(isDebug) {
            Log.v(tag, msg);
        }
    }

    public static void e(String tag,String logPrefix,Throwable throwable){
        if(isDebug){
            Log.e(tag, logPrefix + ":" + throwable);
        }
    }

}
