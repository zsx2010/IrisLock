package com.wcsn.iriscorelibrary.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by suiyue on 2016/6/7 0007.
 */
public class CoreLibrary {
    private Application mApplication;

    /**
     *单例模式
     */
    private CoreLibrary() {

    }
    private static final class Singleton {
        private static final CoreLibrary INSTANCE = new CoreLibrary();
    }

    private static CoreLibrary getInstance() {
        return Singleton.INSTANCE;
    }
    /**
     * 得到全局的Context
     */
    public Context getContext(){
        return mApplication;
    }

    public Application getApplication(){
        return mApplication;
    }
}
