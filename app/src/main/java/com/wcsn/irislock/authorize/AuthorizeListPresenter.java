package com.wcsn.irislock.authorize;

import com.ImaginationUnlimited.library.app.mvp.BasePresenter;
import com.wcsn.irislock.bean.Authorize;
import com.wcsn.irislock.utils.DaoUtils;

import java.util.List;

/**
 * Created by suiyue on 2016/7/24 0024.
 */
public class AuthorizeListPresenter extends BasePresenter<IAuthorizeListUI> {

    private DaoUtils mDaoUtils;

    public void getAuthorizeList() {
        mDaoUtils = DaoUtils.getInstance(getUI().getOwnerActivity().getApplicationContext());
        List<Authorize> authorizes = mDaoUtils.loadAuthorizeAll();
        String date = "";
        if (authorizes.size() != 0) {
            date = authorizes.get(0).getDate();
            Authorize authorize = new Authorize();
            authorize.setDate(date);
            authorizes.add(0, authorize);
        }
        for(int i=1; i<authorizes.size(); i++) {
            if (!authorizes.get(i).getDate().equals(date)) {
                date = authorizes.get(i).getDate();
                Authorize authorize = new Authorize();
                authorize.setDate(date);
                authorizes.add(i,authorize);
            }
        }
        getUI().getAdapter().updateList(authorizes);


    }

    public void deleteItem(int position) {
        Authorize authorize = (Authorize) getUI().getAdapter().getCurrentItem(position);
        mDaoUtils.deleteAuthorize(authorize);
    }
}
