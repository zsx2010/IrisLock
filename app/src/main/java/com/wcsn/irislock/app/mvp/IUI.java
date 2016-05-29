package com.wcsn.irislock.app.mvp;

import com.wcsn.irislock.app.BaseActivity;

/**
 * Created by suiyue on 2016/5/28 0028.
 * MVP View层协议
 */
public interface IUI extends IUIState{


    void finishOwnerActivity();

    BaseActivity getOwnerActivity();
}
