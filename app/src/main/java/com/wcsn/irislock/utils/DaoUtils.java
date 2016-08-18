package com.wcsn.irislock.utils;

import android.content.Context;

import com.wcsn.irislock.app.IrisLockApplication;
import com.wcsn.irislock.bean.Alert;
import com.wcsn.irislock.bean.Authorize;
import com.wcsn.irislock.bean.Monitor;
import com.wcsn.irislock.bean.User;
import com.wcsn.irislock.dao.AlertDao;
import com.wcsn.irislock.dao.AuthorizeDao;
import com.wcsn.irislock.dao.DaoSession;
import com.wcsn.irislock.dao.MonitorDao;
import com.wcsn.irislock.dao.UserDao;

import java.util.List;

/**
 * Created by suiyue on 2016/6/29 0029.
 */
public class DaoUtils {
    private static DaoUtils sInstance;
    private static Context sContext;
    private DaoSession mDaoSession;
    private AlertDao mAlertDao;
    private UserDao mUserDao;
    private AuthorizeDao mAuthorizeDao;
    private MonitorDao mMonitorDao;

    private DaoUtils() {}

    public static DaoUtils getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DaoUtils();
            if (sContext == null) {
                sContext = context.getApplicationContext();
            }
            sInstance.mDaoSession = IrisLockApplication.getDaoSession(context);
            sInstance.mAlertDao = IrisLockApplication.getDaoSession(context).getAlertDao();
            sInstance.mUserDao = IrisLockApplication.getDaoSession(context).getUserDao();
            sInstance.mAuthorizeDao = IrisLockApplication.getDaoSession(context).getAuthorizeDao();
            sInstance.mMonitorDao = IrisLockApplication.getDaoSession(context).getMonitorDao();
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
        return mAlertDao.queryBuilder().orderDesc(AlertDao.Properties.Id).list();
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


    public long saveUser(User user) {
        return mUserDao.insert(user);
    }

    public void saveUserLists(List<User> users) {
        if (users != null) {
            for (User user : users) {
                mUserDao.insert(user);
            }
        }
    }

    public List<User> loadUser(String where, String... params) {
        return mUserDao.queryRaw(where, params);
    }

    public List<User> loadUserAll() {
        return mUserDao.loadAll();
    }

    public User loadUser(long id) {
        return mUserDao.loadByRowId(id);
    }

    public void deleteUser() {
        mUserDao.deleteAll();
    }

    public void deleteUser(User user) {
        mUserDao.delete(user);
    }


    public long saveAuthorize(Authorize authorize) {
        return mAuthorizeDao.insert(authorize);
    }

    public void saveAuthorizeLists(List<Authorize> authorizes) {
        if (authorizes != null) {
            for (Authorize authorize : authorizes) {
                mAuthorizeDao.insert(authorize);
            }
        }
    }

    public List<Authorize> loadAuthorize(String where, String... params) {
        return mAuthorizeDao.queryRaw(where, params);
    }

    public Authorize loadAuthorizeByImagePath(String path) {
        List<Authorize> authorizes = mAuthorizeDao.queryBuilder()
                .where(AuthorizeDao.Properties.AuthorizeImage.eq(path))
                .list();
        return authorizes == null?null:authorizes.get(0);
    }

    public long updateAuthorize(Authorize authorize) {
        return mAuthorizeDao.insertOrReplace(authorize);
    }

    public List<Authorize> loadAuthorizeAll() {
        return mAuthorizeDao.loadAll();
    }

    public Authorize loadAuthorize(long id) {
        return mAuthorizeDao.loadByRowId(id);
    }

    public void deleteAuthorize() {
        mAuthorizeDao.deleteAll();
    }

    public void deleteAuthorize(Authorize authorize) {
        mAuthorizeDao.delete(authorize);
    }

    public long saveMonitor(Monitor monitor) {
        return mMonitorDao.insert(monitor);
    }

    public void saveMonitorLists(List<Monitor> monitors) {
        if (monitors != null) {
            for (Monitor monitor : monitors) {
                mMonitorDao.insert(monitor);
            }
        }
    }

    public List<Monitor> loadMonitor(String where, String... params) {
        return mMonitorDao.queryRaw(where, params);
    }

    public List<Monitor> loadMonitorAll() {
        return mMonitorDao.loadAll();
    }

    public Monitor loadMonitor(long id) {
        return mMonitorDao.loadByRowId(id);
    }

    public void deleteMonitor() {
        mMonitorDao.deleteAll();
    }

    public void deleteMonitor(Monitor Monitor) {
        mMonitorDao.delete(Monitor);
    }

}
