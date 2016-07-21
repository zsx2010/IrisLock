package com.wcsn.irislock.alert;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.mvp.model.ListModel;
import com.ImaginationUnlimited.library.utils.log.Logger;
import com.wcsn.irislock.R;
import com.wcsn.irislock.app.App;
import com.wcsn.irislock.app.adapter.PagerAdapter;
import com.wcsn.irislock.bean.Alert;
import com.wcsn.irislock.utils.ItemSlideHelper;

/**
 * Created by suiyue on 2016/6/22 0022.
 */
public class AlertListAdapter extends PagerAdapter<Alert, ListModel<Alert>,
        AlertHolder> implements ItemSlideHelper.Callback{


    private DeleteCallBack mDeleteCallBack;

    private RecyclerView mRecyclerView;

    public boolean isRemove = false;

    interface DeleteCallBack {
        void removeItem(int position);
    }


    @Override
    public int getHorizontalRange(RecyclerView.ViewHolder holder) {
        Logger.e(holder.itemView.getClass().toString());
        if (holder.itemView instanceof LinearLayout) {
            ViewGroup viewGroup = (ViewGroup) holder.itemView;
            if(viewGroup.getChildCount() == 2){
                return viewGroup.getChildAt(1).getLayoutParams().width;
            }
        }
        return 0;
    }

    public AlertListAdapter(DeleteCallBack callBack) {
        mDeleteCallBack = callBack;
    }

    @Override
    public RecyclerView.ViewHolder getChildViewHolder(View childView) {
        return mRecyclerView.getChildViewHolder(childView);
    }

    @Override
    public View findTargetView(float x, float y) {
        return mRecyclerView.findChildViewUnder(x, y);
    }

    @Override
    public AlertHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) App.getInstance().getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new AlertHolder(inflater.inflate(R.layout.item_alert, parent, false), mDeleteCallBack);
    }

    @Override
    public ListModel<Alert> initModel() {

        return new ListModel<>();
    }

    @Override
    public void onBindViewHolder(AlertHolder holder, int position) {
        Alert alert = getCurrentItem(position);
        holder.bindData(alert, isRemove);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerView = recyclerView;
        mRecyclerView.addOnItemTouchListener(new ItemSlideHelper(mRecyclerView.getContext(), this));

    }

    @Override
    public AlertHolder newHolder(Context context) {
        return null;
    }

}
