package com.wcsn.irislock.admin;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ImaginationUnlimited.library.utils.toast.ToastUtils;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wcsn.irislock.R;
import com.wcsn.irislock.alert.AlertListAdapter;
import com.wcsn.irislock.app.adapter.AHolder;
import com.wcsn.irislock.bean.User;

/**
 * Created by suiyue on 2016/7/7 0007.
 */
public class AdminHolder extends AHolder{

    private SimpleDraweeView mUserTypeView;
    private TextView mUserNameText;
    private TextView mTimeText;
    private TextView mUserTypeText;
    private ImageView mDetailView;


    public AdminHolder(View itemView, final AdminListAdapter.DeleteCallBack callBack) {
        super(itemView);
        ViewFinder finder = new ViewFinder(itemView);
        mAlertTypeView = finder.find(R.id.alertTypeView);
        mTimeText = finder.find(R.id.timeText);
        mWeekText = finder.find(R.id.weekText);
        mDetailText = finder.find(R.id.detailText);
        mDetailView = finder.find(R.id.detailImage);

        mDeleteView = finder.find(R.id.deleteButton);

        mDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.toastShort("Delete");
                callBack.removeItem(getAdapterPosition());

            }
        });
    }

    public void bindData(User user) {
    }
}
