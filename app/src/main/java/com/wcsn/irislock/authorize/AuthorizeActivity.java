package com.wcsn.irislock.authorize;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.RESTfulFactory;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.AuthorizeInfo;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.sp.SPModel;
import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.app.mvp.BaseMVPActivity;
import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wcsn.irislock.R;
import com.wcsn.irislock.utils.image.ImageLoaderFactory;

/**
 * Created by suiyue on 2016/7/14 0014.
 */
public class AuthorizeActivity extends BaseMVPActivity<AuthorizePresenter> implements IAuthorizeUI {


    private ImageView back;
    private SimpleDraweeView mAuthorizationImage;
    private TextView mTakeTimeText;
    private Button mAcceptButton;
    private Button mRejectButton;
    private TextView mCountDownText;
    private TextView mAuthorizationDesc;

    private AuthorizeInfo mAuthorizeInfo;
    private String mImageName;

    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,AuthorizeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_authorized);
        mAuthorizeInfo = SPModel.getAuthorize();

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

        if (mAuthorizeInfo != null) {
            ImageLoaderFactory.getLoader(mAuthorizationImage).showImage(mAuthorizationImage,
                    RESTfulFactory.getUrlBase() + mAuthorizeInfo.getImageUrl(), null);
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

    }

    private CountDownTimer mTimer = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mCountDownText.setText(millisUntilFinished/1000 + "S");
        }

        @Override
        public void onFinish() {
            mCountDownText.setText("0S");
            mAcceptButton.setBackgroundResource(R.drawable.bg_button_register_unable);
            mAcceptButton.setText("授权已过期");
            mRejectButton.setVisibility(View.GONE);
            mAuthorizationDesc.setText("请联系发起人重新进行授权操作");

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
}
