package com.wcsn.irislock.admin;

import android.content.Intent;

import com.ImaginationUnlimited.library.app.BaseActivity;

/**
 * Created by suiyue on 2016/7/9 0009.
 */
public class ValidityTimeActivity {

    public static void launch(BaseActivity activity) {
        Intent intent = new Intent(activity, ValidityTimeActivity.class);
        activity.startActivity(intent);
    }
}
