package com.wcsn.irislock.authorize;

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
import com.wcsn.irislock.bean.Authorize;
import com.wcsn.irislock.utils.view.RecycleViewDivider;

import java.util.List;

/**
 * Created by suiyue on 2016/6/21 0021.
 */
public class AuthorizeListActivity extends BaseMVPActivity<AuthorizeListPresenter> implements AuthorizeListAdapter.DeleteCallBack,
        IAuthorizeListUI{

    private ImageView mBackView;
    private TextView mEditView;
    private RecyclerView mRecyclerView;


    private AuthorizeListAdapter adapter;

    private List<Authorize> mAuthorizes;

    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,AuthorizeListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_authorize_list);
        ViewFinder finder = new ViewFinder(this);
        mBackView = finder.find(R.id.back);
        mBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mEditView = finder.find(R.id.edit);
        mRecyclerView = finder.find(R.id.authorizeList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getPresenter().getAuthorizeList();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected AuthorizeListPresenter createPresenter() {
        return new AuthorizeListPresenter();
    }

    @Override
    protected IUI getUI() {
        return this;
    }

    @Override
    public void removeItem(int position) {

        getPresenter().deleteItem(position);

        adapter.removeItem(position);
    }


    @Override
    public PagerAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void refreshList(List<Authorize> authorizes) {
        mAuthorizes = authorizes;
        adapter = new AuthorizeListAdapter(this, mAuthorizes, false);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getApplicationContext(),
                RecyclerView.HORIZONTAL, 4, Color.GRAY));

    }
}
