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
public class AdminOrVisitorActivity extends BaseActivity {


    private SimpleDraweeView mIsWifiConnectedView;
    private TextView mIsWifiConnectedText;

    private Button mAdminButton;
    private Button mVisitorButton;


    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,AdminOrVisitorActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_or_visitor);

        ViewFinder finder = new ViewFinder(this);

        mIsWifiConnectedView = finder.find(R.id.isWifiConnectedIcon);
        mIsWifiConnectedText = finder.find(R.id.isWifiConnectedText);

        mAdminButton = finder.find(R.id.adminButton);
        mVisitorButton = finder.find(R.id.visitorButton);

        if(NetworkUtils.isWifiConnected(this)) {
            ImageLoaderFactory.getLoader(mIsWifiConnectedView).showImage(mIsWifiConnectedView,
                    "res:///" + R.drawable.wifi, null);
            mIsWifiConnectedText.setText(R.string.wifiConnected);
        } else {
            ImageLoaderFactory.getLoader(mIsWifiConnectedView).showImage(mIsWifiConnectedView,
                    "res:///" + R.drawable.wifi_no, null);
            mIsWifiConnectedText.setText(R.string.wifiUnConnected);
        }

        mAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterOrLoginActivity.launch(AdminOrVisitorActivity.this);
            }
        });

        mVisitorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InformationActivity.launch(AdminOrVisitorActivity.this);
            }
        });
    }
}
