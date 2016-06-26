package com.wcsn.iriscorelibrary.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.wcsn.iriscorelibrary.app.mvp.IUI;

/**
 * Created by suiyue on 2016/6/7 0007.
 */
public class BaseFragment extends Fragment implements IUI{

    protected BaseFragment startActivityForResultChild = null;

    private boolean isPaused;
    private boolean isStoped;
    private boolean isDestoryed;
    private boolean isDetached;
    private boolean isHidden;
    private boolean isVisibleToUser = false;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        isDetached = false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isDestoryed = false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDestoryed = false;
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
    public void onStop() {
        super.onStop();
        isStoped = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isPaused = true;
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
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setStartActivityForResultChild(BaseFragment child) {
        this.startActivityForResultChild = child;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        isHidden = hidden;
        super.onHiddenChanged(hidden);
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

    @Override
    public boolean isPaused() {
        return isPaused;
    }

    @Override
    public boolean isDestoryed() {
        return isDestoryed;
    }

    @Override
    public boolean isStoped() {
        return isStoped;
    }

    @Override
    public boolean isFragmentHidden() {
        return isHidden;
    }

    @Override
    public boolean isVisibleToUser() {
        return isVisibleToUser;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isVisibleToUser = isVisibleToUser;
        super.setUserVisibleHint(isVisibleToUser);
    }
}
