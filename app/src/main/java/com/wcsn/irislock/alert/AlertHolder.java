package com.wcsn.irislock.alert;

import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ImaginationUnlimited.library.utils.log.Logger;
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

    private TextView mAlertInfo;

    private LinearLayout mOpenDoorLayout;
    private SimpleDraweeView mAlertImage;
    private TextView mTakeTime;

    private LinearLayout mPowerLayout;
    private TextView mPowerStateText;
    private SimpleDraweeView mPowerImage;
    private TextView mPowerText;

    private LinearLayout mServerLayout;


    private TextView mDeleteView;

    private ImageView mDeleteImage;

    public AlertHolder(View itemView, final AlertListAdapter.DeleteCallBack callBack) {
        super(itemView);
        ViewFinder finder = new ViewFinder(itemView);
        mAlertTypeView = finder.find(R.id.alertTypeView);
        mTimeText = finder.find(R.id.timeText);
        mWeekText = finder.find(R.id.weekText);
        mDetailText = finder.find(R.id.detailText);
        mDetailView = finder.find(R.id.detailImage);
        mDetailLayout = finder.find(R.id.detailLayout);

        mAlertInfo = finder.find(R.id.alertInfo);


        mOpenDoorLayout = finder.find(R.id.openDoorLayout);
        mAlertImage = finder.find(R.id.alertImage);
        mTakeTime = finder.find(R.id.takeTime);

        mPowerLayout = finder.find(R.id.powerLayout);
        mPowerImage = finder.find(R.id.powerImage);
        mPowerStateText = finder.find(R.id.powerStateText);
        mPowerText = finder.find(R.id.powerText);

        mServerLayout = finder.find(R.id.serverLayout);

        mDeleteView = finder.find(R.id.deleteButton);



        mDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.toastShort("Delete");
                callBack.removeItem(getAdapterPosition());

            }
        });

        mDeleteImage = finder.find(R.id.deleteView);
    }



    public void bindData(final Alert alert, boolean isRemove) {


        if (isRemove) {
            mDeleteImage.setVisibility(View.VISIBLE);
        } else {
            mDeleteImage.setVisibility(View.GONE);
        }

        mTimeText.setText(alert.getTime());

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
        mWeekText.setText(alert.getWeek());
        mDetailText.setText("查看详情");
        mDetailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.e("click");
                if (mDetailText.getText().toString().equals("查看详情")) {
                    Animation animation = new RotateAnimation(0.0f, +90.0f,
                            Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setFillAfter(true);
                    mDetailView.setAnimation(animation);
                    mDetailText.setText("收起");

                    mAlertInfo.setVisibility(View.VISIBLE);
                    if (Alert.ALERT_BATTERY == alert.getType()) {
                        mAlertInfo.setText("门锁现状：可正常使用");
                        mPowerLayout.setVisibility(View.VISIBLE);
                        int power = Integer.parseInt(alert.getAlertInfo());
                        if (power <= 20) {
                            ImageLoaderFactory.getLoader(mPowerImage)
                                    .showImage(mPowerImage, "res:///" + R.drawable.power_20, null);
                            mPowerStateText.setText("电源电量：需要充电");
                            mPowerText.setText("20%");
                            mPowerText.setTextColor(Color.RED);
                        } else if (power <= 50) {
                            ImageLoaderFactory.getLoader(mPowerImage)
                                    .showImage(mPowerImage, "res:///" + R.drawable.power_50, null);
                            mPowerStateText.setText("电源电量：电量适中");
                            mPowerText.setText("50%");
                            mPowerText.setTextColor(Color.YELLOW);
                        } else if (power <= 80) {
                            ImageLoaderFactory.getLoader(mPowerImage)
                                    .showImage(mPowerImage, "res:///" + R.drawable.power_80, null);
                            mPowerStateText.setText("电源电量：电量充足");
                            mPowerText.setText("80%");
                            mPowerText.setTextColor(Color.GREEN);
                        } else {
                            ImageLoaderFactory.getLoader(mPowerImage)
                                    .showImage(mPowerImage, "res:///" + R.drawable.power_100, null);
                            mPowerStateText.setText("电源电量：电量充足");
                            mPowerText.setText("100%");
                            mPowerText.setTextColor(Color.GREEN);
                        }
                    } else if (Alert.ALERT_OPEN_DOOR == alert.getType()){
                        mAlertInfo.setText("报警原因：非虹膜开门");
                        mOpenDoorLayout.setVisibility(View.VISIBLE);
                        String url = alert.getAlertImage();
                        ImageLoaderFactory.getLoader(mAlertTypeView)
                                .showImage(mAlertImage, url, null);
                        mTakeTime.setText("拍摄时间：" + alert.getTime());
                    } else {
                        mAlertInfo.setText("故障代码：" + alert.getAlertInfo());
                        mServerLayout.setVisibility(View.VISIBLE);
                    }
                } else {
                    Animation animation = new RotateAnimation(0.0f, -90.0f,
                            Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
                    mDetailView.setAnimation(animation);
                    mDetailText.setText("查看详情");
                    mAlertInfo.setVisibility(View.GONE);
                    mOpenDoorLayout.setVisibility(View.GONE);
                    mPowerLayout.setVisibility(View.GONE);
                    mServerLayout.setVisibility(View.GONE);
                }
            }
        });
    }

}
