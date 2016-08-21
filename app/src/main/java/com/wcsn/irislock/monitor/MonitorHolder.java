package com.wcsn.irislock.monitor;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.RESTfulFactory;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.sp.SPModel;
import com.ImaginationUnlimited.library.utils.toast.ToastUtils;
import com.ImaginationUnlimited.library.utils.view.ViewFinder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wcsn.irislock.R;
import com.wcsn.irislock.app.adapter.AHolder;
import com.wcsn.irislock.utils.image.ImageLoaderFactory;

/**
 * Created by suiyue on 2016/8/14 0014.
 */
public class MonitorHolder extends AHolder {

    private SimpleDraweeView mMonitorView;
    private CheckBox mPictureDate;
    private TextView mPictureName;
    private TextView mPictureType;


    private TextView mDeleteView;
    private ImageView mDeleteImage;

    public MonitorHolder(View itemView, final MonitorListAdapter.DeleteCallBack callBack) {
        super(itemView);
        ViewFinder finder = new ViewFinder(itemView);
        mMonitorView = finder.find(R.id.monitorView);
        mPictureDate = finder.find(R.id.pictureDate);
        mPictureName = finder.find(R.id.pictureName);
        mPictureType = finder.find(R.id.pictureType);


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

    public void bindData(String s, boolean isRemove) {
        if (isRemove) {
            mDeleteImage.setVisibility(View.VISIBLE);
        } else {
            mDeleteImage.setVisibility(View.GONE);
        }
        if (s.split(".").length>1) {
            String[] ss = s.split(".")[0].split("-");
            String data = "";
            for (int i=1; i<ss.length; i++) {
                data = data + ss[i] + "-";
            }
            data = data.substring(0, data.length()-1);
            mPictureDate.setText(data);
        }


        String url = RESTfulFactory.getUrlBase() + SPModel.getDeviceId() + "/" + s;
        ImageLoaderFactory.getLoader(mMonitorView).showImage(mMonitorView, url, null);

    }
}
