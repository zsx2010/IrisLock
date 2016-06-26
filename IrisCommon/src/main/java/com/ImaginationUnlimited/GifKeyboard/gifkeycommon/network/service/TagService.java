package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.service;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseGifList;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseTagList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by someHui on 4/12/16.
 */
public interface TagService {
    @GET("tag/searchGif")
    Observable<ResponseGifList> tagSearchResult(@Query("tags[]") String[] tagIds,
                                                @Query("page") int page,
                                                @Query("pageSize") int pageSize);

    @GET("tag/searchGif")
    Observable<ResponseGifList> tagAndEmojiSearchResult(@Query("tags[]") String[] tagIds,
                                                        @Query("emojiId") int emojiId,
                                                        @Query("page") int page,
                                                        @Query("pageSize") int pageSize);

    @GET("tag/hotTagList")
    Observable<ResponseTagList> hotTag(@Query("page") int page,
                                       @Query("pageSize") int pageSize);

    @GET("tag/hint")
    Observable<ResponseTagList> hintTag(@Query("preText") String text);
}
