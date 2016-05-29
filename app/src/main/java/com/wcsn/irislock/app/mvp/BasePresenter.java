package com.wcsn.irislock.app.mvp;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by suiyue on 2016/5/29 0029.
 *
 *  Presenter的基类.
 */
public abstract class BasePresenter<U extends IUI> implements IPresenter {
    private U mUI;
    private Context mContext;

    public BasePresenter() {

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IUI> void init(Context context, T ui) {
        this.mContext = context;
        this.mUI = (U) ui;
    }

    protected final U getUI() {
        return mUI;
    }

    protected final Context getContext() {
        return mContext;
    }

    @Override
    public void onUICreate(Bundle savedInstanceState) {

    }

    @Override
    public void onUIStart() {

    }

    @Override
    public void onUIResume() {

    }

    @Override
    public void onUIPause() {

    }

    @Override
    public void onUIStop() {

    }

    @Override
    public void onUIDestory() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }
}
