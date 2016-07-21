package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network;

import android.util.Log;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.service.DownloadService;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.service.GifService;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.service.SearchService;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.service.TagService;
import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.service.UserService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RESTful Service 工厂
 *
 * @author wangheng on 2016-03-08 18:45
 */
public class RESTfulFactory {
    //FIXME check me please
    public static final String URL_BASE = "http://10.103.24.178:8080/IrisLockWeb/";
    // 天气预报url
    public static final String WEATHER = "http://wthrcdn.etouch.cn/weather_mini?city=";
    //百度定位
    public static final String INTERFACE_LOCATION = "http://api.map.baidu.com/geocoder";

    public static String getUrlBase() {
        return URL_BASE;
    }

    // Gif列表相关的接口对象
    private static GifService sGifService = null;

    // 搜索相关的接口对象
    private static SearchService sSearchService = null;

    // 用户相关的接口对象
    private static UserService sUserService = null;

    //tag
    private static TagService sTagService = null;

    //download
    private static DownloadService sDownloadService = null;

    //天气

    public static DownloadService getDownloadService(){
        if(null == sDownloadService){
            sDownloadService = create(DownloadService.class);
        }
        return sDownloadService;
    }

    /**
     * 得到GifService
     * @return GifService
     */
    public static GifService getGifService(){
        if(null == sGifService){
            sGifService = create(GifService.class);
        }
        return sGifService;
    }
    /**
     * 得到 UserService
     * @return UserService
     */
    public static UserService getUserService(){
        if(null == sUserService){
            sUserService = create(UserService.class);
        }
        return sUserService;
    }

    /**
     * 得到SearchService
     * @return SearchService
     */
    public static SearchService getSearchService(){
        if(null == sSearchService){
            sSearchService = create(SearchService.class);
        }
        return sSearchService;
    }

    public static TagService getTagService(){
        if(null == sTagService){
            sTagService = create(TagService.class);
        }
        return sTagService;
    }

    private static <T> T create(Class<T> clazz){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
//                        .addHeader("token", SPModel.getToken())
                        .build();
                Log.e("TAG", request.toString() + " " + request.url());
                Response response = chain.proceed(request);
                return response;
            }
        });

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getUrlBase())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(clazz);
    }
}
