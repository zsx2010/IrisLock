package com.wcsn.irislock.information;

import android.content.Intent;
import android.os.Bundle;

import com.ImaginationUnlimited.library.app.BaseActivity;

/**
 * Created by suiyue on 2016/6/30 0030.
 */
public class InformationActivity extends BaseActivity {


    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,InformationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
