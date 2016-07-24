package com.wcsn.irislock.authorize;

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
import com.wcsn.irislock.bean.Authorize;
import com.wcsn.irislock.utils.ItemSlideHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suiyue on 2016/7/24 0024.
 */
public class AuthorizeListAdapter extends PagerAdapter<Authorize, ListModel<Authorize>,
        AuthorizeHolder> implements ItemSlideHelper.Callback{

    public static final int TYPE_CONTENT = 0;
    public static final int TYPE_TITLE = 1;

    private DeleteCallBack mDeleteCallBack;

    private RecyclerView mRecyclerView;

    public boolean isRemove = false;
    private List<Authorize> mAuthorizes = new ArrayList<>();


    public AuthorizeListAdapter(DeleteCallBack deleteCallBack, List<Authorize> authorizes, boolean isRemove) {
        mDeleteCallBack = deleteCallBack;
        mAuthorizes = authorizes;
        this.isRemove = isRemove;
    }

    @Override
    public void onBindViewHolder(AuthorizeHolder holder, int position) {
        Authorize authorize = getCurrentItem(position);
        holder.bindData(authorize, holder.getItemViewType());
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
    public ListModel<Authorize> initModel() {
        return new ListModel<>();
    }

    @Override
    public AuthorizeHolder newHolder(Context context) {
        return null;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
        mRecyclerView.addOnItemTouchListener(new ItemSlideHelper(mRecyclerView.getContext(), this));

    }

    @Override
    public AuthorizeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) App.getInstance().getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Logger.e(viewType + " = viewType");
        switch (viewType) {
            case TYPE_CONTENT:
                return new AuthorizeHolder(inflater.inflate(
                        R.layout.item_authorize,parent,false
                ), mDeleteCallBack, TYPE_CONTENT);
            case TYPE_TITLE:
                return new AuthorizeHolder(inflater.inflate(
                        R.layout.item_text,parent,false
                ), mDeleteCallBack, TYPE_TITLE);
            default:
                return new AuthorizeHolder(inflater.inflate(
                        R.layout.item_text,parent,false
                ), mDeleteCallBack, TYPE_TITLE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mAuthorizes.get(position).getAuthorizeImage() == null) {
            return TYPE_TITLE;
        } else {
            return TYPE_CONTENT;
        }
    }

    interface DeleteCallBack {
        void removeItem(int position);
    }
}
