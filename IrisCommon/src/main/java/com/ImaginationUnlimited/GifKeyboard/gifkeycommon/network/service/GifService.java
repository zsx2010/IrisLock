package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.service;

import android.support.annotation.StringDef;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.data.BaseData;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.data.DataEmpty;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.data.DataGifInfo;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.data.DataRegifey;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.BaseResponse;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseCommentList;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseGifList;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseNoData;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseSpecialList;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity.response.ResponseStringList;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Gif相关接口
 *
 * @author wangheng on 2016-03-15 16:47
 */
public interface GifService {
    @GET("gif/editInfo")
    Observable<BaseResponse<DataRegifey>> regifey(@Query("id") String gifId);

    @GET("gif/info")
    Observable<BaseResponse<DataGifInfo>> gifInfo(@Query("id") String gifId);

    @GET("gifShare")
    Observable<BaseResponse<BaseData>> callShare(@Query("gifId") String gifId,@Query("way")String way);

    String LIKE = "like";
    String UNLIKE = "unLike";

    @StringDef({LIKE, UNLIKE})
    @interface LIKETYPE {
    }

    @GET("gifLike")
    Observable<ResponseNoData> gifLike(@Query("gifId") String uid, @Query("type") @LIKETYPE String type);

    String FAVOR = "favorite";
    String UNFAVOR = "unFavorite";

    @StringDef({FAVOR, UNFAVOR})
    @interface FAVORTYPE {
    }

    @GET("gifFavorite")
    Observable<ResponseNoData> gifFavor(@Query("gifId") String uid, @Query("type") @FAVORTYPE String type);

    @GET("gif/commentLike")
    Observable<ResponseNoData> gifCommentLike(@Query("commentId") String uid, @Query("type") @LIKETYPE String type);

    String FOLLOW = "follow";
    String UNFOLLOW = "unFollow";

    @StringDef({FOLLOW, UNFOLLOW})
    @interface FOLLOWTYPE {
    }

    @GET("user/follow")
    Observable<ResponseNoData> gifFollow(@Query("userId") String uid, @Query("type") @FOLLOWTYPE String type);

    @GET("gif/commentList")
    Observable<ResponseCommentList> getComment(@Query("gifId") String gifId);

    @GET("gifList")
    Observable<ResponseGifList> gifList(@Query("type") int type,
                                        @Query("page") int page,
                                        @Query("pageSize") int pageSize);

    @GET("gifList")
    Observable<ResponseGifList> gifList(@Query("type") int type,
                                        @Query("page") int page,
                                        @Query("pageSize") int pageSize,
                                        @Query("featureId") int id);

    @GET("gifList")
    Observable<ResponseGifList> gifList(@Query("type") int type,
                                        @Query("page") int page,
                                        @Query("pageSize") int pageSize,
                                        @Query("userId") String userId);

    @GET("gifList")
    Observable<ResponseGifList> emojiGifList(@Query("type") int type,
                                             @Query("page") int page,
                                             @Query("pageSize") int pageSize,
                                             @Query("emojiId") int emojiId);

    @GET("gif/related")
    Observable<ResponseGifList> relatedGifList(@Query("gifId") String gifId);

    @Multipart
    @POST("gif/uploadToFavorite")
    Observable<ResponseNoData> uploadToFavorite(@Part("image\"; filename=\"image") RequestBody image,
                                                @Part("width") RequestBody width,
                                                @Part("height") RequestBody height);

    @Multipart
    @POST("gif/add")
    Observable<ResponseNoData> uploadGif(@Part("image\"; filename=\"image") RequestBody image,
                                         @Part("imgWidth") RequestBody width,
                                         @Part("imgHeight") RequestBody height,
                                         @Part("emojiId") RequestBody emojiTag,
                                         @Part("tags[]") RequestBody tags[],
                                         @Part("originImage\"; filename=\"originImage") RequestBody originImage,
                                         @Part("editInfo") RequestBody json
    );

    @Multipart
    @POST("gif/comment")
    Observable<ResponseNoData> gifCommentPost(@Part("gifId") RequestBody gifId,
                                              @Part("commentId") RequestBody commentId,
                                              @Part("content") RequestBody content,
                                              @Part("image") RequestBody image,
                                              @Part("atList") RequestBody atList);

    @GET("gif/randomText")
    Observable<ResponseStringList> shuffleTextList();

    @GET("user/tagUsedList")
    Observable<ResponseStringList> recentTagList();

    @GET("user/tagSuggestList")
    Observable<ResponseStringList> suggestTagList();

    @GET("gif/typeList")
    Observable<ResponseSpecialList> specialList();

}
