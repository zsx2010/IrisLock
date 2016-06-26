package com.wcsn.irislock.alert;

import com.ImaginationUnlimited.library.app.mvp.BasePresenter;
import com.wcsn.irislock.alert.bean.AlertInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suiyue on 2016/6/21 0021.
 */
public class AlertPresenter extends BasePresenter<IAlertUI> {

    public void refreshAlertList() {
        List<AlertInfo> alertInfos = new ArrayList<>();

        for (int i=0; i<9; i++) {
            AlertInfo alertInfo = new AlertInfo();
            alertInfo.setAlertType(i);
            alertInfos.add(alertInfo);
        }
        getUI().getAdapter().updateList(alertInfos);
    }

    public void addAlertList(AlertInfo alertInfo) {
        List<AlertInfo> alertInfos = new ArrayList<>();
        alertInfos.add(alertInfo);
        getUI().getAdapter().mergeList(alertInfos);
    }
}
