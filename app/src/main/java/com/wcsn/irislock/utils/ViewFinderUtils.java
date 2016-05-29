package com.wcsn.irislock.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by suiyue on 2016/5/28 0028.
 * 查找和设置view、Activity、window的view的帮助类
 */
public class ViewFinderUtils {
    private interface FindWrapper {

        View findViewById(int id);

        Resources getResources();
    }

    private static class WindowWrapper implements FindWrapper {

        private final Window window;

        WindowWrapper(final Window window) {
            this.window = window;
        }

        public View findViewById(final int id) {
            return window.findViewById(id);
        }

        public Resources getResources() {
            return window.getContext().getResources();
        }
    }

    private static class ViewWrapper implements FindWrapper {

        private final View view;

        ViewWrapper(final View view) {
            this.view = view;
        }

        public View findViewById(final int id) {
            return view.findViewById(id);
        }

        public Resources getResources() {
            return view.getResources();
        }
    }

    private final FindWrapper wrapper;

    /**
     * 创建finder 包装给定view
     * @param view
     */
    public ViewFinderUtils(final View view) {
        wrapper = new ViewWrapper(view);
    }

    /**
     * 创建finder 包装给定window
     * @param window
     */
    public ViewFinderUtils(final Window window) {
        wrapper = new WindowWrapper(window);
    }

    /**
     * 创建finder 包装给定activity
     * @param activity
     */
    public ViewFinderUtils(final Activity activity) {
        this(activity.getWindow());
    }

    /**
     * 通过id查找view
     * @param id
     * @return 找到的view
     */
    @SuppressWarnings("unchecked")
    public <V extends View> V find(final int id) {
        return (V)wrapper.findViewById(id);
    }

    /**
     * 通过id获取image
     * @param id
     * @return image view
     */
    public ImageView imageView(final int id) {
        return find(id);
    }

    /**
     * 通过id获取text view
     * @param id
     * @return text view
     */
    public TextView textView(final int id) {
        return find(id);
    }

    /**
     * 通过id获取compound
     * @param id
     * @return image view
     */
    public CompoundButton compoundButton(final int id) {
        return find(id);
    }

    /**
     * 为指定id的child view 设置text
     * @param id
     * @param content
     * @return text view
     */
    public TextView setText(final int id, final CharSequence content) {
        final TextView text = find(id);
        text.setText(content);
        return text;
    }

    /**
     * 为指定id的child view 设置text
     * @param id
     * @param content
     * @return text view
     */
    public TextView setText(final int id, final int content) {
        return setText(id, wrapper.getResources().getString(content));
    }

    /**
     * 为指定id的child view 注册on click listener
     * @param id
     * @param listener
     * @return view registered with listener
     */
    public View onClick(final int id, final View.OnClickListener listener) {
        View clickable = find(id);
        clickable.setOnClickListener(listener);
        return clickable;
    }
    /**
     * 为指定id的child view 注册on click listener
     * @param id View id
     * @param listener 单击监听
     * @return view registered with listener
     */
    public <T extends View> T find(int id,View.OnClickListener listener){
        T t = find(id);
        t.setOnClickListener(listener);
        return t;
    }

    /**
     * 当指定id的child view 被点击时，注册runnable
     * @param id
     * @param runnable
     * @return view registered with runnable
     */
    public View onClick(final int id, final Runnable runnable) {
        return onClick(id, new View.OnClickListener() {

            public void onClick(View v) {
                runnable.run();
            }
        });
    }

    /**
     * 为指定一系列id的child view 注册on click listener
     * @param ids
     * @param listener
     */
    public void onClick(final View.OnClickListener listener, final int... ids) {
        for(int id: ids)
            find(id).setOnClickListener(listener);
    }

    /**
     * 当指定一系列id的child view 被点击时，注册runnable
     * @param ids
     * @param runnable
     */
    public void onClick(final Runnable runnable, final int... ids) {
        onClick(new View.OnClickListener() {

            public void onClick(View v) {
                runnable.run();
            }
        }, ids);
    }

    /**
     * 为对应id的child image view设置drawable
     * @param id
     * @param drawable
     * @return image view
     */
    public ImageView setDrawable(final int id, final int drawable) {
        ImageView image = imageView(id);
        image.setImageDrawable(image.getResources().getDrawable(drawable));
        return image;
    }

    /**
     * 为对应id的child view设置on checked change listener
     * @param id
     * @param listener
     * @return view registered with listener
     */
    public CompoundButton onCheck(final int id, final CompoundButton.OnCheckedChangeListener listener) {
        CompoundButton checkable = find(id);
        checkable.setOnCheckedChangeListener(listener);
        return checkable;
    }

    /**
     * 为对应id的child view的checked/unchecked状态注册runnable
     * @param id
     * @param runnable
     * @return view registered with runnable
     */
    public CompoundButton onCheck(final int id, final Runnable runnable) {
        return onCheck(id, new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                runnable.run();
            }
        });
    }

    /**
     * 为对应一系列id的child view设置on checked change listener
     * @param ids
     * @param listener
     */
    public void onCheck(final CompoundButton.OnCheckedChangeListener listener, final int... ids) {
        for(int id: ids)
            compoundButton(id).setOnCheckedChangeListener(listener);
    }

    /**
     * 为对应一系列id的child view的checked/unchecked状态注册runnable
     * @param ids
     * @param runnable
     */
    public void onCheck(final Runnable runnable, final int... ids) {
        onCheck(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                runnable.run();
            }
        }, ids);
    }
}
