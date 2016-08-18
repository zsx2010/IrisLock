package com.wcsn.irislock.authorize;

import com.ImaginationUnlimited.library.app.mvp.IUI;
import com.wcsn.irislock.app.adapter.PagerAdapter;
import com.wcsn.irislock.bean.Authorize;

import java.util.List;

/**
 * Created by suiyue on 2016/7/24 0024.
 */
public interface IAuthorizeListUI extends IUI{

    void refreshList(List<Authorize> authorizes);
    PagerAdapter getAdapter();
}
