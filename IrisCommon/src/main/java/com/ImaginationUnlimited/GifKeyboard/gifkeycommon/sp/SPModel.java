package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.sp;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.User;
import com.ImaginationUnlimited.library.utils.app.StringUtils;
import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * SP存储
 * @author wangheng on 2016-03-29 14:18
 */
public class SPModel {

    private static final String KEY_DEVICE_ID = "keyDeviceId";
    private static final String KEY_USER = "keyUser";
    private static final String KEY_SHUFFLE_LIST = "keyShuffleList";

    private static final String KEY_SOCKET_IP = "KeySocketIp";
    private static final String KEY_SOCKET_PORT = "KeySocketPort";

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

    public static String getTokenPerhapsNull(){
        return SPUtils.getInstance().getString(KEY_DEVICE_ID,null);
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

    public static void putShuffleList(ArrayList<String> list){
        if(list != null){
            String json = new Gson().toJson(list);
            SPUtils.getInstance().putString(KEY_SHUFFLE_LIST,json);
        }
    }

    public static ArrayList<String> getShuffleList(){
        String json = SPUtils.getInstance().getString(KEY_SHUFFLE_LIST,null);
        if(StringUtils.isNullOrEmpty(json)){
            return null;
        }
        ArrayList<String> list = new Gson().fromJson(json, new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{String.class};
            }

            @Override
            public Type getOwnerType() {
                return null;
            }

            @Override
            public Type getRawType() {
                return ArrayList.class;
            }
        });
        return list;
    }
}
