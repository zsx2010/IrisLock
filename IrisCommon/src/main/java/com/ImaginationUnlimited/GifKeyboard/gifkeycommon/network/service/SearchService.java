package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.service;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseGifList;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseSearch;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseTagList;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseUserInfoList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 搜索相关接口
 *
 * @author wangheng on 2016-03-15 18:48
 */
public interface SearchService {
    @GET("search")
    Observable<ResponseSearch> search(@Query("keyword") String key, @Query("page") int page, @Query("pageSize") int pageSize);

    @GET("tag/hotTagList")
    Observable<ResponseTagList> hotTag(@Query("page") int page, @Query("pageSize") int pageSize);

    @GET("tag/recentTagList")
    Observable<ResponseTagList> recentTag(@Query("page") int page, @Query("pageSize") int pageSize);

    @GET("tag/suggestTagList")
    Observable<ResponseTagList> SuggestionTag(@Query("page") int page, @Query("pageSize") int pageSize);

    @GET("user/popular")
    Observable<ResponseUserInfoList> popularUser(@Query("page") int page, @Query("pageSize") int pageSize);

    @GET("user/messageList")
    Observable<ResponseMessageInfoList> getMessage(@Query("page") int page, @Query("pageSize") int pageSize);
}
