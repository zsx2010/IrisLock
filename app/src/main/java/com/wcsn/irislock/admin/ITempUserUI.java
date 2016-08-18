package com.wcsn.irislock.admin;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ImaginationUnlimited.library.app.mvp.IUI;

/**
 * Created by suiyue on 2016/7/8 0008.
 */
public interface ITempUserUI extends IUI{
    EditText getNameEdit();
    EditText getPhoneEdit();

    void changeNameCheck(boolean isChecked);
    void changePhoneCheck(boolean isChecked);

    boolean isWholeInfo();
    Button getRegisterButton();

    RelativeLayout getRegisterLayout();
    LinearLayout getWaitRegisterLayout();

    TextView getText();
    CheckBox getCheck();

}
