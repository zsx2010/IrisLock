package com.wcsn.irislock.monitor;

import android.content.Context;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.RESTfulFactory;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseStringList;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.sp.SPModel;
import com.ImaginationUnlimited.library.app.mvp.BasePresenter;
import com.wcsn.irislock.utils.network.ResponseSubscriber;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by suiyue on 2016/8/14 0014.
 */
public class MonitorPresenter extends BasePresenter<IMonitorUI>{
    public void refreshAlertList() {
        RESTfulFactory.getUserService().getPicture(SPModel.getDeviceId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseSubscriber<ResponseStringList>() {
                    @Override
                    public Context getEvContext() {
                        return getContext();
                    }

                    @Override
                    public void onSuccess(ResponseStringList responseStringList) {
                        List<String> monitor = responseStringList.getData().getList();
                        getUI().getAdapter().updateList(monitor);
                    }

                    @Override
                    public void onFail(ResponseStringList responseStringList) {

                    }

                    @Override
                    public void onCompleted() {

                    }
                });

    }

    public void deleteAll() {
    }

    public void deleteItem(int position) {

    }
}
