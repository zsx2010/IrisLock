package com.wcsn.irislock.authorize;

import android.app.Dialog;

import com.ImaginationUnlimited.library.app.mvp.IUI;

/**
 * Created by suiyue on 2016/7/14 0014.
 */
public interface IAuthorizeUI extends IUI {
    Dialog getDialog();
    void waitOpenDoor();
}
