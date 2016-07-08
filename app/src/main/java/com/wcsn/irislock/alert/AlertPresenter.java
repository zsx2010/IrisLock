package com.wcsn.irislock.alert;

import com.ImaginationUnlimited.library.app.mvp.BasePresenter;
import com.wcsn.irislock.bean.Alert;
import com.wcsn.irislock.utils.DaoUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by suiyue on 2016/6/21 0021.
 */
public class AlertPresenter extends BasePresenter<IAlertUI> {

    private DaoUtils mDaoUtils;

    public void refreshAlertList() {
        mDaoUtils = DaoUtils.getInstance(getUI().getOwnerActivity().getApplicationContext());
        List<Alert> alerts = mDaoUtils.loadAlertAll();
        if (alerts.size() == 0) {
            Alert alert = new Alert();
            alert.setType(0);
            alert.setTime(new Date().toString());
            alert.setAlertInfo(80 + "");
            alerts.add(alert);
        }
        getUI().getAdapter().updateList(alerts);
    }

    public void deleteItem(int position) {
        Alert alert = (Alert) getUI().getAdapter().getCurrentItem(position);
        mDaoUtils.deleteAlert(alert);
    }
}
