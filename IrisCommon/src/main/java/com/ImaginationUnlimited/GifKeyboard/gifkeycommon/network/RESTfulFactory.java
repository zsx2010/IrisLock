package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network;

import android.util.Log;

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
    public static final String URL_BASE = "http://10.103.25.127/:8080/IrisLockWeb/";
    // 天气预报url
    public static final String WEATHER = "http://wthrcdn.etouch.cn/weather_mini?city=";
    //百度定位
    public static final String INTERFACE_LOCATION = "http://api.map.baidu.com/geocoder";

    public static String getUrlBase() {
        return URL_BASE;
    }


    // 用户相关的接口对象
    private static UserService sUserService = null;

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
