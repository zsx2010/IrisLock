package com.wcsn.irislock.app.event;

/**
 * Created by suiyue on 2016/7/23 0023.
 */
public class EvIsOpenDoor {
    private boolean mIsOpenDoor;
    public EvIsOpenDoor(boolean isOpenDoor) {
        mIsOpenDoor = isOpenDoor;
    }

    public boolean isOpenDoor() {
        return mIsOpenDoor;
    }

    public void setOpenDoor(boolean openDoor) {
        mIsOpenDoor = openDoor;
    }
}
