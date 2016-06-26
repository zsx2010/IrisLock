package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.mvp.model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.data.BaseListData;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.BaseListResponse;

import java.io.Serializable;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by someHui on 4/12/16.
 */
public abstract class PageLoader<T extends BaseListResponse<D>, D extends BaseListData<K>,K extends Serializable> {

    public interface OnUiChangedListener{
        void onStart();
        void onFailed(Throwable e);
        void onComplete();
        void onRefresh();
        void onLoadPage(int start, int count);
    }

    public PageLoader(int pageSize) {
        this.mPageModel = new ListModel<>();
        this.page = 1;
        this.pageSize = pageSize;
        this.mUiListener = new OnUiChangedListener() {
            @Override public void onStart() {}
            @Override  public void onFailed(Throwable e) {}
            @Override public void onComplete() {}
            @Override public void onRefresh() {}
            @Override public void onLoadPage(int start, int count) {}
        };
    }

    private ListModel<K> mPageModel;
    private int page = 1;
    private int pageSize = 20;
    private boolean lastPage = false;
    private OnUiChangedListener mUiListener ;
    private long lastTimeStamp ;
    private Bundle lastBundle;

    public ListModel<K> getPageModel() {
        return mPageModel;
    }

    public OnUiChangedListener getUiListener() {
        return mUiListener;
    }

    public void setUiListener(@NonNull OnUiChangedListener uiListener) {
        mUiListener = uiListener;
    }

    public abstract Observable<T> getService(Bundle bundle,int page,int pagesize);

    public abstract boolean isVaild();


    private boolean isOutOfDate(long timestamp){
        return lastTimeStamp != timestamp;
    }


    public void refresh(Bundle bundle){
        lastBundle = bundle;
        page = 1;
        lastPage = false;
    }

    public void loadNextPage(){
        if(lastPage){
            return;
        }
        request(lastBundle);
    }


    private int requestCount = 0;
    private void request(Bundle bundle){
        if(!isVaild()){
            return;
        }
        final long timeStamp = System.currentTimeMillis();
        if(requestCount>0&&timeStamp-lastTimeStamp<2000){
            return;
        }
        lastTimeStamp = timeStamp;
        mUiListener.onStart();
        final int currentpage = page;
        requestCount++;
        getService(bundle,currentpage,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {//onCompleted always called, @see onError(Throwable e)
                        requestCount--;
                        if(isVaild()){
                            mUiListener.onComplete();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isVaild()){
                            if (isOutOfDate(timeStamp)) {
                                return;
                            }
                            mUiListener.onFailed(e);
                            Log.d("tag", "failed", e);
                        }
                        onCompleted();
                    }

                    @Override
                    public void onNext(T response) {
                        if(isVaild()){
                            if (isOutOfDate(timeStamp)) {
                                return;
                            }
                            page = currentpage + 1;

                            if (response.isSuccess()) {
                                lastPage = response.getData().isLastPage();
                                if (currentpage==1) {
                                    mPageModel.setList(response.getData().getList());
                                    mUiListener.onRefresh();
                                } else {
                                    int startsize = mPageModel.getCount();
                                    mPageModel.addList(response.getData().getList());
                                    mUiListener.onLoadPage(startsize,response.getData().getList().size());
                                }
                                Log.d("tag", "success" + response.getData().toString());
                            } else {
                                onError(new Throwable(response.getMsg()));
                            }
                        }
                    }
                });
    }
}
