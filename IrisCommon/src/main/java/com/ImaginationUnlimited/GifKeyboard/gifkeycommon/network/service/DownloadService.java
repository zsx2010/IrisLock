package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by someHui on 16/5/18.
 */
public interface DownloadService {
    @GET
    @Streaming
    Observable<ResponseBody> download(@Url String url);


}
