package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.service;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseNoData;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseStringList;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 服务器API
 *
 *
 */
public interface UserService {

    @POST("adminAuthorize")
    Observable<ResponseNoData> adminAuthorize(@Query("deviceId") String deviceId,
                                    @Query("imageName") String imageName,
                                    @Query("isAuthorize") String isAuthorize);

    @POST("getPicture")
    Observable<ResponseStringList> getPicture(@Query("deviceId") String deviceId);


}
