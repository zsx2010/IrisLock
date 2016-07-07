package com.wcsn.irislock.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.wcsn.irislock.bean.Alert;
import com.wcsn.irislock.bean.Authorize;
import com.wcsn.irislock.bean.Monitor;
import com.wcsn.irislock.bean.User;

import com.wcsn.irislock.dao.AlertDao;
import com.wcsn.irislock.dao.AuthorizeDao;
import com.wcsn.irislock.dao.MonitorDao;
import com.wcsn.irislock.dao.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig alertDaoConfig;
    private final DaoConfig authorizeDaoConfig;
    private final DaoConfig monitorDaoConfig;
    private final DaoConfig userDaoConfig;

    private final AlertDao alertDao;
    private final AuthorizeDao authorizeDao;
    private final MonitorDao monitorDao;
    private final UserDao userDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        alertDaoConfig = daoConfigMap.get(AlertDao.class).clone();
        alertDaoConfig.initIdentityScope(type);

        authorizeDaoConfig = daoConfigMap.get(AuthorizeDao.class).clone();
        authorizeDaoConfig.initIdentityScope(type);

        monitorDaoConfig = daoConfigMap.get(MonitorDao.class).clone();
        monitorDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        alertDao = new AlertDao(alertDaoConfig, this);
        authorizeDao = new AuthorizeDao(authorizeDaoConfig, this);
        monitorDao = new MonitorDao(monitorDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);

        registerDao(Alert.class, alertDao);
        registerDao(Authorize.class, authorizeDao);
        registerDao(Monitor.class, monitorDao);
        registerDao(User.class, userDao);
    }
    
    public void clear() {
        alertDaoConfig.getIdentityScope().clear();
        authorizeDaoConfig.getIdentityScope().clear();
        monitorDaoConfig.getIdentityScope().clear();
        userDaoConfig.getIdentityScope().clear();
    }

    public AlertDao getAlertDao() {
        return alertDao;
    }

    public AuthorizeDao getAuthorizeDao() {
        return authorizeDao;
    }

    public MonitorDao getMonitorDao() {
        return monitorDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

}
