package com.ImaginationUnlimited.library.utils.log;

import android.util.Log;

/**
 * 
 * @describe 统一管理log类. 
 * @author Zhong Likui
 * @date: 2015年4月11日 下午5:45:35 <br/>
 */
public class Logger {

    private static final String TAG = "tag-gifey";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }

    public static void v(String msg) {
        Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        Log.v(tag, msg);
    }
    
    public static void e(String tag,String logPrefix,Throwable throwable){
        Log.e(tag, logPrefix + ":" + throwable);
    }

}
