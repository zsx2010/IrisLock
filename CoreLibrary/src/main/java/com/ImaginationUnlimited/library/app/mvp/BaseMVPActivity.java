package com.ImaginationUnlimited.library.app.mvp;

import android.os.Bundle;

import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;

/**
 * @author wangheng
 * @describe MVP的Activity的基类.
 * @date: 2015年4月16日 上午11:43:44 <br/>
 */
public abstract class BaseMVPActivity<P extends IPresenter> extends BaseActivity {

    public static final String KEY_DATA = "keyDataOfActivity";

    protected P mPresenter;
    protected ViewFinder mFinder = null;
    protected Bundle mData;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            if (savedInstanceState.getBundle(KEY_DATA) != null) {
                mData = savedInstanceState.getBundle(KEY_DATA);
            }
        }

        if (getIntent() != null && getIntent().getExtras() != null) {
            mData = getIntent().getExtras();
        }

        this.mPresenter = createPresenter();
        getPresenter().init(BaseMVPActivity.this, getUI());
        mFinder = new ViewFinder(BaseMVPActivity.this);
        onCreateExecute(savedInstanceState);
        getPresenter().onUICreate(savedInstanceState);
    }

    /**
     * 所有BaseMVPActivity的子类不能再实现onCreate()方法，而是实现onCreateExecute()方法. <br/>
     *
     * @param savedInstanceState 状态丢失前保存的状态
     */
    protected abstract void onCreateExecute(Bundle savedInstanceState);

    /**
     * 创建一个Presenter，子类来实现，可以通过new的方式直接new出来一个. <br/>
     *
     * @return 创建Presenter，不要手动调用这个方法
     *
     */
    protected abstract P createPresenter();

    /**
     * getUI:得到UI层组件，一般都是Activity或者Fragment本身. <br/>
     *
     * @return
     */
    protected abstract IUI getUI();

    /**
     * getPresenter:子类应该通过这个方法拿到Presenter的实例，而不是通过变量拿到. <br/>
     *
     * @return
     * @author wangheng
     */
    protected final P getPresenter() {
        return mPresenter;
    }

    /**
     * getViewFinder:得到ViewFinder. <br/>
     *
     * @return 得到ViewFinder
     */
    protected final ViewFinder getViewFinder() {
        return mFinder;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().onUIStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onUIResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().onUIPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getPresenter().onUIStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().onUIDestory();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mData != null) {
            outState.putBundle(KEY_DATA, mData);
        }
        if(getPresenter() != null){
            getPresenter().onSaveInstanceState(outState);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.getBundle(KEY_DATA) != null) {
                mData = savedInstanceState.getBundle(KEY_DATA);
            }
        }
        getPresenter().onRestoreInstanceState(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * getData:得到界面必需的Bundle数据. <br/>
     *
     * @return 得到界面必需的Bundle数据
     */
    public Bundle getData() {
        return mData;
    }
}
