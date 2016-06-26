package com.wcsn.irislock.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;

import com.wcsn.irislock.image.FrescoManager;

import java.util.Random;

/**
 * Created by suiyue on 2016/6/7 0007.
 */
public class App {
    /**
     * 渠道号 *
     */
    private String mChannel;

    /**
     * 主线程的Handler对象 *
     */
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private App() {
    }

    /**
     * 得到App的单例对象
     *
     * @return App的单例对象
     */
    public static App getInstance() {
        return Singleton.INSTANCE;
    }


    /**
     * 得到全局上下文对象
     *
     * @return Application上下文
     */
    public Context getContext() {
        return IrisLockApplication.getContext();
    }

    /**
     * 得到全局Handler对象
     *
     * @return 全局Handler
     */
    public Handler getHandler() {
        return mHandler;
    }

    /**
     * 得到全局资源类
     *
     * @return Resource
     */

    public Resources getResources() {
        return getContext().getResources();
    }

    private Random mRandom = new Random();

    /**
     * 全局随机数生成器
     * @return 随机数生成器
     */
    public Random getRandom(){
        return mRandom;
    }
    /**
     * 得到字符串资源
     *
     * @param stringId String id
     * @return 字符串资源
     */
    public String getString(int stringId) {
        return getResources().getString(stringId);
    }

    /**
     * 得到颜色资源
     *
     * @param colorId 颜色id
     * @return 颜色
     */
    public int getColor(int colorId) {
        return getResources().getColor(colorId);
    }

    public int getDimensionPixelOffset(int dimen){
        return getResources().getDimensionPixelOffset(dimen);
    }

    /**
     * getResourcesDrawable:得到Resource下面的图片资源. <br/>
     *
     * @param id 图片第
     * @return Drawable
     */
    @SuppressWarnings("deprecation")
    public Drawable getDrawable(int id) {
        return getResources().getDrawable(id);
    }

    @SuppressLint("NewApi")
    private Drawable getDrawableKitkat(int id) {
        return getResources().getDrawable(id, null);
    }


    public LayoutInflater getLayoutInflater(){
        return (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * getChannel:得到应用的渠道号. <br/>
     *
     * @return 渠道号
     *
     */
    public String getChannel() {
        if (mChannel == null) {
//            mChannel = PackageUtils.getApplicationMetadata(getContext(), KEY_CHANNEL);
//            mChannel = PackageUtils.getMetaInfChannel(App.getInstance().getContext());
        }
        return mChannel;
    }

    /**
     * initApplication:初始化应用程序. <br/>
     * 包含第三方组件，如推送、支付等
     *
     */
    public void initApplication() {
        // Fresco初始化
        FrescoManager.init();
    }

    /**
     * 单例生成器
     */
    private static final class Singleton {

        private static final App INSTANCE = new App();
    }
}
