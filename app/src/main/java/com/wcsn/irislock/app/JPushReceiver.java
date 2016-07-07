package com.wcsn.irislock.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ImaginationUnlimited.library.utils.log.Logger;
import com.google.gson.Gson;
import com.wcsn.irislock.alert.AlertActivity;
import com.wcsn.irislock.app.bean.PushInfo;
import com.wcsn.irislock.bean.Alert;
import com.wcsn.irislock.bean.Authorize;
import com.wcsn.irislock.bean.Monitor;
import com.wcsn.irislock.utils.DaoUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by suiyue on 2016/6/21 0021.
 */
public class JPushReceiver extends BroadcastReceiver {

    private static final String TAG = "JPushReceiver";

    public static final int TYPE_ALERT = 1;
    public static final int TYPE_AUTHORIZE = 2;
    public static final int TYPE_MONITOR = 3;

    private DaoUtils mDaoUtils;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Logger.e("onReceive - " + intent.getAction());

        mDaoUtils = DaoUtils.getInstance(context);

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
        }else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            String pushString = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            Gson gson = new Gson();
            PushInfo pushInfo = gson.fromJson(pushString, PushInfo.class);
            switch (pushInfo.getCode()) {
                case TYPE_ALERT:
                    Alert alert = gson.fromJson(pushInfo.getDate().toString(), Alert.class);
                    mDaoUtils.saveAlert(alert);
                    break;
                case TYPE_AUTHORIZE:
                    Authorize authorize = gson.fromJson(pushInfo.getDate().toString(), Authorize.class);
                    mDaoUtils.saveAuthorize(authorize);
                    break;
                case TYPE_MONITOR:
                    Monitor monitor = gson.fromJson(pushInfo.getDate().toString(), Monitor.class);
                    mDaoUtils.saveMonitor(monitor);
                    break;
            }

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            // 在这里可以做些统计，或者做些其他工作
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Intent i = new Intent(context, AlertActivity.class);  //自定义打开的界面
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Logger.e("Unhandled intent - " + intent.getAction());
        }
    }
}
