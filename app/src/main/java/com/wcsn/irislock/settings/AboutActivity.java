package com.wcsn.irislock.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.wcsn.irislock.R;

/**
 * Created by suiyue on 2016/8/22 0022.
 */
public class AboutActivity extends BaseActivity {

    private ImageView mBack;

    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,AboutActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ViewFinder finder = new ViewFinder(this);

        mBack = finder.find(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
