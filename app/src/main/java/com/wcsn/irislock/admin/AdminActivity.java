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
import com.wcsn.irislock.utils.view.RecycleViewDivider;

/**
 * Created by suiyue on 2016/7/7 0007.
 */
public class AdminActivity extends BaseMVPActivity<AdminPresenter>
        implements IAdminUI {

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

        getPresenter().refreshUserList();

    }

    @Override
    protected AdminPresenter createPresenter() {
        return null;
    }

    @Override
    protected IUI getUI() {
        return null;
    }
}
