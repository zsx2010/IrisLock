package com.wcsn.irislock.monitor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ImaginationUnlimited.library.app.BaseActivity;
import com.ImaginationUnlimited.library.app.mvp.BaseMVPActivity;
import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.ImaginationUnlimited.library.utils.log.Logger;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.wcsn.irislock.R;
import com.wcsn.irislock.app.adapter.PagerAdapter;
import com.wcsn.irislock.utils.view.RecycleViewDivider;

import java.util.ArrayList;

/**
 * Created by suiyue on 2016/8/14 0014.
 */
public class MonitorActivity extends BaseMVPActivity<MonitorPresenter>
        implements  IMonitorUI, MonitorListAdapter.DeleteCallBack, Thread.UncaughtExceptionHandler{

    private ImageView mBackView;
    private TextView mEditView;
    private TextView mDeleteAllView;
    private RecyclerView mRecyclerView;

    private MonitorListAdapter adapter;


    public static void launch(BaseActivity activity){
        Intent intent = new Intent(activity,MonitorActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_monitor);
        ViewFinder finder = new ViewFinder(this);
        mBackView = finder.find(R.id.back);
        mBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mEditView = finder.find(R.id.edit);
        mEditView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditView.getText().toString().equals("编辑")) {
                    mBackView.setVisibility(View.GONE);
                    mDeleteAllView.setVisibility(View.VISIBLE);
                    mEditView.setText("完成");
                    adapter.isRemove = true;
                    adapter.notifyDataSetChanged();
                } else {
                    mBackView.setVisibility(View.VISIBLE);
                    mDeleteAllView.setVisibility(View.GONE);
                    adapter.isRemove = true;
                    adapter.notifyDataSetChanged();
                    mEditView.setText("编辑");
                }

            }
        });
        mDeleteAllView = finder.find(R.id.deleteAll);
        mDeleteAllView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteHint(v,getOwnerActivity());
            }
        });


        mRecyclerView = finder.find(R.id.pictureList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MonitorListAdapter(this);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getApplicationContext(),
                RecyclerView.HORIZONTAL, 4, Color.GRAY));

        getPresenter().refreshAlertList();

        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    protected MonitorPresenter createPresenter() {
        return new MonitorPresenter();
    }

    @Override
    protected IUI getUI() {
        return this;
    }



    public void deleteHint(View view, final Activity context) {
        View contentView = LayoutInflater.from(context).inflate(
                R.layout.popup_delete_hint, null);
        ViewFinder finder = new ViewFinder(contentView);

        TextView ok = finder.find(R.id.ok);
        TextView cancel = finder.find(R.id.cancel);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().deleteAll();
                adapter.updateList(new ArrayList<String>());
                mBackView.setVisibility(View.VISIBLE);
                mDeleteAllView.setVisibility(View.GONE);
                mEditView.setText("编辑");
                adapter.isRemove = false;
                adapter.notifyDataSetChanged();
                popupWindow.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBackView.setVisibility(View.VISIBLE);
                mDeleteAllView.setVisibility(View.GONE);
                adapter.isRemove = false;
                adapter.notifyDataSetChanged();
                popupWindow.dismiss();

            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                adapter.isRemove = false;
                adapter.notifyDataSetChanged();
                mBackView.setVisibility(View.VISIBLE);
                mDeleteAllView.setVisibility(View.GONE);
                mEditView.setText("编辑");
            }
        });
    }

    @Override
    public PagerAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void removeItem(int position) {
        getPresenter().deleteItem(position);
        adapter.removeItem(position);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Logger.e(ex.toString());
    }
}
