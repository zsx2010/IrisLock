package com.wcsn.irislock.authorize;

import android.os.Bundle;

import com.ImaginationUnlimited.library.app.mvp.BaseMVPActivity;
import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.wcsn.irislock.R;

/**
 * Created by suiyue on 2016/7/14 0014.
 */
public class AuthorizeActivity extends BaseMVPActivity<AuthorizePresenter> implements IAuthorizeUI {
    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_authorized);

    }

    @Override
    protected AuthorizePresenter createPresenter() {
        return new AuthorizePresenter();
    }

    @Override
    protected IUI getUI() {
        return this;
    }
}
