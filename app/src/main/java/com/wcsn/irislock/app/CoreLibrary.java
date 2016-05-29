package com.wcsn.irislock.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by suiyue on 2016/5/28 0028.
 */
public class CoreLibrary {
    private Application mApplication;
    private boolean mDebug;

    private CoreLibrary() {

    }

    /**
     * 仅仅为了单例而存在.
     *
     * @author wangheng
     */
    private static final class Singleton {
        private static final CoreLibrary INSTANCE = new CoreLibrary();
    }

    /**
     * getInstance:得到Configuration的单例对象. <br/>
     *
     * @return LibraryConfig单例
     */
    public static CoreLibrary getInstance() {
        return Singleton.INSTANCE;
    }

    /**
     * 是否处于Debug模式
     *
     * @return 是否处于Debug模式
     */

    public boolean isDebug() {
        return mDebug;
    }

    public static boolean debug(){
        return getInstance().isDebug();
    }
    /**
     * 初始化CoreLibrary
     * @param application application
     * @param isDebug 是否处于Debug模式
     */
    public void init(Application application,boolean isDebug){
        mApplication = application;
        mDebug = isDebug;
    }

    /**
     * 得到全局Context对象
     * @return 全局Context对象
     */
    public Context getContext(){
        return mApplication;
    }

    /**
     * 得到当前应用程序对象
     * @return 当前应用程序对象
     */
    public Application getApplication(){
        return mApplication;
    }

}
