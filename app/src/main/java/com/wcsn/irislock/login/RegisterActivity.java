package com.wcsn.irislock.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.app.mvp.BaseMVPActivity;
import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.ImaginationUnlimited.library.utils.log.Logger;
import com.ImaginationUnlimited.library.utils.network.NetworkUtils;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wcsn.irislock.R;
import com.wcsn.irislock.login.bean.AdminInfo;
import com.wcsn.irislock.utils.image.ImageLoaderFactory;

/**
 * Created by suiyue on 2016/6/14 0014.
 */
public class RegisterActivity extends BaseMVPActivity<RegisterPresenter>
        implements IRegisterUI, Thread.UncaughtExceptionHandler{

    private SimpleDraweeView mIsWifiConnectedView;
    private TextView mIsWifiConnectedText;
    private RadioGroup mChooseSexGroup;
    private EditText mNameEdit;
    private CheckBox mNameCheck;
    private EditText mPhoneEdit;
    private CheckBox mPhoneCheck;
    private LinearLayout mAddressLayout;
    private TextView mAddressEdit;
    private CheckBox mAddressCheck;
    private EditText mStreetEdit;
    private CheckBox mStreetCheck;
    private Button mRegisterButton;

    private RelativeLayout mRegisterLayout;
    private LinearLayout mWaitLayout;

    private AdminInfo mAdminInfo = new AdminInfo();

    private String sex = "male";


    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,RegisterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {

        setContentView(R.layout.activity_register);

        ViewFinder finder = new ViewFinder(this);


        mIsWifiConnectedView = finder.find(R.id.isWifiConnectIcon);
        mIsWifiConnectedText = finder.find(R.id.isWifiConnectText);
        if(NetworkUtils.isWifiConnected(this)) {
            ImageLoaderFactory.getLoader(mIsWifiConnectedView).showImage(mIsWifiConnectedView,
                    "res:///" + R.drawable.wifi, null);
            mIsWifiConnectedText.setText(R.string.wifiConnected);
        } else {
            ImageLoaderFactory.getLoader(mIsWifiConnectedView).showImage(mIsWifiConnectedView,
                    "res:///" + R.drawable.wifi_no, null);
            mIsWifiConnectedText.setText(R.string.wifiUnConnected);
        }

        mNameEdit = finder.find(R.id.nameEdit);
        mNameCheck = finder.find(R.id.nameCheck);
        mPhoneEdit = finder.find(R.id.phoneEdit);
        mPhoneCheck = finder.find(R.id.phoneCheck);
        mStreetEdit = finder.find(R.id.streetEdit);
        mStreetCheck = finder.find(R.id.streetCheck);



        mAddressLayout = finder.find(R.id.addressLayout);
        mAddressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().showAddressPicker();
            }
        });
        mAddressEdit = finder.find(R.id.addressEdit);
        mAddressCheck = finder.find(R.id.addressCheck);

        mChooseSexGroup = finder.find(R.id.chooseSex);
        mChooseSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.maleRadio) {
                    sex = "male";
                } else {
                    sex = "female";
                }
            }
        });
        mRegisterButton = finder.find(R.id.register);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdminInfo.setAddress(mAddressEdit.getText().toString());
                mAdminInfo.setName(mNameEdit.getText().toString());
                mAdminInfo.setPhone(mPhoneEdit.getText().toString());
                mAdminInfo.setSex(sex);
                mAdminInfo.setStreet(mStreetEdit.getEditableText().toString());
                getPresenter().registerAdmin(mAdminInfo);
            }
        });

        mRegisterLayout = finder.find(R.id.registerLayout);
        mWaitLayout = finder.find(R.id.waitLayout);

        getPresenter().InitAddress();
        getPresenter().bindWatcher();
    }


    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected IUI getUI() {
        return this;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Logger.e(ex.toString());
    }

    @Override
    public void loadAddress(String address) {
        mAddressEdit.setText(address);
    }

    @Override
    public EditText getNameEdit() {
        return mNameEdit;
    }

    @Override
    public EditText getPhoneEdit() {
        return mPhoneEdit;
    }

    @Override
    public EditText getStreetEdit() {
        return mStreetEdit;
    }

    @Override
    public void changeNameCheck(boolean isChecked) {
        mNameCheck.setChecked(isChecked);
    }

    @Override
    public void changePhoneCheck(boolean isChecked) {
        mPhoneCheck.setChecked(isChecked);
    }

    @Override
    public void changeAddressCheck(boolean isChecked) {
        mAddressCheck.setChecked(isChecked);
    }

    @Override
    public void changeStreetCheck(boolean isChecked) {
        mStreetCheck.setChecked(isChecked);
    }

    @Override
    public boolean isWholeInfo() {
        if (mAddressCheck.isChecked() && mStreetCheck.isChecked()
                && mPhoneCheck.isChecked() && mNameCheck.isChecked()) {
            return true;
        }
        return false;
    }

    @Override
    public Button getRegisterButton() {
        return mRegisterButton;
    }

    @Override
    public RelativeLayout getRegisterLayout() {
        return mRegisterLayout;
    }

    @Override
    public LinearLayout getWaitRegisterLayout() {
        return mWaitLayout;
    }


}
