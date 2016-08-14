package com.wcsn.irislock.admin;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ImaginationUnlimited.library.utils.toast.ToastUtils;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wcsn.irislock.R;
import com.wcsn.irislock.admin.bean.UserInfo;
import com.wcsn.irislock.app.adapter.AHolder;
import com.wcsn.irislock.bean.User;
import com.wcsn.irislock.login.bean.AdminInfo;
import com.wcsn.irislock.utils.image.ImageLoaderFactory;

/**
 * Created by suiyue on 2016/7/7 0007.
 */
public class AdminHolder extends AHolder{

    private SimpleDraweeView mUserTypeView;
    private TextView mUserNameText;
    private TextView mTimeText;
    private TextView mUserTypeText;
    private ImageView mDetailView;

    private TextView mDeleteView;

    private LinearLayout mDetailLayout;
    private TextView mSexText;
    private TextView mPhoneText;
    private TextView mAddressText;

    private Boolean isDetail = false;

    public AdminHolder(View itemView, final AdminListAdapter.DeleteCallBack callBack) {
        super(itemView);
        ViewFinder finder = new ViewFinder(itemView);
        mTimeText = finder.find(R.id.timeText);
        mUserNameText = finder.find(R.id.userNameText);
        mUserTypeView = finder.find(R.id.userTypeView);
        mUserTypeText = finder.find(R.id.userTypeText);
        mDetailView = finder.find(R.id.detailImage);

        mDeleteView = finder.find(R.id.deleteButton);

        mDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.toastShort("Delete");
                callBack.removeItem(getAdapterPosition());

            }
        });

        mDetailLayout = finder.find(R.id.detailLayout);
        mSexText = finder.find(R.id.sexText);
        mPhoneText = finder.find(R.id.phoneText);
        mAddressText = finder.find(R.id.addressText);
    }

    public void bindData(User user) {
        String s = "";
        if (user.getRegister_time().split(" ").length > 0) {
            s = user.getRegister_time().split(" ")[0];
        }

        System.out.println(user.getRegister_time());
//        Logger.e(getClass().getSimpleName(), user.getRegister_time());

        mTimeText.setText(s);
        mUserNameText.setText(user.getUser_name());
        System.out.println(user.getUser_name());
//        Logger.e(getClass().getSimpleName(), user.getUser_name());
        if (user.getUser_flag().equals("0")) {
            ImageLoaderFactory.getLoader(mUserTypeView)
                    .showImage(mUserTypeView, "res:///" + R.drawable.user_fix, null);
            mUserTypeText.setText("管理员");
        } else if (user.getUser_flag().equals("1")) {
            ImageLoaderFactory.getLoader(mUserTypeView)
                    .showImage(mUserTypeView, "res:///" + R.drawable.user_fix, null);
            mUserTypeText.setText("永久用户");
        }else if (user.getUser_flag().equals("2")) {
            ImageLoaderFactory.getLoader(mUserTypeView)
                    .showImage(mUserTypeView, "res:///" + R.drawable.user_tmp, null);
            mUserTypeText.setText("临时用户");
        }

        String userInfo = user.getUser_info();
        Gson gson = new Gson();
        AdminInfo adminInfo = gson.fromJson(userInfo, AdminInfo.class);
        UserInfo info = adminInfo.getUserInfo();

        mSexText.setText(info.getSex());
        mPhoneText.setText(info.getPhone());
        mAddressText.setText(info.getAddress() + " " + info.getStreet());
        mUserNameText.setText(adminInfo.getName());

        mUserTypeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isDetail) {
                    Animation animation = new RotateAnimation(0.0f, +90.0f,
                            Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setFillAfter(true);
                    mDetailView.setAnimation(animation);
                    mDetailLayout.setVisibility(View.VISIBLE);
                    isDetail = true;
                } else {
                    Animation animation = new RotateAnimation(0.0f, -90.0f,
                            Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
                    mDetailView.setAnimation(animation);
                    mDetailLayout.setVisibility(View.GONE);
                    isDetail = false;
                }
            }
        });


//        mDetailView.setImageResource(R.drawable.ic_keyboard_arrow_right_black_36dp);



    }
}
