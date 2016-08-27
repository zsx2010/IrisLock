package com.wcsn.irislock.admin;

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
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.wcsn.irislock.R;
import com.wcsn.irislock.app.adapter.PagerAdapter;
import com.wcsn.irislock.utils.view.RecycleViewDivider;

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
                chooseUserType(v, getOwnerActivity());
            }
        });

        getPresenter().refreshUserList();

    }

    public void chooseUserType(View view, final Activity context) {
        View contentView = LayoutInflater.from(context).inflate(
                R.layout.popup_user_type, null);
        ViewFinder finder = new ViewFinder(contentView);

        TextView fixedUser = finder.find(R.id.fixedUser);
        TextView tmpUser = finder.find(R.id.tmpUser);
        TextView cancel = finder.find(R.id.cancel);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);


        fixedUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FixedUserActivity.launch(AdminActivity.this);
                popupWindow.dismiss();
            }
        });

        tmpUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TempUserActivity.launch(AdminActivity.this);
                popupWindow.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            }
        });
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
