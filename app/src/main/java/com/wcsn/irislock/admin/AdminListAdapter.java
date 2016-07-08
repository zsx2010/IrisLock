package com.wcsn.irislock.admin;

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
import com.wcsn.irislock.bean.User;
import com.wcsn.irislock.utils.ItemSlideHelper;

/**
 * Created by suiyue on 2016/7/7 0007.
 */
public class AdminListAdapter extends PagerAdapter<User, ListModel<User>,
        AdminHolder> implements ItemSlideHelper.Callback{


    private DeleteCallBack mDeleteCallBack;
    private RecyclerView mRecyclerView;

    interface DeleteCallBack {
        void removeItem(int position);
    }


    @Override
    public void onBindViewHolder(AdminHolder holder, int position) {
        User user = getCurrentItem(position);
        holder.bindData(user);

    }

    @Override
    public AdminHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) App.getInstance().getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new AdminHolder(inflater.inflate(R.layout.item_admin, parent, false), mDeleteCallBack);
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

    public AdminListAdapter(DeleteCallBack callBack) {
        mDeleteCallBack = callBack;
    }

    @Override
    public RecyclerView.ViewHolder getChildViewHolder(View childView) {
        return mRecyclerView.getChildViewHolder(childView);
    }

    @Override
    public View findTargetView(float x, float y) {
        return  mRecyclerView.findChildViewUnder(x, y);
    }

    @Override
    public ListModel<User> initModel() {
        return new ListModel<>();
    }

    @Override
    public AdminHolder newHolder(Context context) {
        return null;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerView = recyclerView;
        mRecyclerView.addOnItemTouchListener(new ItemSlideHelper(mRecyclerView.getContext(), this));

    }
}
