package com.wcsn.irislock.home;

import com.ImaginationUnlimited.library.app.mvp.BasePresenter;
import com.ImaginationUnlimited.library.utils.log.Logger;
import com.ImaginationUnlimited.library.utils.network.NetworkUtils;
import com.ImaginationUnlimited.library.utils.toast.ToastUtils;
import com.wcsn.irislock.home.beans.WeatherBean;
import com.wcsn.irislock.utils.WeatherPresenter;

import java.util.List;

/**
 * Created by suiyue on 2016/6/21 0021.
 */
public class MainPresenter extends BasePresenter<IMainUI> implements WeatherPresenter, WeatherModelImpl.LoadWeatherListener {

    private WeatherModel mWeatherModel;

    @Override
    public void loadWeatherData() {

        Logger.e("loadWeatherData");

        mWeatherModel = new WeatherModelImpl();

        if(!NetworkUtils.isAvailable(getContext())) {
            ToastUtils.toastShort("无网络连接");
            return;
        }

        WeatherModelImpl.LoadLocationListener listener = new WeatherModelImpl.LoadLocationListener() {
            @Override
            public void onSuccess(String cityName) {
                //定位成功，获取定位城市天气预报
                Logger.e("name");
                mWeatherModel.loadWeatherData(cityName, MainPresenter.this);
            }

            @Override
            public void onFailure(String msg, Exception e) {
                mWeatherModel.loadWeatherData("北京", MainPresenter.this);
            }
        };
        //获取定位信息
        mWeatherModel.loadLocation(getContext(), listener);

    }


    @Override
    public void onSuccess(List<WeatherBean> list) {
        if(list != null && list.size() > 0) {
            WeatherBean todayWeather = list.remove(0);
            getUI().loadWeather(todayWeather.getTemperature(), todayWeather.getWeather(), todayWeather.getImageRes());
        }
    }

    @Override
    public void onFailure(String msg, Exception e) {
        ToastUtils.toastShort("获取天气信息失败");
    }
}
