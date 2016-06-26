package com.ImaginationUnlimited.library.app;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ImaginationUnlimited.library.R;
import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.ImaginationUnlimited.library.utils.app.DeviceUtils;
import com.ImaginationUnlimited.library.utils.dialog.DialogUtils;
import com.ImaginationUnlimited.library.utils.topsnack.EvTopSnack;
import com.ImaginationUnlimited.library.utils.topsnack.TopSnackBus;
import com.androidadvance.topsnackbar.TSnackbar;

import org.greenrobot.eventbus.Subscribe;

/**
 * Activity基类，所有的Activity必须继承这个类.
 *
 * @author wangheng
 *
 */
@SuppressLint("Registered")
public abstract class BaseActivity extends FragmentActivity implements IUI {
    private boolean mIsActivityDestoryed = false;
    private boolean isPaused;
    private boolean isStoped;
    private Dialog mWaitingDialog = null;

    @SuppressWarnings("unchecked")
    protected <T extends View> T $view(int id){
        return (T) findViewById(id);
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T $view(@NonNull View parent,@IdRes int id){
        return (T) parent.findViewById(id);
    }

    protected View $inflate(@LayoutRes int id){
        return LayoutInflater.from(this).inflate(id,null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsActivityDestoryed = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPaused = false;
        TopSnackBus.getDefault().register(this);
//        if(App.getInstance().isDebug()) {
//            ViewServer.get(this).addWindow(this);
//            ViewServer.get(this).setFocusedWindow(this);
//        }
    }

    /**
     * 设置状态栏颜色
     * @param color 颜色，必须
     */
    public void setStatusBarColor(@ColorInt int color){
        ViewGroup view = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View rootView = null;
        if(view != null && (rootView = view.getChildAt(0)) != null){
//            if(DeviceUtils.hasKITKAT() && !DeviceUtils.hasLOLLIPOP()){
//            }
            rootView.setBackgroundColor(color);
            if(DeviceUtils.hasKITKAT()) {
                rootView.setFitsSystemWindows(true);
            }
        }
    }

    @Subscribe
    public void showTopSnack(final EvTopSnack event){
        final String str = event.getText();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TSnackbar snackbar = TSnackbar.make((getWindow().getDecorView().findViewById(android.R.id.content)),str,TSnackbar.LENGTH_SHORT);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(event.getBgColor());
                TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                if(textView!=null){
                    textView.setTextColor(event.getTextColor());
                    textView.setGravity(Gravity.CENTER);
                }
                snackbar.setDuration(TSnackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });
    }

    @Override
    protected void onPause() {
        TopSnackBus.getDefault().unregister(this);
        try {
            super.onPause();
//            if(App.getInstance().isDebug()) {
//                ViewServer.get(this).removeWindow(this);
//            }
        }catch(Throwable ex){
            ex.printStackTrace();
        }
        isPaused = true;
    }

    @Override
    protected void onDestroy() {
        mIsActivityDestoryed = true;
        dismissWaitingDialogIfShowing();
        super.onDestroy();
    }

    /**
     * isActivityDestoryed:Activity是否已经Destory了. <br/>
     *
     * @return true, Activity已经销毁了，不要在执行任何Fragment事务、显示Dialog等操作
     */
    public final boolean isActivityDestoryed() {
        return mIsActivityDestoryed;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 一定不要干掉这段代码
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        isStoped = false;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        isStoped = false;
    }

    @Override
    protected void onStop() {
        isStoped = true;
        super.onStop();
    }


    @Override
    public final boolean isPaused() {
        return isPaused;
    }

    @Override
    public final boolean isDestoryed() {
        return isActivityDestoryed();
    }

    @Override
    public final boolean isDetached() {
        return isDestoryed();
    }

    @Override
    public final boolean isStoped() {
        return isStoped;
    }

    @Override
    public final boolean isFragmentHidden() {
        return isDestoryed();
    }

    @Override
    public final boolean isVisibleToUser() {
        return !isPaused();
    }


    @Override
    public void showWaitingDialog() {
        showWaitingDialog(getString(R.string.dialog_wait_msg_default));
    }

    @Override
    public final void showWaitingDialog(String text){
        dismissWaitingDialogIfShowing();
        if (!isFinishing() && !isActivityDestoryed()) {
            mWaitingDialog = DialogUtils.showWaitingDialog(BaseActivity.this, text);
        }
    }

    @Override
    public final void dismissWaitingDialogIfShowing() {
        if (!isActivityDestoryed() && mWaitingDialog != null && mWaitingDialog.isShowing()) {
            mWaitingDialog.dismiss();
            mWaitingDialog = null;
        }
    }

    @Override
    public void finishOwnerActivity() {
        finish();
    }

    @Override
    public BaseActivity getOwnerActivity() {
        return BaseActivity.this;
    }

    public void openKeyboard(@NonNull EditText editText) {
        if (editText != null) {
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, 0);
        }
    }

    public void hideKeyboard(@Nullable EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View focus = null;
        if(editText!=null){
            focus = editText;
        }else if(getCurrentFocus()!=null){
            focus = getCurrentFocus();
        }
        if(focus!=null){
            imm.hideSoftInputFromWindow(focus.getWindowToken(), 0);
        }
    }
}
