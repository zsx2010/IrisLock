package com.wcsn.irislock.alert;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ImaginationUnlimited.library.utils.toast.ToastUtils;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wcsn.irislock.R;
import com.wcsn.irislock.alert.bean.AlertInfo;
import com.wcsn.irislock.app.adapter.AHolder;
import com.wcsn.irislock.utils.image.ImageLoaderFactory;

/**
 * Created by suiyue on 2016/6/22 0022.
 */
public class AlertHolder extends AHolder{
    private SimpleDraweeView mAlertTypeView;
    private TextView mTimeText;
    private TextView mWeekText;
    private TextView mDetailText;
    private ImageView mDetailView;


    private TextView mDeleteView;

    public AlertHolder(View itemView, final AlertListAdapter.DeleteCallBack callBack) {
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

    public void bindData(AlertInfo alertInfo, boolean isRemove, boolean isDetail) {
        if (isRemove) {

        } else {
            if(isDetail) {
                if (AlertInfo.ALERT_BATTERY == alertInfo.getAlertType()) {
                    ImageLoaderFactory.getLoader(mAlertTypeView)
                            .showImage(mAlertTypeView, "res:///" + R.drawable.message_power, null);
                } else if (AlertInfo.ALERT_OPEN_DOOR == alertInfo.getAlertType()){
                    ImageLoaderFactory.getLoader(mAlertTypeView)
                            .showImage(mAlertTypeView, "res:///" + R.drawable.message_alert, null);
                } else {
                    ImageLoaderFactory.getLoader(mAlertTypeView)
                            .showImage(mAlertTypeView, "res:///" + R.drawable.message_error, null);
                }
                mTimeText.setText(alertInfo.getTime());
                mWeekText.setText(alertInfo.getWeek());
                mDetailText.setText("收起");
            }else {
                if (AlertInfo.ALERT_BATTERY == alertInfo.getAlertType()) {
                    ImageLoaderFactory.getLoader(mAlertTypeView)
                            .showImage(mAlertTypeView, "res:///" + R.drawable.message_power, null);
                } else if (AlertInfo.ALERT_OPEN_DOOR == alertInfo.getAlertType()){
                    ImageLoaderFactory.getLoader(mAlertTypeView)
                            .showImage(mAlertTypeView, "res:///" + R.drawable.message_alert, null);
                } else {
                    ImageLoaderFactory.getLoader(mAlertTypeView)
                            .showImage(mAlertTypeView, "res:///" + R.drawable.message_error, null);
                }
                mTimeText.setText(alertInfo.getTime());
                mWeekText.setText(alertInfo.getWeek());
                mDetailText.setText("查看详情");

            }


        }
    }

//    interface deleteCallBack();
}
