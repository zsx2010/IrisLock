package com.wcsn.irislock.authorize;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wcsn.irislock.R;
import com.wcsn.irislock.app.adapter.AHolder;
import com.wcsn.irislock.bean.Authorize;
import com.wcsn.irislock.utils.image.ImageLoaderFactory;

/**
 * Created by suiyue on 2016/7/24 0024.
 */
public class AuthorizeHolder extends AHolder{

    private SimpleDraweeView mSimpleDraweeView;
    private TextView mUserName;
    private TextView mIsAuthorize;
    private ImageView mIsOpenDoorView;
    private TextView mOpenDoorTime;
    private CheckBox mAuthorizeDate;
    private TextView mAuthorizeTime;
    private TextView mAuthorizeWeek;
    private TextView mDeleteButton;

    private TextView mDate;


    public AuthorizeHolder(View itemView, final AuthorizeListAdapter.DeleteCallBack deleteCallBack,
                           int type) {
        super(itemView);

        ViewFinder finder = new ViewFinder(itemView);
        if (type == AuthorizeListAdapter.TYPE_CONTENT) {
            mSimpleDraweeView = finder.find(R.id.authorizeView);
            mUserName = finder.find(R.id.userName);
            mIsAuthorize = finder.find(R.id.isAuthorize);
            mIsOpenDoorView = finder.find(R.id.isOpenDoorView);
            mOpenDoorTime = finder.find(R.id.openDoorTime);
            mAuthorizeDate = finder.find(R.id.authorizeDate);
            mAuthorizeTime = finder.find(R.id.authorizeTime);
            mAuthorizeWeek = finder.find(R.id.authorizeWeek);
            mDeleteButton = finder.find(R.id.deleteButton);

            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteCallBack.removeItem(getAdapterPosition());
                }
            });
        } else {
            mDate = finder.find(R.id.date);
        }
    }

    public void bindData(Authorize authorize, int type) {
        if (type == AuthorizeListAdapter.TYPE_CONTENT) {
            ImageLoaderFactory.getLoader(mSimpleDraweeView).showImage(mSimpleDraweeView,
                    authorize.getAuthorizeImage(), null);
            mUserName.setText(authorize.getName());
            if (authorize.getIsAuthorize()) {
                mIsAuthorize.setText("已授权");
                mAuthorizeDate.setChecked(true);
                mAuthorizeDate.setText(authorize.getDate());
            } else {
                mIsAuthorize.setText("未授权");
                mAuthorizeDate.setChecked(false);
                mAuthorizeDate.setText(authorize.getDate());
            }
            if (authorize.getIsOpen()) {
                mIsOpenDoorView.setImageResource(R.drawable.door_open_list);
                mOpenDoorTime.setText(authorize.getOpenTime());
            } else {
                mIsOpenDoorView.setImageResource(R.drawable.door_close_list);
                mOpenDoorTime.setText(authorize.getOpenTime());
            }
            mAuthorizeTime.setText(authorize.getTime());
            mAuthorizeWeek.setText(authorize.getWeek());

        } else {
            mDate.setText(authorize.getDate());
        }

    }
}
