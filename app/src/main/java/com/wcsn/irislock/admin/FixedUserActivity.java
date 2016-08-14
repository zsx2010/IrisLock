package com.wcsn.irislock.admin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.app.mvp.BaseMVPActivity;
import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.ImaginationUnlimited.library.utils.network.NetworkUtils;
import com.ImaginationUnlimited.library.utils.toast.ToastUtils;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.wcsn.irislock.R;
import com.wcsn.irislock.app.App;
import com.wcsn.irislock.login.bean.AdminInfo;

import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by suiyue on 2016/8/14 0014.
 */
public class FixedUserActivity extends BaseMVPActivity<FixedUserPresenter>
        implements IFixedUserUI{

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

    private ImageView mBackView;
    private ImageView mAddUserView;

    private String sex = "male";

    private TextView mEnterCount;
    private CheckBox mCheckBox;

    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,FixedUserActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_fixed_user);
        ViewFinder finder = new ViewFinder(this);

        mBackView = finder.find(R.id.back);
        mBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAddUserView = finder.find(R.id.addUser);
        mAddUserView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionPicker picker = new OptionPicker(FixedUserActivity.this, new String[]{"永久用户", "临时用户"});
                picker.setOffset(1);
                picker.setSelectedIndex(1);
                picker.setTextSize(24);
                picker.setLineColor(Color.GRAY);
                picker.setTitleText("添加用户");
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(String option) {
                        if (option.equals("永久用户")) {
                            FixedUserActivity.launch(FixedUserActivity.this);
                        } else {
                            TempUserActivity.launch(FixedUserActivity.this);
                        }
                    }
                });
                picker.show();
            }
        });

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
                if (NetworkUtils.getWifiSSID(getBaseContext()).startsWith(App.mWifiName)) {
                    mAdminInfo.setAddress(mAddressEdit.getText().toString());
                    mAdminInfo.setName(mNameEdit.getText().toString());
                    mAdminInfo.setPhone(mPhoneEdit.getText().toString());
                    mAdminInfo.setSex(sex);
                    mAdminInfo.setStreet(mStreetEdit.getEditableText().toString());
                    getPresenter().registerFixedUser(mAdminInfo);
                } else {
                    ToastUtils.toastShort("请连接设备WiFi");
                }

            }
        });

        mRegisterLayout = finder.find(R.id.registerLayout);
        mWaitLayout = finder.find(R.id.waitLayout);

        mEnterCount = finder.find(R.id.enterCount);
        mCheckBox = finder.find(R.id.checkbox);

        getPresenter().InitAddress();
        getPresenter().bindWatcher();

    }

    @Override
    protected FixedUserPresenter createPresenter() {
        return new FixedUserPresenter();
    }

    @Override
    protected IUI getUI() {
        return this;
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

    @Override
    public TextView getText() {
        return mEnterCount;
    }

    @Override
    public CheckBox getCheck() {
        return mCheckBox;
    }
}
