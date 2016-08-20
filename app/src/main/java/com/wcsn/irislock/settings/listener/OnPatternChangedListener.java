package com.wcsn.irislock.settings.listener;

/**
 * Created by suiyue on 2016/8/20 0020.
 */
public interface OnPatternChangedListener {

    void patternChanged(String password);
    void patternStart(boolean start);

}
