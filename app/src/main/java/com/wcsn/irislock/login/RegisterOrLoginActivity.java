package com.wcsn.irislock.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.utils.network.NetworkUtils;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wcsn.irislock.R;
import com.wcsn.irislock.information.InformationActivity;
import com.wcsn.irislock.utils.image.ImageLoaderFactory;

/**
 * Created by suiyue on 2016/6/30 0030.
 */
public class RegisterOrLoginActivity extends BaseActivity {

    private SimpleDraweeView mIsWifiConnectedView;
    private TextView mIsWifiConnectedText;

    private Button mAdminRegisterButton;
    private Button mIsAdminButton;


    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,RegisterOrLoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_or_login);

        ViewFinder finder = new ViewFinder(this);

        mIsWifiConnectedView = finder.find(R.id.isWifiConnectedIcon);
        mIsWifiConnectedText = finder.find(R.id.isWifiConnectedText);

        mAdminRegisterButton = finder.find(R.id.adminRegisterButton);
        mIsAdminButton = finder.find(R.id.isAdminButton);

        if(NetworkUtils.isWifiConnected(this)) {
            ImageLoaderFactory.getLoader(mIsWifiConnectedView).showImage(mIsWifiConnectedView,
                    "res:///" + R.drawable.wifi, null);
            mIsWifiConnectedText.setText(R.string.wifiConnected);
        } else {
            ImageLoaderFactory.getLoader(mIsWifiConnectedView).showImage(mIsWifiConnectedView,
                    "res:///" + R.drawable.wifi_no, null);
            mIsWifiConnectedText.setText(R.string.wifiUnConnected);
        }

        mAdminRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.launch(RegisterOrLoginActivity.this);
            }
        });

        mIsAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminCheckActivity.launch(RegisterOrLoginActivity.this);
            }
        });
    }
}
