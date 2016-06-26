package com.wcsn.irislock.alert;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.app.mvp.BaseMVPActivity;
import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.wcsn.irislock.R;
import com.wcsn.irislock.app.adapter.PagerAdapter;
import com.wcsn.irislock.utils.view.RecycleViewDivider;

/**
 * Created by suiyue on 2016/6/21 0021.
 */
public class AlertActivity extends BaseMVPActivity<AlertPresenter> implements AlertListAdapter.DeleteCallBack,
        IAlertUI{

    private ImageView mBackView;
    private TextView mEditView;
    private RecyclerView mRecyclerView;


    private AlertListAdapter adapter;

    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,AlertActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_alert);
        ViewFinder finder = new ViewFinder(this);
        mBackView = finder.find(R.id.alertBack);
        mBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mEditView = finder.find(R.id.alertEdit);
        mRecyclerView = finder.find(R.id.alertList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AlertListAdapter(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getApplicationContext(),
                RecyclerView.HORIZONTAL, 4, Color.GRAY));

        getPresenter().refreshAlertList();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected AlertPresenter createPresenter() {
        return new AlertPresenter();
    }

    @Override
    protected IUI getUI() {
        return this;
    }

    @Override
    public void removeItem(int position) {
        adapter.removeItem(position);
    }


    @Override
    public PagerAdapter getAdapter() {
        return adapter;
    }
}
