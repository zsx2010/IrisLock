package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.sp;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.AuthorizeInfo;
import com.ImaginationUnlimited.library.utils.app.StringUtils;
import com.google.gson.Gson;

/**
 * SP存储
 * @author wangheng on 2016-03-29 14:18
 */
public class SPModel {

    private static final String KEY_DEVICE_ID = "keyDeviceId";

    private static final String KEY_SOCKET_IP = "KeySocketIp";
    private static final String KEY_SOCKET_PORT = "KeySocketPort";

    private static final String KEY_IS_AUTHORIZE = "KeyIsAuthorize";
    private static final String KEY_AUTHORIZE_INFO = "KeyAuthorizeInfo";

    private static final String KEY_POWER = "KeyPower";

    private static final String KEY_ADMIN = "KeyAdmin";

    private static final String KEY_PWD = "KeyPwd";

    /**
     * 0:无
     * 1:数字
     * 2:图形
     */
    private static final String KEY_PWD_TYPE = "KeyPwdType";


    public static final int PWD_TYPE_NONE = 0;
    public static final int PWD_TYPE_NUM = 1;
    public static final int PWD_TYPE_IMAGE = 2;

    private static String socketIp = "192.168.1.1";
    private static int socketPort = 12345;

    public static void clean(){
        putAdmin(false);
        putDevieceId(null);
        putPower(null);
        putIsAuthorize(false);
        putAuthorize(null);
    }

    public static void putDevieceId(String deviceId){
        SPUtils.getInstance().putString(KEY_DEVICE_ID, deviceId);
    }

    public static String getDeviceId(){
        return SPUtils.getInstance().getString(KEY_DEVICE_ID,"2d7c9d7c1c281da13804000000009200108f");
    }


    public static void putPwd(String pwd){
        SPUtils.getInstance().putString(KEY_PWD, pwd);
    }

    public static String getPwd(){
        return SPUtils.getInstance().getString(KEY_PWD,"");
    }

    public static void putPwdType(int pwdTyoe){
        SPUtils.getInstance().putInt(KEY_PWD_TYPE, pwdTyoe);
    }

    public static int getPwdType(){
        return SPUtils.getInstance().getInt(KEY_PWD_TYPE,0);
    }



    public static void putPower(String power){
        SPUtils.getInstance().putString(KEY_POWER, power);
    }

    public static String getPower(){
        return SPUtils.getInstance().getString(KEY_POWER,"100");
    }


    public static void putAdmin(boolean hasAdmin) {
        SPUtils.getInstance().putBoolean(KEY_ADMIN, hasAdmin);
    }

    public static boolean getAdmin() {
        return SPUtils.getInstance().getBoolean(KEY_ADMIN, false);
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

    public static String getSocketIp(){
        String json = SPUtils.getInstance().getString(KEY_SOCKET_IP, socketIp);
        return json;
    }

    public static int getSocketPort(){
        int json = SPUtils.getInstance().getInt(KEY_SOCKET_PORT, socketPort);
        return json;
    }


}
