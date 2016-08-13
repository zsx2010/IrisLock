package com.wcsn.irislock.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.sp.SPModel;
import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.app.mvp.BaseMVPActivity;
import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.ImaginationUnlimited.library.utils.log.Logger;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wcsn.irislock.R;
import com.wcsn.irislock.admin.AdminActivity;
import com.wcsn.irislock.alert.AlertActivity;
import com.wcsn.irislock.app.App;
import com.wcsn.irislock.authorize.AuthorizeActivity;
import com.wcsn.irislock.authorize.AuthorizeListActivity;
import com.wcsn.irislock.monitor.MonitorActivity;
import com.wcsn.irislock.utils.image.ImageLoaderFactory;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BaseMVPActivity<MainPresenter> implements IMainUI, Thread.UncaughtExceptionHandler {

    private SimpleDraweeView mWeatherSimpleDraweeView;
    private TextView mTemperatureTextView;
    private TextView mWeatherTextView;
    private ImageView mSettingView;
    private SimpleDraweeView mIrisImage;
    private SimpleDraweeView mInfoImage;
    private TextView mSafeText;
    private SimpleDraweeView mPowerImage;
    private TextView mPowerText;

    private LinearLayout mAlertLayout;
    private LinearLayout mAdminLayout;
    private LinearLayout mPictureLayout;
    private LinearLayout mAuthorizationLayout;



    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        Logger.e("MainActivity");

        ViewFinder finder = new ViewFinder(this);
        mWeatherSimpleDraweeView = finder.find(R.id.weatherImageView);
        mTemperatureTextView = finder.find(R.id.temperatureTextView);
        mWeatherTextView = finder.find(R.id.weatherTextView);
        mSettingView = finder.find(R.id.setting);

        mIrisImage = finder.find(R.id.irisImage);

        ImageLoaderFactory.getLoader(mIrisImage).showImage(mIrisImage,
                "res:///" + R.drawable.grid, null);

        mInfoImage = finder.find(R.id.infoImage);

        ImageLoaderFactory.getLoader(mInfoImage).showImage(mInfoImage,
                "res:///" + R.drawable.message_info, null);
        mSafeText = finder.find(R.id.safeText);
        mPowerImage = finder.find(R.id.powerImage);

        ImageLoaderFactory.getLoader(mPowerImage).showImage(mPowerImage,
                "res:///" + R.drawable.power_100, null);

        mPowerText = finder.find(R.id.powerText);

        mPowerText.setText("100%");

        mAlertLayout = finder.find(R.id.alertLayout);

        mAlertLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertActivity.launch(MainActivity.this);
            }
        });

        mAdminLayout = finder.find(R.id.adminLayout);
        mAdminLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminActivity.launch(MainActivity.this);
            }
        });
        mPictureLayout = finder.find(R.id.pictureLayout);
        mPictureLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonitorActivity.launch(MainActivity.this);
            }
        });
        mAuthorizationLayout = finder.find(R.id.authorizationLayout);
        mAuthorizationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthorizeListActivity.launch(MainActivity.this);
            }
        });

        //加载天气
        getPresenter().loadWeatherData();

        //是否有远程授权信息需要处理
        if (SPModel.getIsAuthorize()) {
            Logger.e(getClass().getSimpleName(), SPModel.getAuthorize().toString());
            final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
            dialog.show();
            Window window = dialog.getWindow();
            window.setContentView(R.layout.dialog_authorize);
            ImageView closeView = (ImageView) window.findViewById(R.id.close);
            Button button = (Button) window.findViewById(R.id.button);
            closeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SPModel.putAuthorize(null);
                    SPModel.putIsAuthorize(false);
                    dialog.dismiss();
                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AuthorizeActivity.launch(MainActivity.this);
                    dialog.dismiss();
                }
            });
        }

        //加载电池电量信息
        loadPower();

        Thread.setDefaultUncaughtExceptionHandler(this);


    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected IUI getUI() {
        return this;
    }

    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,MainActivity.class);
        activity.startActivity(intent);
    }


    @Override
    public void loadWeather(String temp, String weather, int imageId) {
        ImageLoaderFactory.getLoader(mWeatherSimpleDraweeView).showImage(mWeatherSimpleDraweeView, imageId, null);
        mWeatherTextView.setText(weather);
        mTemperatureTextView.setText(temp);

    }

    @Override
    public void loadIrisImage(String state) {

    }

    @Override
    public void loadInfoImage(String state) {



    }

    @Override
    public void loadSafeState(String state) {

    }

    @Override
    public void loadPower() {
        int power = Integer.parseInt(SPModel.getPower());
        if (power <= 20) {
            ImageLoaderFactory.getLoader(mPowerImage)
                    .showImage(mPowerImage, "res:///" + R.drawable.power_20, null);
            mPowerText.setText("20%");
            mPowerText.setTextColor(Color.RED);
        } else if (power <= 50) {
            ImageLoaderFactory.getLoader(mPowerImage)
                    .showImage(mPowerImage, "res:///" + R.drawable.power_50, null);
            mPowerText.setText("50%");
            mPowerText.setTextColor(Color.YELLOW);
        } else if (power <= 80) {
            ImageLoaderFactory.getLoader(mPowerImage)
                    .showImage(mPowerImage, "res:///" + R.drawable.power_80, null);
            mPowerText.setText("80%");
            mPowerText.setTextColor(Color.GREEN);
        } else {
            ImageLoaderFactory.getLoader(mPowerImage)
                    .showImage(mPowerImage, "res:///" + R.drawable.power_100, null);
            mPowerText.setText("100%");
            mPowerText.setTextColor(Color.GREEN);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(App.getInstance().getContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(App.getInstance().getContext());
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Logger.e(ex.toString());
    }
}
