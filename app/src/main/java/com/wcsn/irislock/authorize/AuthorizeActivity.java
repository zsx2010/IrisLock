package com.wcsn.irislock.authorize;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.RESTfulFactory;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.AuthorizeInfo;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.sp.SPModel;
import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.app.mvp.BaseMVPActivity;
import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.ImaginationUnlimited.library.utils.dialog.DialogUtils;
import com.ImaginationUnlimited.library.utils.log.Logger;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wcsn.irislock.R;
import com.wcsn.irislock.app.event.EvIsOpenDoor;
import com.wcsn.irislock.bean.Authorize;
import com.wcsn.irislock.utils.DaoUtils;
import com.wcsn.irislock.utils.image.ImageLoaderFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by suiyue on 2016/7/14 0014.
 */
public class AuthorizeActivity extends BaseMVPActivity<AuthorizePresenter> implements IAuthorizeUI {


    private static final String TAG = AuthorizeActivity.class.getSimpleName();

    private ImageView back;
    private SimpleDraweeView mAuthorizationImage;
    private TextView mTakeTimeText;
    private Button mAcceptButton;
    private Button mRejectButton;
    private TextView mCountDownText;
    private TextView mAuthorizationDesc;

    private AuthorizeInfo mAuthorizeInfo;
    private String mImageName;

    private Dialog mDialog;

    private LinearLayout mAlreadyAuthorizedLayout;
    private TextView mOpenDoorCountText;
    private SimpleDraweeView mIsOpenDoorView;
    private TextView mIsOpenDoorText;

    private DaoUtils mDaoUtils;


    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,AuthorizeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_authorized);
        mAuthorizeInfo = SPModel.getAuthorize();
        mDaoUtils = DaoUtils.getInstance(this);

        ViewFinder finder = new ViewFinder(this);
        back = finder.find(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAuthorizationImage = finder.find(R.id.authorizationImage);

        mTakeTimeText = finder.find(R.id.takeTime);
        mAcceptButton = finder.find(R.id.acceptButton);
        mRejectButton = finder.find(R.id.rejectButton);
        mCountDownText = finder.find(R.id.countDownText);
        mAuthorizationDesc = finder.find(R.id.authorizationDesc);


        mAlreadyAuthorizedLayout = finder.find(R.id.alreadyAuthorization);
        mOpenDoorCountText = finder.find(R.id.openDoorCountDownText);
        mIsOpenDoorView = finder.find(R.id.isOpenDoorView);
        mIsOpenDoorText = finder.find(R.id.isOpenDoorText);

        if (mAuthorizeInfo != null) {
            Logger.e(TAG, mAuthorizeInfo.getImageUrl());
            String url = RESTfulFactory.getUrlBase() + mAuthorizeInfo.getImageUrl();
            url = url.replace(" ", "%20");
            Logger.e(TAG,url);

            ImageLoaderFactory.getLoader(mAuthorizationImage).showImage(mAuthorizationImage, url, null);
            mTakeTimeText.setText("拍摄时间：" + mAuthorizeInfo.getTime());
            mTimer.start();
            mImageName = mAuthorizeInfo.getImageUrl().split("/")[mAuthorizeInfo.getImageUrl().split("/").length - 1];
            SPModel.putIsAuthorize(false);
            SPModel.putAuthorize(null);
        }

        mAcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().Authorize(true, mImageName);
                DialogUtils.showWaitingDialog(getBaseContext(), "正在上传授权信息，请稍后");
                mTimer.cancel();
            }
        });

        mRejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().Authorize(false, mImageName);
                mTimer.cancel();
            }
        });

        EventBus.getDefault().register(this);

    }


    private CountDownTimer mTimer = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mCountDownText.setText(millisUntilFinished/1000 + "S");
        }

        @Override
        public void onFinish() {
            getPresenter().Authorize(false, mImageName);
            mCountDownText.setText("0S");
            mAcceptButton.setBackgroundResource(R.drawable.bg_button_register_unable);
            mAcceptButton.setText("授权已过期");
            mRejectButton.setVisibility(View.GONE);
            mAuthorizationDesc.setText("请联系发起人重新进行授权操作");

        }
    };


    private CountDownTimer mOpenTimer = new CountDownTimer(100000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mOpenDoorCountText.setText(millisUntilFinished/1000 + "S");
        }

        @Override
        public void onFinish() {
            mOpenDoorCountText.setText("0S");

        }
    };

    @Override
    protected AuthorizePresenter createPresenter() {
        return new AuthorizePresenter();
    }

    @Override
    protected IUI getUI() {
        return this;
    }

    @Override
    public Dialog getDialog() {
        return mDialog;
    }

    @Override
    public void waitOpenDoor() {

        mAcceptButton.setVisibility(View.GONE);
        mCountDownText.setVisibility(View.GONE);
        mRejectButton.setVisibility(View.GONE);
        mAuthorizationDesc.setVisibility(View.GONE);

        mAlreadyAuthorizedLayout.setVisibility(View.VISIBLE);
        mOpenDoorCountText.setVisibility(View.VISIBLE);
        mIsOpenDoorView.setVisibility(View.VISIBLE);
        mIsOpenDoorText.setVisibility(View.VISIBLE);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mTakeTimeText.setText("授权时间 :" + format.format(new Date()));

        mIsOpenDoorText.setText("等待开门信息");

        ImageLoaderFactory.getLoader(mAuthorizationImage).showImage(mAuthorizationImage,
                "res:///" + R.drawable.door_close, null);
        mOpenTimer.start();

    }

    @Subscribe
    public void isOpenDoor(EvIsOpenDoor isOpenDoor) {
        if (isOpenDoor.isOpenDoor()) {

            mIsOpenDoorText.setText("门已开启");
            ImageLoaderFactory.getLoader(mAuthorizationImage).showImage(mAuthorizationImage,
                    "res:///" + R.drawable.door_open, null);
            Authorize authorize = mDaoUtils.loadAuthorizeByImagePath(mAuthorizeInfo.getImageUrl());
            authorize.setIsOpen(true);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            authorize.setOpenTime(format.format(new Date()));
            mDaoUtils.updateAuthorize(authorize);
        }
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
