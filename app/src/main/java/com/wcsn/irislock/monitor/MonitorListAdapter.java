package com.wcsn.irislock.monitor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ImaginationUnlimited.GifKeyboard.gifkeycommon.mvp.model.ListModel;
import com.wcsn.irislock.R;
import com.wcsn.irislock.app.App;
import com.wcsn.irislock.app.adapter.PagerAdapter;
import com.wcsn.irislock.utils.ItemSlideHelper;

/**
 * Created by suiyue on 2016/8/14 0014.
 */
public class MonitorListAdapter extends PagerAdapter<String, ListModel<String>,
        MonitorHolder> implements ItemSlideHelper.Callback{


    private DeleteCallBack mDeleteCallBack;

    private RecyclerView mRecyclerView;

    public boolean isRemove = false;

    interface DeleteCallBack {
        void removeItem(int position);
    }

    public MonitorListAdapter(DeleteCallBack deleteCallBack) {
        mDeleteCallBack = deleteCallBack;
    }

    @Override
    public void onBindViewHolder(MonitorHolder holder, int position) {
        String s = getCurrentItem(position);
        holder.bindData(s, isRemove);

    }

    @Override
    public int getHorizontalRange(RecyclerView.ViewHolder holder) {
        if (holder.itemView instanceof LinearLayout) {
            ViewGroup viewGroup = (ViewGroup) holder.itemView;
            if(viewGroup.getChildCount() == 2){
                return viewGroup.getChildAt(1).getLayoutParams().width;
            }
        }
        return 0;
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
    public ListModel<String> initModel() {
        return new ListModel<>();
    }

    @Override
    public MonitorHolder newHolder(Context context) {
        return null;
    }

    @Override
    public MonitorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) App.getInstance().getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new MonitorHolder(inflater.inflate(R.layout.item_monitor, parent, false), mDeleteCallBack);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerView = recyclerView;
        mRecyclerView.addOnItemTouchListener(new ItemSlideHelper(mRecyclerView.getContext(), this));
    }
}
