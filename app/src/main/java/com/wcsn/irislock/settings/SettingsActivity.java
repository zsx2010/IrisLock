package com.wcsn.irislock.settings;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.app.mvp.BaseMVPActivity;
import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.wcsn.irislock.R;

/**
 * Created by suiyue on 2016/8/16 0016.
 */
public class SettingsActivity extends BaseMVPActivity<SettingsPresenter> implements ISettingsUI {


    private RelativeLayout mNetworkLayout;
    private RelativeLayout mPasswordLayout;
    private RelativeLayout mSoundLayout;
    private RelativeLayout mAboutLayout;

    private View mShadow;

    private ImageView mBack;

    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,SettingsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_settings);

        ViewFinder finder = new ViewFinder(this);

        mNetworkLayout = finder.find(R.id.wifiSetting);
        mPasswordLayout = finder.find(R.id.passwordSetting);
        mPasswordLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShadow.setVisibility(View.VISIBLE);
                choosePwdWay(v, getOwnerActivity());
            }
        });

        mSoundLayout = finder.find(R.id.soundSetting);
        mAboutLayout = finder.find(R.id.about);

        mShadow = finder.find(R.id.shadow);

        mBack = finder.find(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected SettingsPresenter createPresenter() {
        return new SettingsPresenter();
    }

    @Override
    protected IUI getUI() {
        return this;
    }


    public void choosePwdWay(View view, final Activity context) {
        View contentView = LayoutInflater.from(context).inflate(
                R.layout.popup_password_way, null);
        ViewFinder finder = new ViewFinder(contentView);

        TextView numberPwd = finder.find(R.id.numberPwd);
        TextView imagePwd = finder.find(R.id.imagePwd);
        TextView cancel = finder.find(R.id.cancel);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);


        numberPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShadow.setVisibility(View.GONE);
                NumberPwdActivity.launch(getOwnerActivity());
                popupWindow.dismiss();
            }
        });

        imagePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShadow.setVisibility(View.GONE);
                ImagePwdActivity.launch(getOwnerActivity());
                popupWindow.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mShadow.setVisibility(View.GONE);
                popupWindow.dismiss();

            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mShadow.setVisibility(View.GONE);
            }
        });
    }
}
