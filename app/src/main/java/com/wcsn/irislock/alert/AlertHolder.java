package com.wcsn.irislock.alert;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ImaginationUnlimited.library.utils.toast.ToastUtils;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wcsn.irislock.R;
import com.wcsn.irislock.app.adapter.AHolder;
import com.wcsn.irislock.bean.Alert;
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
    private LinearLayout mDetailLayout;


    private TextView mDeleteView;

    public AlertHolder(View itemView, final AlertListAdapter.DeleteCallBack callBack) {
        super(itemView);
        ViewFinder finder = new ViewFinder(itemView);
        mAlertTypeView = finder.find(R.id.alertTypeView);
        mTimeText = finder.find(R.id.timeText);
        mWeekText = finder.find(R.id.weekText);
        mDetailText = finder.find(R.id.detailText);
        mDetailView = finder.find(R.id.detailImage);
        mDetailLayout = finder.find(R.id.detailLayout);

        mDeleteView = finder.find(R.id.deleteButton);



        mDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.toastShort("Delete");
                callBack.removeItem(getAdapterPosition());

            }
        });
    }

    public void bindData(Alert alert, boolean isRemove, boolean isDetail) {
        if (isRemove) {

        } else {

            if (Alert.ALERT_BATTERY == alert.getType()) {
                ImageLoaderFactory.getLoader(mAlertTypeView)
                        .showImage(mAlertTypeView, "res:///" + R.drawable.message_power, null);
            } else if (Alert.ALERT_OPEN_DOOR == alert.getType()){
                ImageLoaderFactory.getLoader(mAlertTypeView)
                        .showImage(mAlertTypeView, "res:///" + R.drawable.message_alert, null);
            } else {
                ImageLoaderFactory.getLoader(mAlertTypeView)
                        .showImage(mAlertTypeView, "res:///" + R.drawable.message_error, null);
            }
            mTimeText.setText(alert.getTime());
            mWeekText.setText(alert.getWeek());
            mDetailText.setText("查看详情");

            mDetailLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDetailText.getText().toString().equals("查看详情")) {
                        Animation animation = new RotateAnimation(0.0f, +90.0f,
                                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
                        animation.setFillAfter(true);
                        mDetailView.setAnimation(animation);
                        mDetailText.setText("收起");
                    } else {
                        Animation animation = new RotateAnimation(0.0f, -90.0f,
                                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
                        mDetailView.setAnimation(animation);
                        mDetailText.setText("查看详情");
                    }
                }
            });

//            if(isDetail) {
//                if (Alert.ALERT_BATTERY == alert.getType()) {
//                    ImageLoaderFactory.getLoader(mAlertTypeView)
//                            .showImage(mAlertTypeView, "res:///" + R.drawable.message_power, null);
//                } else if (Alert.ALERT_OPEN_DOOR == alert.getType()){
//                    ImageLoaderFactory.getLoader(mAlertTypeView)
//                            .showImage(mAlertTypeView, "res:///" + R.drawable.message_alert, null);
//                } else {
//                    ImageLoaderFactory.getLoader(mAlertTypeView)
//                            .showImage(mAlertTypeView, "res:///" + R.drawable.message_error, null);
//                }
//                mTimeText.setText(alert.getTime());
//                mWeekText.setText(alert.getWeek());
//                mDetailText.setText("收起");
//            }else {
//                if (Alert.ALERT_BATTERY == alert.getType()) {
//                    ImageLoaderFactory.getLoader(mAlertTypeView)
//                            .showImage(mAlertTypeView, "res:///" + R.drawable.message_power, null);
//                } else if (Alert.ALERT_OPEN_DOOR == alert.getType()){
//                    ImageLoaderFactory.getLoader(mAlertTypeView)
//                            .showImage(mAlertTypeView, "res:///" + R.drawable.message_alert, null);
//                } else {
//                    ImageLoaderFactory.getLoader(mAlertTypeView)
//                            .showImage(mAlertTypeView, "res:///" + R.drawable.message_error, null);
//                }
//                mTimeText.setText(alert.getTime());
//                mWeekText.setText(alert.getWeek());
//                mDetailText.setText("查看详情");
//
//            }


        }
    }

//    interface deleteCallBack();
}
