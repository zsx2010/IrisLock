package com.wcsn.irislock.utils.network;

import android.content.Context;
import android.support.annotation.CallSuper;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.BaseResponse;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.exception.TokenInvalidException;

import rx.Subscriber;

/**
 * Created by someHui on 16/5/6.
 */
public abstract class ResponseSubscriber<T extends BaseResponse> extends Subscriber<T> {

    @CallSuper
    @Override
    public void onNext(T t) {
        if(t.isSuccess()){
            onSuccess(t);
        }else if(t.isInvalidToken()){
            onError(new TokenInvalidException());
        }else{
            onFail(t);
        }
    }

    @CallSuper
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if(e instanceof TokenInvalidException){
            onInvalidToken(getEvContext());
        }
    }

    public abstract Context getEvContext();

    public abstract void onSuccess(T t);
    public abstract void onFail(T t);

    public void onInvalidToken(Context context){

    }
}
