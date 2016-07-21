package com.wcsn.irislock.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.AuthorizeInfo;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.sp.SPModel;
import com.ImaginationUnlimited.library.utils.log.Logger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.wcsn.irislock.alert.AlertActivity;
import com.wcsn.irislock.app.bean.PushInfo;
import com.wcsn.irislock.bean.Alert;
import com.wcsn.irislock.bean.Authorize;
import com.wcsn.irislock.bean.Monitor;
import com.wcsn.irislock.home.MainActivity;
import com.wcsn.irislock.utils.DaoUtils;
import com.wcsn.irislock.utils.DateUtils;

import java.io.IOException;
import java.io.StringReader;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by suiyue on 2016/6/21 0021.
 */
public class JPushReceiver extends BroadcastReceiver {

    private static final String TAG = "JPushReceiver";

//    public static final String

    public static final int TYPE_ALERT = 1;
    public static final int TYPE_AUTHORIZE = 2;
    public static final int TYPE_MONITOR = 3;
    public static final int TYPE_AUTHORIZE_ACTION = 4;


    private DaoUtils mDaoUtils;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        mDaoUtils = DaoUtils.getInstance(context);

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
        }else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            //数据到达后存入数据库
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            JsonReader reader = new JsonReader(new StringReader(extras));
            String pushString;
            Gson gson = new Gson();
            PushInfo pushInfo;
            try {
                reader.beginObject();
                while (reader.hasNext()) {
                    if (reader.nextName().equals("Info")) {
                        pushString = reader.nextString();
                        pushInfo = gson.fromJson(pushString, new TypeToken<PushInfo>(){}.getType());
                        switch (pushInfo.getCode()) {
                            case TYPE_ALERT:
                                pushInfo = gson.fromJson(pushString, new TypeToken<PushInfo<Alert>>(){}.getType());
                                Alert alert = (Alert) pushInfo.getData();
                                mDaoUtils.saveAlert(alert);
                                break;
                            case TYPE_AUTHORIZE:
                                pushInfo = gson.fromJson(pushString, new TypeToken<PushInfo<AuthorizeInfo>>(){}.getType());
                                Logger.e(pushInfo.toString());
                                //发起授权信息
                                AuthorizeInfo authorizeInfo = (AuthorizeInfo) pushInfo.getData();
                                Authorize authorize =new Authorize();
                                authorize.setAuthorizeImage(authorizeInfo.getImageUrl());
                                authorize.setDate(authorizeInfo.getTime().split(" ")[0]);
                                authorize.setTime(authorizeInfo.getTime().split(" ")[1]);
                                authorize.setWeek(DateUtils.getWeekOfString(authorizeInfo.getTime()));
                                authorize.setName("");
                                authorize.setIsOpen(false);
                                authorize.setOpenTime(authorizeInfo.getTime());
                                SPModel.putIsAuthorize(true);
                                SPModel.putAuthorize(authorizeInfo);
                                mDaoUtils.saveAuthorize(authorize);
                                break;
                            case TYPE_MONITOR:
                                pushInfo = gson.fromJson(pushString, new TypeToken<PushInfo<Monitor>>(){}.getType());
                                Monitor monitor = gson.fromJson(pushInfo.getData().toString(), Monitor.class);
                                mDaoUtils.saveMonitor(monitor);
                                break;
                        }
                    }
                }
                reader.endObject();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            JsonReader reader = new JsonReader(new StringReader(extras));
            String pushString;
            Gson gson = new Gson();
            PushInfo pushInfo;
            Intent i;
            try {
                reader.beginObject();
                while (reader.hasNext()) {
                    if (reader.nextName().equals("Info")) {
                        pushString = reader.nextString();
                        pushInfo = gson.fromJson(pushString, new TypeToken<PushInfo>(){}.getType());
                        switch (pushInfo.getCode()) {
                            case TYPE_ALERT:
                                i = new Intent(context, AlertActivity.class);  //自定义打开的界面
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(i);
                                break;
                            case TYPE_AUTHORIZE:
                                i = new Intent(context, MainActivity.class);  //自定义打开的界面
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(i);
                                break;
                            case TYPE_MONITOR:
                                pushInfo = gson.fromJson(pushString, new TypeToken<PushInfo<Monitor>>(){}.getType());
                                break;
                        }
                    }
                }
                reader.endObject();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Logger.e("Unhandled intent - " + intent.getAction());
        }
    }
}
