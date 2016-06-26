package com.ImaginationUnlimited.library.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 *
 * 可以控制是否禁止滑动的ViewPager
 *
 * @author wangheng on 2016-03-11 13:46
 *
 */
public class UnscrollableViewPager extends ViewPager {

    private boolean scrollable = false;

    public UnscrollableViewPager(Context context) {
        super(context);
    }

    public UnscrollableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(!scrollable){
            return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(!scrollable){
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 设置是否可以滑动
     * @param scrollable 是否可以滑动
     */
    public void setScrollable(boolean scrollable){
        this.scrollable = scrollable;
    }

    /**
     * 当前是否处于可滑动状态
     *
     * @return 是否处于可滑动状态
     */
    public boolean isScrollable(){
        return scrollable;
    }
}
