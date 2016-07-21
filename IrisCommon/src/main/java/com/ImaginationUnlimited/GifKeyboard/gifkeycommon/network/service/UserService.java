package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.service;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseNoData;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 用户相关的API服务
 *
 * @author wangheng on 2016-03-29 11:37
 */
public interface UserService {

    @POST("adminAuthorize")
    Observable<ResponseNoData> adminAuthorize(@Query("deviceId") String deviceId,
                                    @Query("imageName") String imageName,
                                    @Query("isAuthorize") String isAuthorize);


}
