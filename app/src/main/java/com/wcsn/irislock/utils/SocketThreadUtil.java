package com.wcsn.irislock.utils;

import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by suiyue on 2016/3/9 0009.
 */
public class SocketThreadUtil extends Thread {

    private Handler mHandler;

    public SocketThreadUtil(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void run() {
        //定义消息
        Socket socket;
//        Message msg = new Message();
//        msg.what = 0x11;
//        Bundle bundle = new Bundle();
//        bundle.clear();
        try {
            //连接服务器 并设置连接超时为5秒
            socket = new Socket();
            socket.connect(new InetSocketAddress("192.168.0.107", 12345), 5000);
            OutputStream ou = socket.getOutputStream();
            //向服务器发送信息
            ou.write("hello".getBytes("utf-8"));
            ou.flush();

            BufferedReader bff = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            Log.e("msg", bff.readLine());
            //读取发来服务器信息
            String line = null;
            String buffer="";
            Log.e("msg", buffer);
            while ((line = bff.readLine()) != null) {
                Log.e("msg", line);
                buffer = line + buffer;
            }

            Log.e("msg", buffer);
//            bundle.putString("msg", buffer);
//            msg.setData(bundle);
//            mHandler.sendMessage(msg);

            bff.close();
            ou.close();
            socket.close();
        } catch (SocketTimeoutException aa) {
            //连接超时 在UI界面显示消息
//            bundle.putString("msg", "服务器连接失败！请检查网络是否打开");
//            msg.setData(bundle);
            //发送消息 修改UI线程中的组件
//            mHandler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
