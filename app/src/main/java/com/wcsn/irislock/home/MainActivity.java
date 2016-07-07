package com.wcsn.irislock.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.app.mvp.BaseMVPActivity;
import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.ImaginationUnlimited.library.utils.log.Logger;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wcsn.irislock.R;
import com.wcsn.irislock.alert.AlertActivity;
import com.wcsn.irislock.app.App;
import com.wcsn.irislock.utils.JPushUtils;
import com.wcsn.irislock.utils.image.ImageLoaderFactory;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BaseMVPActivity<MainPresenter> implements IMainUI {

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
//        List<Drawable> overlayList = new ArrayList<>();
//        overlayList.add(getResources().getDrawable(R.drawable.grid));
//        overlayList.add(getResources().getDrawable(R.drawable.shield_shadow));
//        overlayList.add(getResources().getDrawable(R.drawable.shield));
//        overlayList.add(getResources().getDrawable(R.drawable.shield_glisten));
//        GenericDraweeHierarchyBuilder builder =
//                new GenericDraweeHierarchyBuilder(getResources());
//        GenericDraweeHierarchy hierarchy = builder
//                .setOverlays(overlayList)
//                .build();
//        mIrisImage.setHierarchy(hierarchy);

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
        mPictureLayout = finder.find(R.id.pictureLayout);
        mAuthorizationLayout = finder.find(R.id.authorizationLayout);

        getPresenter().loadWeatherData();

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
    public void loadPowerImage(String power) {

    }

    @Override
    public void loadPowerText(String power) {

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
}
