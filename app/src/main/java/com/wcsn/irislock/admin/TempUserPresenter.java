package com.wcsn.irislock.admin;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.sp.SPModel;
import com.ImaginationUnlimited.library.app.mvp.BasePresenter;
import com.ImaginationUnlimited.library.utils.app.StringUtils;
import com.ImaginationUnlimited.library.utils.log.Logger;
import com.ImaginationUnlimited.library.utils.toast.ToastUtils;
import com.google.gson.Gson;
import com.vilyever.socketclient.SocketClient;
import com.vilyever.socketclient.helper.SocketClientAddress;
import com.vilyever.socketclient.helper.SocketClientDelegate;
import com.vilyever.socketclient.helper.SocketResponsePacket;
import com.wcsn.irislock.admin.bean.TempUserInfo;
import com.wcsn.irislock.bean.User;
import com.wcsn.irislock.utils.DaoUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by suiyue on 2016/7/8 0008.
 */
public class TempUserPresenter extends BasePresenter<ITempUserUI> {

    public void bindWatcher () {
        getUI().getNameEdit().addTextChangedListener(new TextWatcher() {
            /**
             *
             * @param s 输入框的原内容
             * @param start 索引字符
             * @param count 有count个字符被替换
             * @param after 替换count个字符的新的字符个数为after
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                Logger.e("name = " + s.toString());
                if (StringUtils.isNullOrEmpty(s.toString())) {
                    getUI().changeNameCheck(false);
                    if (getUI().isWholeInfo()) {
                        getUI().getRegisterButton().setEnabled(true);
                    } else {
                        getUI().getRegisterButton().setEnabled(false);
                    }
                } else {
                    getUI().changeNameCheck(true);
                    if (getUI().isWholeInfo()) {
                        getUI().getRegisterButton().setEnabled(true);
                    } else {
                        getUI().getRegisterButton().setEnabled(false);
                    }
                }
            }
        });

        getUI().getPhoneEdit().addTextChangedListener(new TextWatcher() {
            /**
             *
             * @param s 输入框的原内容
             * @param start 索引字符
             * @param count 有count个字符被替换
             * @param after 替换count个字符的新的字符个数为after
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtils.validatePhone(s.toString())) {
                    getUI().changePhoneCheck(true);
                    if (getUI().isWholeInfo()) {
                        getUI().getRegisterButton().setEnabled(true);
                    } else {
                        getUI().getRegisterButton().setEnabled(false);
                    }
                } else {
                    getUI().changePhoneCheck(false);
                    if (getUI().isWholeInfo()) {
                        getUI().getRegisterButton().setEnabled(true);
                    } else {
                        getUI().getRegisterButton().setEnabled(false);
                    }
                }
            }
        });
    }

    public void registerTemp(TempUserInfo tempUserInfo) {

        Logger.e(tempUserInfo.toString());

        new SocketThread(tempUserInfo).run();

    }

    class SocketThread extends Thread {

        private TempUserInfo mTempUserInfo;
        /**
         * 0 表示连接上
         * 1 发送deviceId
         * 2 发送用户信息
         */
        private int SocketType = 0;

        public SocketThread(TempUserInfo tempUserInfo) {
            mTempUserInfo = tempUserInfo;
        }

        private DaoUtils mDaoUtils;

        @Override
        public void run() {

            mDaoUtils = DaoUtils.getInstance(getUI().getOwnerActivity().getApplicationContext());

            Logger.e("SocketThread");

            SocketClient socketClient = new SocketClient(new SocketClientAddress(SPModel.getSocketIp(), SPModel.getSocketPort(), 5*1000));

            socketClient.setCharsetName("UTF-8");
            socketClient.getSocketPacketHelper().setSendHeaderData(null); // 设置发送消息时自动在消息头部添加的信息，远程端收到此信息后表示一条消息开始，用于解决粘包分包问题，若为null则不添加头部信息
            socketClient.getSocketPacketHelper().setSendTrailerString(null);


            Gson gson = new Gson();
            String jsonUserInfo = gson.toJson(mTempUserInfo);
            final User userInfo = new User();
            userInfo.setUser_name(mTempUserInfo.getName());
            userInfo.setUser_info(jsonUserInfo);
            userInfo.setUser_flag("2");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            userInfo.setRegister_time(format.format(new Date()));
            userInfo.setUser_id("0");
            userInfo.setValid_time_start(mTempUserInfo.getStartDate() + "-" + mTempUserInfo.getStartTime());
            userInfo.setValid_time_stop(mTempUserInfo.getStopDate() + "-" + mTempUserInfo.getStopTime());
            userInfo.setValid_time_week(mTempUserInfo.getWeek());
            userInfo.setIris_path("/");
            Logger.e(userInfo.toString());
            final String jsonUser = gson.toJson(userInfo);

            socketClient.registerSocketClientDelegate(new SocketClientDelegate() {
                int i = 5;
                @Override
                public void onConnected(SocketClient socketClient) {
                    Logger.e("Socket 已连接");
                    SocketType = 0;
                    socketClient.sendString(SPModel.getDeviceId());
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
                        switch(SocketType){
                            case 0:
                                socketClient.sendString("F201" + jsonUser);
                                getUI().getRegisterLayout().setVisibility(View.GONE);
                                getUI().getWaitRegisterLayout().setVisibility(View.VISIBLE);
                                Random random=new Random();
                                int time = random.nextInt(4000);
                                Observable.timer(time, TimeUnit.MILLISECONDS)
                                        .subscribeOn(Schedulers.computation())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Action1<Long>() {
                                            @Override
                                            public void call(Long aLong) {
                                                getUI().getText().setText("1");
                                            }
                                        });

                                time = random.nextInt(4000);
                                Observable.timer(time, TimeUnit.MILLISECONDS)
                                        .subscribeOn(Schedulers.computation())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Action1<Long>() {
                                            @Override
                                            public void call(Long aLong) {
                                                getUI().getText().setText("2");
                                            }
                                        });


                                SocketType = 1;
                                break;
                            case 1:
                                socketClient.sendString("EXIT");

                                getUI().getText().setText("3");
                                getUI().getCheck().setChecked(true);
                                ToastUtils.toastShort("注册成功");
                                getUI().getRegisterLayout().setVisibility(View.VISIBLE);
                                getUI().getWaitRegisterLayout().setVisibility(View.GONE);

                                mDaoUtils.saveUser(userInfo);

                                SocketType = 0;
                                break;

                        }

                    } else {
                        Logger.e("message = failed");
                        switch(SocketType){
                            case 0:
                                Logger.e("deviceId 无效");
                                SocketType = 1;
                                break;
                            case 1:
                                socketClient.sendString("EXIT");
                                getUI().getText().setText("0");
                                getUI().getCheck().setChecked(false);
                                ToastUtils.toastShort("注册失败，请重新扫描虹膜");
                                getUI().getRegisterLayout().setVisibility(View.VISIBLE);
                                getUI().getWaitRegisterLayout().setVisibility(View.GONE);

                                SocketType = 0;
                                break;
                        }
                    }
                }
            });
            socketClient.connect();
        }
    }
}
