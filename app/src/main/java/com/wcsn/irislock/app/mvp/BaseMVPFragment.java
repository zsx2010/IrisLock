package com.wcsn.irislock.app.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wcsn.irislock.app.BaseFragment;

/**
 * Created by suiyue on 2016/5/29 0029.
 */
public abstract class BaseMVPFragment<P extends IPresenter> extends BaseFragment {

    private static final String KEY_DATA = "keyData";

    protected P mPresenter;


    protected Bundle mData;

    protected Bundle mExtras;

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Bundle bundle = savedInstanceState.getBundle(KEY_DATA);
            if (bundle != null) {
                mData = bundle;
            }
        }
        mPresenter = createPresenter();
        getPresenter().init(getActivity(), getUI());
        View rootView = onCreateViewExecute(inflater, container, savedInstanceState);
        getPresenter().onUICreate(savedInstanceState);

        onCreateViewExecuteEveryTime(savedInstanceState);

        return rootView;
    }

    /**
     * 每次执行onCreateView的时候都调用这个方法
     * <p/>
     * Notes:这个方法执行一些数据恢复、注册监听等类似的事情，和onDestoryView方法成对调用
     *
     * @param savedInstanceState 保存的状态
     */
    protected void onCreateViewExecuteEveryTime(Bundle savedInstanceState) {

    }

    /**
     * onCreateViewExecute:MVPd的Fragment不应该再实现onCreateView()方法，
     * 而是应该实现onCreateViewExecute()方法. <br/>
     *
     * @param inflater           布局填充器
     * @param container          rootView
     * @param savedInstanceState 保存的状态
     * @return 填充出来的布局
     */
    protected abstract View onCreateViewExecute(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * getUI:得到UI.一般都是Fragment或者Activity本身 <br/>
     *
     * @return UI组件本身
     */
    protected abstract IUI getUI();

    /**
     * createPresenter:创建一个Presenter，子类来实现，可以通过new的方式直接new出来一个. <br/>
     *
     * @return Presenter
     */
    protected abstract P createPresenter();

    /**
     * getPresenter:子类应该通过这个方法拿到Presenter的实例，而不是通过变量拿到. <br/>
     *
     * @return 创建Presenter
     */
    protected final P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onUIStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().onUIStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onUIResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().onUIPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPresenter().onUIDestory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mData != null) {
            outState.putBundle(KEY_DATA, mData);
        }
        if (getPresenter() != null) {
            getPresenter().onSaveInstanceState(outState);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * getExtras:得到Extras. <br/>
     *
     * @return 得到额外的，在内存被回收的时候不会被保存的数据
     */
    public Bundle getExtras() {
        return mExtras;
    }

    /**
     * setExtras:设置Extras. <br/>
     *
     * @param extras 额外的，在内存被回收的时候不会被保存的数据
     */
    public void setExtras(Bundle extras) {
        this.mExtras = extras;
    }

}
