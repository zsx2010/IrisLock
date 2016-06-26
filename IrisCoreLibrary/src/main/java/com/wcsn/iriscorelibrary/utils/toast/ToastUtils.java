package com.wcsn.iriscorelibrary.utils.toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.IntDef;
import android.util.Log;
import android.widget.Toast;

import com.ImaginationUnlimited.library.app.CoreLibrary;
import com.ImaginationUnlimited.library.utils.app.StringUtils;

/**
 * Toast 工具类
 *
 * @author wangheng
 */
public class ToastUtils {

    /** 显示时间 - 较短，对应的是Toast的LENGTH_SHORT **/
    public static final int DURATION_SHORT = 0x00;

    /** 显示时间 - 较长，对应的是Toast的LENGTH_LONG **/
    public static final int DURATION_LONG = 0x01;

    @IntDef({DURATION_SHORT,DURATION_LONG})
    public @interface Duration{}

    private static int convertDuration(int duration){
        if(duration == DURATION_LONG ){
            return Toast.LENGTH_LONG;
        }
        return Toast.LENGTH_SHORT;
    }
    private static Context getContext(){
        return CoreLibrary.getInstance().getContext();
    }

    /**
     * 显示指定时长的Toast
     *
     * 如果文本为空字符串或者null，则不进行显示
     *
     * @param text 显示文本
     * @param duration 显示的时间，DURATION_SHORT或者DURATION_LONG之一
     */
    public static void toast(String text,@Duration int duration){

        if(StringUtils.isNullOrEmpty(text)){
            return;
        }
        boolean isDurationShort = convertDuration(duration) == Toast.LENGTH_SHORT;
        Toast.makeText(CoreLibrary.getInstance().getContext(),
                text,
                isDurationShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG)
                .show();
    }

    private static Toast mToast;

    @SuppressLint("ShowToast")
    private static Toast getToast(){
        if (mToast == null){
            mToast = Toast.makeText(getContext(),null,Toast.LENGTH_SHORT);
        }
        return mToast;
    }
    public static void toast2(final String text,final @Duration int duration){
        if(StringUtils.isNullOrEmpty(text)){
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                boolean isDurationShort = convertDuration(duration) == Toast.LENGTH_SHORT;
                Toast toast = getToast();
                toast.setText(text);
                toast.setDuration(isDurationShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
                toast.show();
                Log.d("toast2",text);
            }
        });
    }

    /**
     * 显示指定resId的Toast
     * @param resId 字符串资源Id
     * @param showTimeMillis 显示的时间，DURATION_SHORT或者DURATION_LONG之一
     */
    public static void toast(int resId,int showTimeMillis){
        toast(CoreLibrary.getInstance().getContext().getString(resId), showTimeMillis);
    }

    /**
     * 显示短Toast
     *
     * 如果文本为空字符串或者null，则不进行显示
     *
     * @param text 如果文本为空字符串或者null，则不进行显示
     */
    public static void toastShort(String text){
        toast(text, DURATION_SHORT);
    }
    /**
     * 显示短Toast
     *
     *
     * @param resId 字符串资源Id
     */
    public static void toastShort(int resId){
        toast(CoreLibrary.getInstance().getContext().getString(resId), DURATION_SHORT);
    }
    /**
     * 显示长Toast
     *
     * 如果文本为空字符串或者null，则不进行显示
     *
     * @param text 如果文本为空字符串或者null，则不进行显示
     */
    public static void toastLong(String text){
        toast(text,DURATION_LONG);
    }
    /**
     * 显示长Toast
     *
     *
     * @param resId 字符串资源Id
     */
    public static void toastLong(int resId){
        toast(CoreLibrary.getInstance().getContext().getString(resId),DURATION_LONG);
    }

    /**
     * 显示长Toast，通过指定的网络错误
     *
     * 如果文本为空字符串或者null，则不进行显示
     *
     * @param error 网络错误
     */
//    public static void toastLongByNetworkError(NetworkError error){
//        toast(NetworkErrorConvertor.convertNetworkErrorToString(error),DURATION_LONG);
//    }
    /**
     * 显示短Toast，通过指定的网络错误
     *
     * 如果文本为空字符串或者null，则不进行显示
     *
     * @param error 网络错误
     */
//    public static void toastShortByNetworkError(NetworkError error){
//        toast(NetworkErrorConvertor.convertNetworkErrorToString(error),DURATION_SHORT);
//    }
}
