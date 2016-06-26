package com.wcsn.iriscorelibrary.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.wcsn.iriscorelibrary.app.mvp.IUI;

/**
 * Created by suiyue on 2016/6/7 0007.
 * 所有Activity的基类
 */
public class BaseActivity extends FragmentActivity implements IUI {

    private boolean mIsActivityDestoryed = false;
    private boolean isPaused;
    private boolean isStoped;

    @SuppressWarnings("unchecked")
    protected <T extends View> T $view(int id){
        return (T) findViewById(id);
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T $view(@NonNull View parent, @IdRes int id){
        return (T) parent.findViewById(id);
    }

    protected View $inflate(@LayoutRes int id){
        return LayoutInflater.from(this).inflate(id,null);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsActivityDestoryed = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPaused = false;
    }

    @Override
    protected void onPause() {
        try {
            super.onPause();
        }catch(Throwable ex){
            ex.printStackTrace();
        }
        isPaused = true;
    }

    @Override
    protected void onDestroy() {
        mIsActivityDestoryed = true;
        super.onDestroy();
    }

    public final boolean isActivityDestoryed() {
        return mIsActivityDestoryed;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
    public void finishOwnerActivity() {
        finish();
    }

    @Override
    public BaseActivity getOwnerActivity() {
        return  BaseActivity.this;
    }

    @Override
    public boolean isPaused() {
        return isPaused;
    }

    @Override
    public boolean isDestoryed() {
        return isActivityDestoryed();
    }

    @Override
    public boolean isDetached() {
        return isDestoryed();
    }

    @Override
    public boolean isStoped() {
        return isStoped;
    }

    @Override
    public boolean isFragmentHidden() {
        return isDestoryed();
    }

    @Override
    public boolean isVisibleToUser() {
        return isPaused();
    }
}
