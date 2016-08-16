package com.wcsn.irislock.settings;

import android.content.Intent;
import android.os.Bundle;

import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.app.mvp.BaseMVPActivity;
import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.wcsn.irislock.R;

/**
 * Created by suiyue on 2016/8/16 0016.
 */
public class SettingsActivity extends BaseMVPActivity<SettingsPresenter> implements ISettingsUI {

    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,SettingsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_settings);

    }

    @Override
    protected SettingsPresenter createPresenter() {
        return new SettingsPresenter();
    }

    @Override
    protected IUI getUI() {
        return this;
    }
}
