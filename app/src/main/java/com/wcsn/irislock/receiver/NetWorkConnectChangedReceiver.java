package com.wcsn.irislock.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.sp.SPModel;
import com.ImaginationUnlimited.library.utils.log.Logger;
import com.ImaginationUnlimited.library.utils.network.NetworkUtils;
import com.vilyever.socketclient.SocketClient;
import com.vilyever.socketclient.helper.SocketClientAddress;
import com.vilyever.socketclient.helper.SocketClientDelegate;
import com.vilyever.socketclient.helper.SocketPacket;
import com.vilyever.socketclient.helper.SocketResponsePacket;
import com.wcsn.irislock.app.App;
import com.wcsn.irislock.utils.SocketUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by suiyue on 2016/7/15 0015.
 */
public class NetWorkConnectChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {// 这个监听wifi的打开与关闭，与wifi的连接无关
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            switch (wifiState) {
                case WifiManager.WIFI_STATE_DISABLED:
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    break;
                //
            }
        }
        // 这个监听wifi的连接状态即是否连上了一个有效无线路由，当上边广播的状态是WifiManager.WIFI_STATE_DISABLING，和WIFI_STATE_DISABLED的时候，根本不会接到这个广播。
        // 在上边广播接到广播是WifiManager.WIFI_STATE_ENABLED状态的同时也会接到这个广播，当然刚打开wifi肯定还没有连接到有效的无线
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            Parcelable parcelableExtra = intent
                    .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (null != parcelableExtra) {
                NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                NetworkInfo.State state = networkInfo.getState();
                boolean isConnected = state == NetworkInfo.State.CONNECTED;// 当然，这边可以更精确的确定状态
                if (isConnected) {
                    if (NetworkUtils.getWifiSSID(context).startsWith(App.mWifiName)) {
                        SocketUtils.getTime();
                    }
                } else {

                }
            }
        }
    }

    class SocketThread extends Thread {

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
                    Logger.e("Socket 已连接");
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
                        Logger.e("message = null");
                    } else if (message.equals("success")){
                        Logger.e("message = success");
                        socketClient.disconnect();
                    } else {
                        Logger.e("message = " + message);
                    }
                }
            });

            socketClient.connect();
        }
    }


}
