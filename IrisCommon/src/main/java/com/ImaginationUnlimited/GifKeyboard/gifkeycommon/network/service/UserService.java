package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.service;

import android.support.annotation.StringDef;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseAnotherPersonalInfo;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseFollowUsers;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseLogin;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseNoData;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponsePersonalInfo;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseTagList;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 用户相关的API服务
 *
 * @author wangheng on 2016-03-29 11:37
 */
public interface UserService {
    String FOLLOW = "follow";
    String UNFOLLOW = "unFollow";

    @StringDef({FOLLOW, UNFOLLOW})
    @interface FOLLOWTYPE {
    }

    @POST("user/login")
    Observable<ResponseLogin> login(@Query("email") String email,
                                    @Query("password") String password);

    @POST("user/outLogin")
    Observable<ResponseLogin> outLogin(@Query("email") String email,
                                       @Query("userId") String userId,
                                       @Query("name") String name,
                                       @Query("token") String token,
                                       @Query("headImgUrl") String headImgUrl);

    @GET("user/tagUsedList")
    Observable<ResponseTagList> getUserRecentTagList();

    @GET("user/follow")
    Observable<ResponseNoData> follow(@Query("userId") String uid,
                                      @Query("type") @FOLLOWTYPE String type);

    @GET("user/myInfo")
    Observable<ResponsePersonalInfo> myInfo();

    @GET("user/herInfo")
    Observable<ResponseAnotherPersonalInfo> herInfo(@Query("herUserId") String herUserId);

    @GET("user/updateInfo")
    Observable<ResponseNoData> updateInfo(@Query("name") String name,
                                          @Query("desc") String desc);

    @GET("user/followUsers")
    Observable<ResponseFollowUsers> followUsers(@Query("page") int page,
                                           @Query("pageSize") int pageSize);

    @GET("user/fans")
    Observable<ResponseFollowUsers> fans(@Query("page") int page,
                                                @Query("pageSize") int pageSize);


}
