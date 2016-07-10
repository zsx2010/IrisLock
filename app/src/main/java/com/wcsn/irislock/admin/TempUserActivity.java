package com.wcsn.irislock.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.app.mvp.BaseMVPActivity;
import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.wcsn.irislock.R;
import com.wcsn.irislock.admin.bean.TempUserInfo;

import java.util.Calendar;

import cn.qqtheme.framework.picker.DatePicker;

/**
 * Created by suiyue on 2016/7/8 0008.
 */
public class TempUserActivity extends BaseMVPActivity<TempUserPresenter> implements ITempUserUI{


    private RadioGroup mChooseSexGroup;
    private EditText mNameEdit;
    private CheckBox mNameCheck;
    private EditText mPhoneEdit;
    private CheckBox mPhoneCheck;
    private Button mRegisterButton;

    private TextView mStartDateText;
    private TextView mStopDateText;

    private RelativeLayout mValidityLayout;



    private TempUserInfo mTempUserInfo = new TempUserInfo();

    private String sex = "male";

    public static void launch(BaseActivity activity) {
        Intent intent = new Intent(activity, TempUserActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {

        setContentView(R.layout.activity_add_temp_user);
        ViewFinder finder = new ViewFinder(this);
        mNameEdit = finder.find(R.id.nameEdit);
        mNameCheck = finder.find(R.id.nameCheck);
        mPhoneEdit = finder.find(R.id.phoneEdit);
        mPhoneCheck = finder.find(R.id.phoneCheck);

        final Calendar c = Calendar.getInstance();
        mStartDateText = finder.find(R.id.startDate);
        mStartDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker datePicker = new DatePicker(getOwnerActivity());
                datePicker.setLabel("", "", "");
                datePicker.setTitleText("开始时间");
                datePicker.setCancelText("返回");
                datePicker.setSubmitText("下一步");
                datePicker.setRange(c.get(Calendar.YEAR), c.get(Calendar.YEAR) + 50);
                datePicker.setSelectedItem(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
                datePicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        mStartDateText.setText("年-" + year + "月-" + month + " 日-" + day);
                    }
                });
                datePicker.show();
            }
        });
        mStopDateText = finder.find(R.id.stopDate);
        mStopDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker datePicker = new DatePicker(getOwnerActivity());
                datePicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        mStopDateText.setText("年-" + year + "月-" + month + " 日-" + day);
                    }
                });
                datePicker.show();
            }
        });

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

        mValidityLayout = finder.find(R.id.validityLayout);

        mRegisterButton = finder.find(R.id.register);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTempUserInfo.setName(mNameEdit.getText().toString());
                mTempUserInfo.setPhone(mPhoneEdit.getText().toString());
                mTempUserInfo.setSex(sex);
            }
        });

    }

    @Override
    protected TempUserPresenter createPresenter() {
        return new TempUserPresenter();
    }

    @Override
    protected IUI getUI() {
        return this;
    }
}
