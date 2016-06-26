package com.ImaginationUnlimited.library.app.mvp;

import com.ImaginationUnlimited.library.app.BaseActivity;

/**
 * MVP的View层协议.
 *
 * @author wangheng
 *
 */
public interface IUI extends IUIState {
    /**
     * dismissWaitingDialogIfShowing:如果加载对话框正在显示，则dismiss掉它. <br/>
     */
    void dismissWaitingDialogIfShowing();

    /**
     * showWaitingDialog:显示正在加载对话框. <br/>
     *
     * @param text 指定的字符串
     *
     */
    void showWaitingDialog(String text);

    /**
     * showWaitingDialog:显示正在加载对话框. <br/>
     */
    void showWaitingDialog();

    void finishOwnerActivity();

    BaseActivity getOwnerActivity();
}
