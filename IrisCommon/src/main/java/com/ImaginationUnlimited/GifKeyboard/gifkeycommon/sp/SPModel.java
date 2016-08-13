package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.sp;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.AuthorizeInfo;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.User;
import com.ImaginationUnlimited.library.utils.app.StringUtils;
import com.google.gson.Gson;

/**
 * SP存储
 * @author wangheng on 2016-03-29 14:18
 */
public class SPModel {

    private static final String KEY_DEVICE_ID = "keyDeviceId";
    private static final String KEY_USER = "keyUser";

    private static final String KEY_SOCKET_IP = "KeySocketIp";
    private static final String KEY_SOCKET_PORT = "KeySocketPort";

    private static final String KEY_IS_AUTHORIZE = "KeyIsAuthorize";
    private static final String KEY_AUTHORIZE_INFO = "KeyAuthorizeInfo";

    private static final String KEY_POWER = "KeyPower";

    private static String socketIp = "192.168.1.1";
    private static int socketPort = 12345;

    public static void clean(){
        putUser(null);
    }

    public static void putDevieceId(String deviceId){
        SPUtils.getInstance().putString(KEY_DEVICE_ID, deviceId);
    }

    public static String getDeviceId(){
        return SPUtils.getInstance().getString(KEY_DEVICE_ID,"");
    }

    public static void putPower(String power){
        SPUtils.getInstance().putString(KEY_POWER, power);
    }

    public static String getPower(){
        return SPUtils.getInstance().getString(KEY_POWER,"100");
    }


    public static void putIsAuthorize(boolean isAuthorize) {
        SPUtils.getInstance().putBoolean(KEY_IS_AUTHORIZE, isAuthorize);
    }

    public static boolean getIsAuthorize() {
        return SPUtils.getInstance().getBoolean(KEY_IS_AUTHORIZE, false);
    }


    public static void putAuthorize(AuthorizeInfo authorizeInfo){
        String json = null;
        if(authorizeInfo != null){
            json = new Gson().toJson(authorizeInfo);
        }
        SPUtils.getInstance().putString(KEY_AUTHORIZE_INFO,json);
    }


    public static AuthorizeInfo getAuthorize(){
        String json = SPUtils.getInstance().getString(KEY_AUTHORIZE_INFO,"");
        if(!StringUtils.isNullOrEmpty(json)){
            return new Gson().fromJson(json,AuthorizeInfo.class);
        }
        return null;
    }


    public static void putUser(User user){
        String json = null;
        if(user != null){
            json = new Gson().toJson(user);
        }
        SPUtils.getInstance().putString(KEY_USER,json);
    }


    public static User getUser(){
        String json = SPUtils.getInstance().getString(KEY_USER,"");
        if(!StringUtils.isNullOrEmpty(json)){
            return new Gson().fromJson(json,User.class);
        }
        return null;
    }

    public static String getSocketIp(){
        String json = SPUtils.getInstance().getString(KEY_SOCKET_IP, socketIp);
        return json;
    }

    public static int getSocketPort(){
        int json = SPUtils.getInstance().getInt(KEY_SOCKET_PORT, socketPort);
        return json;
    }


}
