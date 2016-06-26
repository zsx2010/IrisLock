package com.ImaginationUnlimited.library.widget;

import android.view.View;
import android.view.ViewGroup;

/**
 * LineGridView的适配器都要实现这个协议
 * @author wangheng on 2014年10月8日 下午8:43:36 <br/>
 */
public interface ILineGridViewAdapter {
    /**
     * getCount:数据集合的个数<br/>
     *
     * @return item count
     */
    int getCount();

    /**
     * getItem:具体的数据集中的position对应的数据 <br/>
     *
     * @param position 在数据集中的位置
     * @return Item
     */
    Object getItem(int position);

    /**
     * getItemId:指定position的Item的id <br/>
     *
     * @param position position
     * @return item id
     */
    long getItemId(int position);

    /**
     * getView:得到指定位置的View <br/>
     *
     * @param position position
     * @param parent parent view
     * @param gridView NestedLineGridView
     * @return view
     */
    View getView(int position, ViewGroup parent,NestedLineGridView gridView);
}
