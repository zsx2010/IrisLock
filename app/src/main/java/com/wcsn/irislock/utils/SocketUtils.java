package com.wcsn.irislock.utils;

import android.support.annotation.NonNull;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.sp.SPModel;
import com.ImaginationUnlimited.library.utils.log.Logger;
import com.google.gson.Gson;
import com.vilyever.socketclient.SocketClient;
import com.vilyever.socketclient.helper.SocketClientAddress;
import com.vilyever.socketclient.helper.SocketClientDelegate;
import com.vilyever.socketclient.helper.SocketPacket;
import com.vilyever.socketclient.helper.SocketResponsePacket;
import com.wcsn.irislock.app.App;
import com.wcsn.irislock.utils.bean.WiFiInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by suiyue on 2016/8/8 0008.
 */
public class SocketUtils {


    public static void getTime() {
        new GetTimeThread().run();
    }

    public static void getDeviceId() {
        new GetIdThread().run();

    }

    public static void setWiFi(String ssid, String pwd) {

        new SetWifiThread(ssid, pwd).run();

    }

    static class GetTimeThread extends Thread {

        @Override
        public void run() {

            SocketClient socketClient = new SocketClient(new SocketClientAddress(SPModel.getSocketIp(), SPModel.getSocketPort(), 5*1000));

            socketClient.setCharsetName("UTF-8");
            socketClient.getSocketPacketHelper().setSendHeaderData(null); // 设置发送消息时自动在消息头部添加的信息，远程端收到此信息后表示一条消息开始，用于解决粘包分包问题，若为null则不添加头部信息
            socketClient.getSocketPacketHelper().setSendTrailerString(null);

            socketClient.registerSocketClientDelegate(new SocketClientDelegate() {
                @Override
                public void onConnected(SocketClient socketClient) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = format.format(new Date());
                    SocketPacket packet = socketClient.sendString("FD" + time);
                    packet = socketClient.sendString("EXIT");
                }

                @Override
                public void onDisconnected(SocketClient socketClient) {
                    Logger.e("Socket 连接断开");
                }

                @Override
                public void onResponse(SocketClient socketClient, @NonNull SocketResponsePacket socketResponsePacket) {
                    String message = socketResponsePacket.getMessage();
                    if (message == null) {
                    } else if (message.equals("success")){
                        socketClient.disconnect();
                    } else {
                    }
                }
            });
            socketClient.connect();
        }
    }

    static class GetIdThread extends Thread {

        @Override
        public void run() {

            Logger.e("SocketThread");

            SocketClient socketClient = new SocketClient(new SocketClientAddress(SPModel.getSocketIp(), SPModel.getSocketPort(), 5*1000));

            socketClient.setCharsetName("UTF-8");
            socketClient.getSocketPacketHelper().setSendHeaderData(null); // 设置发送消息时自动在消息头部添加的信息，远程端收到此信息后表示一条消息开始，用于解决粘包分包问题，若为null则不添加头部信息
            socketClient.getSocketPacketHelper().setSendTrailerString(null);

            socketClient.registerSocketClientDelegate(new SocketClientDelegate() {
                @Override
                public void onConnected(SocketClient socketClient) {
                    SocketPacket packet = socketClient.sendString("FF");
                    packet = socketClient.sendString("EXIT");
                }

                @Override
                public void onDisconnected(SocketClient socketClient) {

                }

                @Override
                public void onResponse(SocketClient socketClient, @NonNull SocketResponsePacket socketResponsePacket) {
                    String message = socketResponsePacket.getMessage();
                    if (message == null) {
                        Logger.e("message = null");
                    } else if (message.equals("success")){
                        Logger.e("message = success");

                        socketClient.disconnect();
                    } else {
                        SPModel.putDevieceId(message);
                        JPushInterface.setAlias(App.getInstance().getContext(), message, new TagAliasCallback() {
                            @Override
                            public void gotResult(int i, String s, Set<String> set) {
                                if (i == 0) {
                                    Logger.e("别名设置成功");
                                }
                            }
                        });
                    }
                }
            });

            socketClient.connect();
        }
    }


    private static class SetWifiThread extends Thread{

        private String mSsid;
        private String mPwd;

        public SetWifiThread(String ssid, String pwd) {
            mSsid = ssid;
            mPwd = pwd;
        }

        @Override
        public void run() {
            Logger.e("SocketThread");

            SocketClient socketClient = new SocketClient(new SocketClientAddress(SPModel.getSocketIp(), SPModel.getSocketPort(), 5*1000));

            socketClient.setCharsetName("UTF-8");
            socketClient.getSocketPacketHelper().setSendHeaderData(null); // 设置发送消息时自动在消息头部添加的信息，远程端收到此信息后表示一条消息开始，用于解决粘包分包问题，若为null则不添加头部信息
            socketClient.getSocketPacketHelper().setSendTrailerString(null);

            socketClient.registerSocketClientDelegate(new SocketClientDelegate() {
                @Override
                public void onConnected(SocketClient socketClient) {
                    WiFiInfo wiFiInfo = new WiFiInfo(mSsid, mPwd, 0);
                    Gson gson = new Gson();
                    String wifi = gson.toJson(wiFiInfo);
                    SocketPacket packet = socketClient.sendString("F3" + wifi);
                    packet = socketClient.sendString("EXIT");
                }

                @Override
                public void onDisconnected(SocketClient socketClient) {

                }

                @Override
                public void onResponse(SocketClient socketClient, @NonNull SocketResponsePacket socketResponsePacket) {
                    String message = socketResponsePacket.getMessage();


                    if (message == null) {
                        Logger.e("message = null");
                    } else if (message.equals("success")){
                        Logger.e("message = success");

                        socketClient.disconnect();
                    } else {
                        SPModel.putDevieceId(message);
                        JPushInterface.setAlias(App.getInstance().getContext(), message, new TagAliasCallback() {
                            @Override
                            public void gotResult(int i, String s, Set<String> set) {
                                if (i == 0) {
                                    Logger.e("别名设置成功");
                                }
                            }
                        });
                    }
                }
            });

            socketClient.connect();
        }

        public String getPwd() {
            return mPwd;
        }

        public void setPwd(String pwd) {
            mPwd = pwd;
        }

        public String getSsid() {
            return mSsid;
        }

        public void setSsid(String ssid) {
            mSsid = ssid;
        }
    }


}
