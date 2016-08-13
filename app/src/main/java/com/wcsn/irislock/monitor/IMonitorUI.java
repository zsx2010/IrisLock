package com.wcsn.irislock.monitor;

import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.wcsn.irislock.app.adapter.PagerAdapter;

/**
 * Created by suiyue on 2016/8/14 0014.
 */
public interface IMonitorUI extends IUI{
    PagerAdapter getAdapter();
}
