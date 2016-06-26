package com.ImaginationUnlimited.library.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.ImaginationUnlimited.library.R;
import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.ImaginationUnlimited.library.utils.app.DeviceUtils;
import com.ImaginationUnlimited.library.utils.dialog.DialogUtils;


/**
 *
 * 所有Fragment的基类.
 *
 * @author wangheng
 *
 */
public abstract class BaseFragment extends Fragment implements IUI {

    /**
     * 调用Fragment的startActivityForResult的child *
     */
    protected BaseFragment startActivityForResultChild = null;

    private boolean isPaused;
    private boolean isStoped;
    private boolean isDestoryed;
    private boolean isDetached;
    private boolean isHidden;
    private boolean isVisibleToUser = false;
    private Dialog mWaitingDialog;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        isDetached = false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isDetached = false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDestoryed = false;
    }

    /**
     * 设置状态栏颜色
     * @param color 颜色，必须
     */
    public void setStatusBarColor(@ColorInt int color){
        if(getActivity() == null){
            return;
        }
        ViewGroup view = (ViewGroup) getActivity().findViewById(Window.ID_ANDROID_CONTENT);
        View rootView = null;
        if(view != null && (rootView = view.getChildAt(0)) != null){
            rootView.setBackgroundColor(color);
            if(DeviceUtils.hasKITKAT()) {
                rootView.setFitsSystemWindows(true);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        isStoped = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        isPaused = false;

    }

    @Override
    public void onPause() {
        isPaused = true;
        super.onPause();

    }

    @Override
    public void onStop() {
        isStoped = true;
        super.onStop();
    }

    @Override
    public void onDestroy() {
        isDestoryed = true;
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        isDetached = true;
        super.onDetach();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 一定不要干掉这段代码
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 设置调用Fragment的startActivityForResult的child. <br/>
     *
     * @param child 调用Fragment的startActivityForResult的child
     * @author wangheng
     */
    public void setStartActivityForResultChild(BaseFragment child) {
        this.startActivityForResultChild = child;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        isHidden = hidden;
        super.onHiddenChanged(hidden);
    }

    @Override
    public final boolean isPaused() {
        return isPaused;
    }

    @Override
    public final boolean isStoped() {
        return isStoped;
    }

    @Override
    public final boolean isDestoryed() {
        return isDestoryed;
    }

    public final boolean isFragmentDetached() {
        return isDetached;
    }

    @Override
    public final boolean isFragmentHidden() {
        return isHidden;
    }

    @Override
    public final boolean isVisibleToUser() {
        return isVisibleToUser;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isVisibleToUser = isVisibleToUser;
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public final void showWaitingDialog() {
        showWaitingDialog(getString(R.string.dialog_wait_msg_default));
    }

    @Override
    public final void showWaitingDialog(String text){
        dismissWaitingDialogIfShowing();
        if (!isDetached() && !isDestoryed() && !isHidden() && getActivity() != null) {
            mWaitingDialog = DialogUtils.showWaitingDialog(getActivity(), text);
        }
    }

    /**
     * 如果等待dialog正在显示，则dismiss掉它
     */
    public final void dismissWaitingDialogIfShowing() {
        if (!isDetached() && !isDestoryed() && mWaitingDialog != null
                && mWaitingDialog.isShowing()) {
            mWaitingDialog.dismiss();
            mWaitingDialog = null;
        }
    }

    @Override
    public void finishOwnerActivity() {
        if(getActivity() != null){
            getActivity().finish();
        }
    }

    @Override
    public BaseActivity getOwnerActivity() {
        return (BaseActivity) getActivity();
    }
}
