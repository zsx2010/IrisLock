package com.wcsn.irislock.admin;

import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.wcsn.irislock.app.adapter.PagerAdapter;

/**
 * Created by suiyue on 2016/7/7 0007.
 */
public interface IAdminUI extends IUI{
    PagerAdapter getAdapter();
}
