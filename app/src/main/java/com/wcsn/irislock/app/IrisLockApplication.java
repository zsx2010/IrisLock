package com.wcsn.irislock.app;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;

import com.ImaginationUnlimited.library.app.CoreLibrary;
import com.ImaginationUnlimited.library.utils.log.Logger;
import com.wcsn.irislock.dao.DaoMaster;
import com.wcsn.irislock.dao.DaoSession;
import com.wcsn.irislock.receiver.NetWorkConnectChangedReceiver;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by suiyue on 2016/6/7 0007.
 */
public class IrisLockApplication extends Application{

    private static Context sContext = null;


    private static IrisLockApplication sApplication;
    private static DaoMaster sDaoMaster;
    private static DaoSession sDaoSession;


    public static Context getContext() {
        return sContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        synchronized (IrisLockApplication.this) {
            if (null == sContext) {
                sContext = getApplicationContext();
                sApplication = this;
            }
        }
        // 配置CoreLibrary
        CoreLibrary.getInstance().init(IrisLockApplication.this);

        // 初始化APP
        App.getInstance().initApplication();

//        EventBus.getDefault().register(this);
        JPushInterface.init(this);
        JPushInterface.setDebugMode(false);
        JPushInterface.setAlias(getContext(), "2d7c9d7c1c281da13804000000009200108f", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                String logs;
                switch (i) {
                    case 0:
                        logs = "Set tag and alias success";
                        Logger.e(logs);
                        break;
                    case 6002:
                        logs = "Failed to set alias and tags due to timeout. Try again after 60s.";

                        Logger.e(logs);
                    default:
                        logs = "Failed with errorCode = " + i;
                        Logger.e(logs);
                }
            }
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(new NetWorkConnectChangedReceiver(), filter);
    }

    public static DaoMaster getDaoMaster(Context context){
        if(sDaoMaster == null){
            //create database
            //获取Master对象
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "irislock", null);
            sDaoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return sDaoMaster;
    }

    public static DaoSession getDaoSession(Context context){
        if(sDaoSession == null){
            if(sDaoMaster == null){
                getDaoMaster(context);
            }
            sDaoSession = sDaoMaster.newSession();
        }

        return sDaoSession;
    }
}
