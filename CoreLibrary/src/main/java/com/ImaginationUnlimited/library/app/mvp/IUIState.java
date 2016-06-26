package com.ImaginationUnlimited.library.app.mvp;

/**
 * UI组件状态接口
 * @author wangheng
 */
public interface IUIState {

    /**
     * isPaused:是否已经pause. <br/>
     *
     * @return 是否已经pause
     */
    boolean isPaused();

    /**
     * isDestoryed:是否已经destoryed. <br/>
     *
     * @return 是否已经destoryed
     */
    boolean isDestoryed();

    /**
     * isDetached:Fragment是否Detached;对Activity来讲,返回值同isDestoryed(). <br/>
     *
     * @return ragmeng是否Detached;对Activity来讲,返回值同isDestoryed()
     */
    boolean isDetached();

    /**
     * isStoped:是否已Stop. <br/>
     *
     * @return 是否已Stop
     */
    boolean isStoped();

    /**
     * isFragmentHidden:Fragment是否被隐藏，对Activity来讲，返回值同isDestoryed(). <br/>
     *
     * @return Fragment是否被隐藏，对Activity来讲，返回值同isDestoryed()
     */
    boolean isFragmentHidden();

    /**
     * isVisibleToUser:判断是否对用户可见，对Activity来讲和isPause()方法返回值相反；
     * 对Fragment来讲和setUserVisibleHint()的参数值一致. <br/>
     *
     * @return 判断是否对用户可见，对Activity来讲和isPause()方法返回值相反；
     * 对Fragment来讲和setUserVisibleHint()的参数值一致.
     */
    boolean isVisibleToUser();
}
