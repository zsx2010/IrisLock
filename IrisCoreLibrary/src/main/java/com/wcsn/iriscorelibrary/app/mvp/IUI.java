package com.wcsn.iriscorelibrary.app.mvp;

import com.wcsn.iriscorelibrary.app.BaseActivity;

/**
 * Created by suiyue on 2016/6/7 0007.
 */
public interface IUI extends IUIState {
    void finishOwnerActivity();

    BaseActivity getOwnerActivity();
}
