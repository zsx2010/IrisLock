package com.wcsn.irislock.admin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.app.mvp.BaseMVPActivity;
import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.wcsn.irislock.R;
import com.wcsn.irislock.app.adapter.PagerAdapter;
import com.wcsn.irislock.utils.view.RecycleViewDivider;

import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by suiyue on 2016/7/7 0007.
 */
public class AdminActivity extends BaseMVPActivity<AdminPresenter>
        implements IAdminUI, AdminListAdapter.DeleteCallBack {

    private ImageView mBackView;
    private RecyclerView mRecyclerView;
    private ImageView mAddUserView;

    private AdminListAdapter mAdapter;

    public static void launch(BaseActivity activity) {
        Intent intent = new Intent(activity, AdminActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_admin);
        ViewFinder finder = new ViewFinder(this);
        mBackView = finder.find(R.id.adminBack);
        mBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRecyclerView = finder.find(R.id.userList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new AdminListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new RecycleViewDivider(getApplicationContext(),
                RecyclerView.HORIZONTAL, 4, Color.GRAY));

        mAddUserView = finder.find(R.id.addUser);
        mAddUserView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionPicker picker = new OptionPicker(AdminActivity.this, new String[]{"永久用户", "临时用户"});
                picker.setOffset(1);
                picker.setSelectedIndex(1);
                picker.setTextSize(24);
                picker.setLineColor(Color.GRAY);
                picker.setTitleText("添加用户");
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(String option) {
                        if (option.equals("永久用户")) {
                            FixedUserActivity.launch(AdminActivity.this);
                        } else {
                            TempUserActivity.launch(AdminActivity.this);
                        }
                    }
                });
                picker.show();
            }
        });

        getPresenter().refreshUserList();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected AdminPresenter createPresenter() {
        return new AdminPresenter();
    }

    @Override
    protected IUI getUI() {
        return this;
    }

    @Override
    public void removeItem(int position) {
        mAdapter.removeItem(position);
    }


    @Override
    public PagerAdapter getAdapter() {
        return mAdapter;
    }



}
