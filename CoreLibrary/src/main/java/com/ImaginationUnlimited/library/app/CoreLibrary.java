package com.ImaginationUnlimited.library.app;

import android.app.Application;
import android.content.Context;

/**
 * Library配置
 *
 * @author wangheng on 2016-03-08 14:57
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
     * 初始化CoreLibrary
     * @param application application
     */
    public void init(Application application){
        mApplication = application;
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
