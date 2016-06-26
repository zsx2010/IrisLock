package com.wcsn.iriscorelibrary.app.mvp;

/**
 * Created by suiyue on 2016/6/7 0007.
 */
public interface IUIState {
    boolean isPaused();
    boolean isDestoryed();
    boolean isDetached();
    boolean isStoped();
    boolean isFragmentHidden();
    boolean isVisibleToUser();

}
