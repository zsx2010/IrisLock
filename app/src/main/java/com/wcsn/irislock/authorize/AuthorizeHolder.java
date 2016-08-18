package com.wcsn.irislock.authorize;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.RESTfulFactory;
import com.ImaginationUnlimited.library.utils.app.StringUtils;
import com.ImaginationUnlimited.library.utils.log.Logger;
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

    private ImageView mDeleteImage;


    public AuthorizeHolder(View itemView, final AuthorizeListAdapter.DeleteCallBack deleteCallBack,
                           int type) {
        super(itemView);

        ViewFinder finder = new ViewFinder(itemView);

        if (type == AuthorizeListAdapter.TYPE_CONTENT) {

            Logger.e("type = " + type);

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
        mDeleteImage = finder.find(R.id.deleteView);
    }

    public void bindData(Authorize authorize, int type, boolean isRemove) {

        if (isRemove) {
            mDeleteImage.setVisibility(View.VISIBLE);
        } else {
            mDeleteImage.setVisibility(View.GONE);
        }

        if (type == AuthorizeListAdapter.TYPE_CONTENT) {

            String url = RESTfulFactory.getUrlBase() + authorize.getAuthorizeImage();
            url = url.replace(" ", "%20");
            ImageLoaderFactory.getLoader(mSimpleDraweeView).showImage(mSimpleDraweeView,
                    url, null);
            mUserName.setText(authorize.getName());
            if (StringUtils.isNullOrEmpty(authorize.getName())) {
                mUserName.setText("用户名");
            }

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
            switch (authorize.getWeek()) {
                case "1":
                    mAuthorizeWeek.setText("星期一");
                    break;
                case "2":
                    mAuthorizeWeek.setText("星期二");
                    break;
                case "3":
                    mAuthorizeWeek.setText("星期三");
                    break;
                case "4":
                    mAuthorizeWeek.setText("星期四");
                    break;
                case "5":
                    mAuthorizeWeek.setText("星期五");
                    break;
                case "6":
                    mAuthorizeWeek.setText("星期六");
                    break;
                case "7":
                    mAuthorizeWeek.setText("星期日");
                    break;

            }


        } else {
            mDate.setText(authorize.getDate());
        }

    }
}
