package com.wcsn.irislock.home;

import com.ImaginationUnlimited.library.app.mvp.IUI;

/**
 * Created by suiyue on 2016/6/21 0021.
 */
public interface IMainUI extends IUI {
    void loadWeather(String temp, String weather, int imageId);
    void loadIrisImage(String state);
    void loadInfoImage(String state);
    void loadSafeState(String state);
    void loadPowerImage(String power);
    void loadPowerText(String power);
}
