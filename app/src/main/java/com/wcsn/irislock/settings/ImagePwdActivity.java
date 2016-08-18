package com.wcsn.irislock.settings;

import android.content.Intent;
import android.os.Bundle;

import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.wcsn.irislock.R;

/**
 * Created by suiyue on 2016/8/17 0017.
 */
public class ImagePwdActivity extends BaseActivity {

    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,ImagePwdActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pwd);

        ViewFinder finder = new ViewFinder(this);
    }
}
