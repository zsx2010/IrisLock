package com.wcsn.irislock.authorize;

import android.content.Context;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.RESTfulFactory;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseNoData;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.sp.SPModel;
import com.ImaginationUnlimited.library.app.mvp.BasePresenter;
import com.ImaginationUnlimited.library.utils.log.Logger;
import com.ImaginationUnlimited.library.utils.toast.ToastUtils;
import com.wcsn.irislock.utils.network.ResponseSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
 * Created by suiyue on 2016/7/14 0014.
 */
public class AuthorizePresenter extends BasePresenter<IAuthorizeUI>{
    public void Authorize(boolean b, String imageName) {
        String isAuthorize;
        if (b) {
            isAuthorize = "YES";
        } else {
            isAuthorize = "NO";
        }
        RESTfulFactory.getUserService().adminAuthorize(SPModel.getDeviceId(), imageName, isAuthorize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseSubscriber<ResponseNoData>() {
                    @Override
                    public Context getEvContext() {
                        return getContext();
                    }

                    @Override
                    public void onSuccess(ResponseNoData responseNoData) {
                        if (responseNoData.getCode() == 1) {
                            Logger.e("SUCCESS");
                            getUI().getDialog().dismiss();
                            getUI().waitOpenDoor();
                        } else {
                            ToastUtils.toastShort("授权失败，请重新尝试");
                        }
                    }

                    @Override
                    public void onFail(ResponseNoData responseNoData) {

                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }
}
