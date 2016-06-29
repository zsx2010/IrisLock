package com.wcsn.irislock.utils;

import android.content.Context;

import com.wcsn.irislock.app.IrisLockApplication;
import com.wcsn.irislock.bean.Alert;
import com.wcsn.irislock.dao.AlertDao;
import com.wcsn.irislock.dao.DaoSession;

import java.util.List;

/**
 * Created by suiyue on 2016/6/29 0029.
 */
public class DaoUtils {
    private static DaoUtils sInstance;
    private static Context sContext;
    private DaoSession mDaoSession;
    private AlertDao mAlertDao;

    private DaoUtils() {}

    public static DaoUtils getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DaoUtils();
            if (sContext == null) {
                sContext = context.getApplicationContext();
            }
            sInstance.mDaoSession = IrisLockApplication.getDaoSession(context);
            sInstance.mAlertDao = IrisLockApplication.getDaoSession(context).getAlertDao();
        }
        return sInstance;
    }

    public long saveAlert(Alert alert) {
        return mAlertDao.insert(alert);
    }

    public void saveAlertLists(List<Alert> alerts) {
        if (alerts != null) {
            for (Alert alert : alerts) {
                mAlertDao.insert(alert);
            }
        }
    }

    public List<Alert> loadAlert(String where, String... params) {
        return mAlertDao.queryRaw(where, params);
    }

    public List<Alert> loadAlertAll() {
        return mAlertDao.loadAll();
    }

    public Alert loadAlert(long id) {
        return mAlertDao.loadByRowId(id);
    }

    public void deleteAlert() {
        mAlertDao.deleteAll();
    }

    public void deleteAlert(Alert alert) {
        mAlertDao.delete(alert);
    }

}
