package com.wcsn.irislock.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.sp.SPModel;
import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.app.mvp.BasePresenter;
import com.ImaginationUnlimited.library.utils.app.StringUtils;
import com.ImaginationUnlimited.library.utils.log.Logger;
import com.ImaginationUnlimited.library.utils.toast.ToastUtils;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.vilyever.socketclient.SocketClient;
import com.vilyever.socketclient.helper.SocketClientAddress;
import com.vilyever.socketclient.helper.SocketClientDelegate;
import com.vilyever.socketclient.helper.SocketResponsePacket;
import com.wcsn.irislock.home.MainActivity;
import com.wcsn.irislock.login.bean.AdminInfo;
import com.wcsn.irislock.login.bean.UserInfo;
import com.wcsn.irislock.utils.AssetsUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import cn.qqtheme.framework.picker.AddressPicker;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by suiyue on 2016/6/14 0014.
 */
public class RegisterPresenter extends BasePresenter<IRegisterUI>{

    private Activity activity;
    private ProgressDialog dialog;
    private String selectedProvince = "", selectedCity = "", selectedCounty = "";
    private boolean hideCounty=false;
    private BaseActivity mActivity;



    private ArrayList<AddressPicker.Province> data;

    public void InitAddress(String... params) {
        mActivity = getUI().getOwnerActivity();
        if (params != null) {
            switch (params.length) {
                case 1:
                    selectedProvince = params[0];
                    break;
                case 2:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    break;
                case 3:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    selectedCounty = params[2];
                    break;
                default:
                    break;
            }
        }
        data = new ArrayList<>();
        try {
            String json = AssetsUtils.readText(getContext(), "city.json");
            data.addAll(JSON.parseArray(json, AddressPicker.Province.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void showAddressPicker() {
        if (data.size() > 0) {
            AddressPicker picker = new AddressPicker(getUI().getOwnerActivity(), data);
            picker.setHideCounty(hideCounty);
            picker.setSelectedItem(selectedProvince, selectedCity, selectedCounty);
            picker.setLineColor(Color.GRAY);
            picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                @Override
                public void onAddressPicked(String province, String city, String county) {
                    if (county==null){
                        getUI().loadAddress(province + "-" + city);
                        getUI().changeAddressCheck(true);
                        if (getUI().isWholeInfo()) {
                            getUI().getRegisterButton().setEnabled(true);
                        } else {
                            getUI().getRegisterButton().setEnabled(false);
                        }

                    } else {
                        getUI().loadAddress(province + "-" + city + "-" + county);
                        getUI().changeAddressCheck(true);
                        if (getUI().isWholeInfo()) {
                            getUI().getRegisterButton().setEnabled(true);
                        } else {
                            getUI().getRegisterButton().setEnabled(false);
                        }
                    }
                }
            });
            picker.show();
        } else {
            Toast.makeText(activity, "数据初始化失败", Toast.LENGTH_SHORT).show();
        }
    }

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

        getUI().getStreetEdit().addTextChangedListener(new TextWatcher() {
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
                if (StringUtils.isNullOrEmpty(s.toString())) {
                    getUI().changeStreetCheck(false);
                    if (getUI().isWholeInfo()) {
                        getUI().getRegisterButton().setEnabled(true);
                    } else {
                        getUI().getRegisterButton().setEnabled(false);
                    }
                } else {
                    getUI().changeStreetCheck(true);
                    if (getUI().isWholeInfo()) {
                        getUI().getRegisterButton().setEnabled(true);
                    } else {
                        getUI().getRegisterButton().setEnabled(false);
                    }
                }
            }
        });
    }

    public void registerAdmin(AdminInfo adminInfo) {

        Logger.e(adminInfo.toString());

        new SocketThread(adminInfo).run();


    }

    class SocketThread extends Thread {

        private AdminInfo mAdminInfo;
        /**
         * 0 表示连接上
         * 1 发送deviceId
         * 2 发送用户信息
         */
        private int SocketType = 0;

        public SocketThread(AdminInfo adminInfo) {
            mAdminInfo = adminInfo;
        }



        @Override
        public void run() {

            Logger.e("SocketThread");

            SocketClient socketClient = new SocketClient(new SocketClientAddress(SPModel.getSocketIp(), SPModel.getSocketPort(), 5*1000));

            socketClient.setCharsetName("UTF-8");
            socketClient.getSocketPacketHelper().setSendHeaderData(null); // 设置发送消息时自动在消息头部添加的信息，远程端收到此信息后表示一条消息开始，用于解决粘包分包问题，若为null则不添加头部信息
            socketClient.getSocketPacketHelper().setSendTrailerString(null);


            Gson gson = new Gson();
            String jsonUserInfo = gson.toJson(mAdminInfo);
            UserInfo userInfo = new UserInfo();
            userInfo.setUser_name(mAdminInfo.getName());
            userInfo.setUser_info(jsonUserInfo);
            userInfo.setUser_flag("1");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            userInfo.setRegister_time(format.format(new Date()));
            userInfo.setUser_id("0");
            userInfo.setValid_time_start("");
            userInfo.setValid_time_stop("");
            userInfo.setValid_time_week("");
            userInfo.setIris_path("/");
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
                    MainActivity.launch(mActivity);
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
