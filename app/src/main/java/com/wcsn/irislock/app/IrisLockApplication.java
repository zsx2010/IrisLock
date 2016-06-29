package com.wcsn.irislock.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ImaginationUnlimited.library.app.CoreLibrary;
import com.ImaginationUnlimited.library.utils.log.Logger;
import com.wcsn.irislock.bean.Alert;
import com.wcsn.irislock.dao.AlertDao;
import com.wcsn.irislock.dao.DaoMaster;
import com.wcsn.irislock.dao.DaoSession;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import de.greenrobot.dao.query.Query;

/**
 * Created by suiyue on 2016/6/7 0007.
 */
public class IrisLockApplication extends Application{
    private static Context sContext = null;

    public DaoSession daoSession;
    public SQLiteDatabase db;
    public DaoMaster.DevOpenHelper helper;
    public DaoMaster daoMaster;

    public static Context getContext() {
        return sContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        synchronized (IrisLockApplication.this) {
            if (null == sContext) {
                sContext = getApplicationContext();
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
        setupDatabase();
    }

    private void setupDatabase() {
        //创建数据库
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        helper = new DaoMaster.DevOpenHelper(this, "test", null);
        //得到数据库连接对象
        db = helper.getWritableDatabase();
        //得到数据库管理者
        daoMaster =new DaoMaster(db);
        //得到daoSession，可以执行增删改查操作
        daoSession = daoMaster.newSession();

        Alert alert = new Alert();
        alert.setInfo("20");
        alert.setTime("2016-06-26");
        alert.setWeek("7");
        alert.setType(1);

        getAlertDao().insert(alert);

        Query query = getAlertDao().queryBuilder().build();
        Logger.e(query.list().toString());


    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    private AlertDao getAlertDao() {
        return daoSession.getAlertDao();
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
