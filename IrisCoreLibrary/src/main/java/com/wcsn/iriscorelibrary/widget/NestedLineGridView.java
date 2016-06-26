package com.wcsn.iriscorelibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.ImaginationUnlimited.library.R;


/**
 * 嵌套的GridView
 *
 * 注意，这个View没有考虑任何回收操作和效率问题，仅适用于有限数量的View展示
 *
 * @author wangheng on 2014年10月8日 下午8:41:58 <br/>
 */
public class NestedLineGridView extends LinearLayout {

    /**
     * Item的水平间距 *
     */
    private int mRowSpacing = 0;

    private int mColumnSpacing = 0;

    private int mColumnCount = 1;

    private boolean mDisplayLastRows = true;
    /**
     * View持有的单击监听器 *
     */
    private OnItemClickListener onItemClickListener = null;

    public NestedLineGridView(Context context) {
        super(context);
        init(context, null);
    }

    public NestedLineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NestedLineGridView);
        mRowSpacing = ta.getDimensionPixelSize(R.styleable.NestedLineGridView_rowSpacing, 0);
        mColumnSpacing = ta.getDimensionPixelSize(R.styleable.NestedLineGridView_columnSpacing, 0);
        mColumnCount = ta.getInteger(R.styleable.NestedLineGridView_columnCount, 1);
        mDisplayLastRows = ta.getBoolean(R.styleable.NestedLineGridView_displayLastRow, true);
        ta.recycle();
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(VERTICAL);
    }

    /**
     * setAdapter:设置Adapter. <br/>
     *
     * @param adapter
     * @author wangheng
     */
    public void setAdapter(ILineGridViewAdapter adapter) {
        removeAllViews();
        int count = adapter.getCount();

        int rowsCount;
        int modOfCountAndColumnCount = count % mColumnCount;
        if (mDisplayLastRows && modOfCountAndColumnCount != 0) {
            rowsCount = count / mColumnCount + 1;
        } else {
            rowsCount = count / mColumnCount;
        }

        LayoutParams rowParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        rowParams.bottomMargin = mRowSpacing;

        LayoutParams lastRowParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        for (int row = 0; row < rowsCount; row++) {

            LinearLayout rowView = new LinearLayout(getContext());
            rowView.setOrientation(HORIZONTAL);
            if (row != rowsCount - 1) {
                rowView.setLayoutParams(rowParams);
            } else {
                rowView.setLayoutParams(lastRowParams);
            }

            for (int column = 0; column < mColumnCount; column++) {
                int position = row * mColumnCount + column;
                if (position >= adapter.getCount()) {
                    break;
                }
                View v = adapter.getView(position, rowView,NestedLineGridView.this);
                v.setClickable(true);
                v.setOnClickListener(new MyOnClickListener(position, adapter.getItemId(position)));

                if (column == mColumnCount - 1) {
                    LayoutParams params = (LayoutParams) v.getLayoutParams();
                    if (params == null) {
                        params = new LayoutParams(LayoutParams.WRAP_CONTENT, 0);
                        params.weight = 1;
                    }
                    float totalWidth = getWidth() - getPaddingLeft() - getPaddingRight()
                            - getColumnSpacing() * (mColumnCount - 1);
                    params.width = (int)(totalWidth / mColumnCount);

                    rowView.addView(v, params);
                } else {
                    LayoutParams params = (LayoutParams) v.getLayoutParams();
                    if (params == null) {
                        params = new LayoutParams(LayoutParams.WRAP_CONTENT, 0);
                        params.weight = 1;
                    }
                    float totalWidth = getWidth() - getPaddingLeft() - getPaddingRight()
                            - getColumnSpacing() * (mColumnCount - 1);
                    params.width = (int)(totalWidth / mColumnCount);

                    params.rightMargin = mColumnSpacing;
                    rowView.addView(v, params);
                }
            }

            addView(rowView);
        }
    }

    /**
     * setOnItemClickListener:设置当View的Item被单击的时候的监听器<br/>
     *
     * @param onItemClickListener Item被单击的时候的监听器
     * @author wangheng
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public int getRowSpacing() {
        return mRowSpacing;
    }

    public void setRowSpacing(int mRowSpacing) {
        this.mRowSpacing = mRowSpacing;
    }

    public int getColumnSpacing() {
        return mColumnSpacing;
    }

    public void setColumnSpacing(int mColumnSpacing) {
        this.mColumnSpacing = mColumnSpacing;
    }

    public int getColumnCount() {
        return mColumnCount;
    }

    public void setColumnCount(int mColumnCount) {
        this.mColumnCount = mColumnCount;
    }

    public boolean isDisplayLastRows() {
        return mDisplayLastRows;
    }

    public void setDisplayLastRows(boolean mDisplayLastRows) {
        this.mDisplayLastRows = mDisplayLastRows;
    }


    /**
     * @author wangheng
     * @describe Item单击监听器
     * @date: 2014年10月8日 下午8:39:11 <br/>
     */
    public interface OnItemClickListener {

        /**
         * onItemClick:当LineGridView的任一个Item被点击的时候将回调这个方法<br/>
         *
         * @param parent   就是这个LineGridView
         * @param view     被点击的具体的Item的View
         * @param position 被点击的View在数据集合中的位置
         * @param id       被点击的Item的Id
         * @author wangheng
         */
        void onItemClick(View parent, View view, int position, long id);
    }

    /**
     * @author wangheng
     * @describe 自定义单击监听，为了要得到点击的位置
     * @date: 2014年10月8日 下午8:37:57 <br/>
     */
    private class MyOnClickListener implements OnClickListener {

        /**
         * 点击的position *
         */
        private int position = -1;

        /**
         * 点击的Item的id *
         */
        private long id = -1;

        private MyOnClickListener(int position, long id) {
            this.position = position;
            this.id = id;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener
                        .onItemClick(NestedLineGridView.this, NestedLineGridView.this.getChildAt(position), position, id);
            }

        }

    }

}
