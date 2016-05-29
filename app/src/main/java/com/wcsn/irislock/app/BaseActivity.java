package com.wcsn.irislock.app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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

import com.androidadvance.topsnackbar.TSnackbar;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wcsn.irislock.app.mvp.IUI;
import com.wcsn.irislock.utils.DeviceUtils;
import com.wcsn.irislock.utils.EvTopSnack;
import com.wcsn.irislock.utils.TopSnackBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by suiyue on 2016/5/28 0028.
 */
public class BaseActivity extends FragmentActivity implements IUI {
    private boolean mIsActivityDestoryed = false;
    private boolean isPaused;
    private boolean isStoped;
    private Dialog mWaitingDialog = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    @SuppressWarnings("unchecked")
    protected <T extends View> T $view(int id) {
        return (T) findViewById(id);
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T $view(@NonNull View parent, @IdRes int id) {
        return (T) parent.findViewById(id);
    }

    protected View $inflate(@LayoutRes int id) {
        return LayoutInflater.from(this).inflate(id, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsActivityDestoryed = false;
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
     *
     * @param color 颜色，必须
     */
    public void setStatusBarColor(@ColorInt int color) {
        ViewGroup view = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View rootView = null;
        if (view != null && (rootView = view.getChildAt(0)) != null) {
//            if(DeviceUtils.hasKITKAT() && !DeviceUtils.hasLOLLIPOP()){
//            }
            rootView.setBackgroundColor(color);
            if (DeviceUtils.hasKITKAT()) {
                rootView.setFitsSystemWindows(true);
            }
        }
    }

    @Subscribe
    public void showTopSnack(final EvTopSnack event) {
        final String str = event.getText();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TSnackbar snackbar = TSnackbar.make(findViewById(android.R.id.content), str, TSnackbar.LENGTH_SHORT);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.parseColor("#ffff0000"));
                TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                if (textView != null) {
                    textView.setTextColor(Color.WHITE);
                    textView.setGravity(Gravity.CENTER);
                }
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
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        isPaused = true;
    }

    @Override
    protected void onDestroy() {
        mIsActivityDestoryed = true;
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        isStoped = false;
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Base Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.wcsn.irislock.app/http/host/path")
        );
        AppIndex.AppIndexApi.start(mClient, viewAction);
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Base Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.wcsn.irislock.app/http/host/path")
        );
        AppIndex.AppIndexApi.end(mClient, viewAction);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.disconnect();
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
        if (editText != null) {
            focus = editText;
        } else if (getCurrentFocus() != null) {
            focus = getCurrentFocus();
        }
        if (focus != null) {
            imm.hideSoftInputFromWindow(focus.getWindowToken(), 0);
        }
    }
}
