package com.wcsn.irislock.login;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ImaginationUnlimited.library.app.mvp.IUI;

/**
 * Created by suiyue on 2016/6/14 0014.
 */
public interface IRegisterUI extends IUI{
    void loadAddress(String address);
    EditText getNameEdit();
    EditText getPhoneEdit();
    EditText getStreetEdit();
    void changeNameCheck(boolean isChecked);
    void changePhoneCheck(boolean isChecked);
    void changeAddressCheck(boolean isChecked);
    void changeStreetCheck(boolean isChecked);
    boolean isWholeInfo();
    Button getRegisterButton();

    RelativeLayout getRegisterLayout();
    LinearLayout getWaitRegisterLayout();

}
