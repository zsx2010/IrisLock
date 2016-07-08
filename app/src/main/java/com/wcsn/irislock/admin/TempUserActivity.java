package com.wcsn.irislock.admin;

import android.content.Intent;
import android.os.Bundle;

import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.app.mvp.BaseMVPActivity;
import com.ImaginationUnlimited.library.app.mvp.IUI;

/**
 * Created by suiyue on 2016/7/8 0008.
 */
public class TempUserActivity extends BaseMVPActivity<TempUserPresenter> implements ITempUserUI{
    public static void launch(BaseActivity activity) {
        Intent intent = new Intent(activity, TempUserActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {

    }

    @Override
    protected TempUserPresenter createPresenter() {
        return null;
    }

    @Override
    protected IUI getUI() {
        return null;
    }
}
