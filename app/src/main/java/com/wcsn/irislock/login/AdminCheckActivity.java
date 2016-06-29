package com.wcsn.irislock.login;

import android.content.Intent;
import android.os.Bundle;

import com.ImaginationUnlimited.library.app.BaseActivity;
import com.wcsn.irislock.R;

/**
 * Created by suiyue on 2016/6/30 0030.
 */
public class AdminCheckActivity extends BaseActivity {


    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,AdminCheckActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_check);
    }
}
