package com.wcsn.irislock.admin;

import com.ImaginationUnlimited.library.app.mvp.BasePresenter;
import com.wcsn.irislock.bean.User;
import com.wcsn.irislock.utils.DaoUtils;

import java.util.List;

/**
 * Created by suiyue on 2016/7/7 0007.
 */
public class AdminPresenter extends BasePresenter<IAdminUI> {

    private DaoUtils mDaoUtils;
    public void refreshUserList() {
        mDaoUtils = DaoUtils.getInstance(getUI().getOwnerActivity().getApplicationContext());
        List<User> users = mDaoUtils.loadUserAll();
        getUI().getAdapter().updateList(users);
    }
    public void deleteItem(int position) {
        User user = (User) getUI().getAdapter().getCurrentItem(position);
        mDaoUtils.deleteUser(user);
    }
}
