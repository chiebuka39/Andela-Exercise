package com.harrricdev.edwin.andelaexercise.Widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.harrricdev.edwin.andelaexercise.Utils.ToolBox;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by chiebuka on 12/21/2016.
 */
public class DevelopersRecyclerView extends RecyclerView {

    private List<View> mNonEmptyViews = Collections.emptyList();
    private List<View> mEmptyViews = Collections.emptyList();

    private AdapterDataObserver mObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            toggleViews();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            toggleViews();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            toggleViews();
        }
    };

    private void toggleViews() {
        if(getAdapter() != null && !mEmptyViews.isEmpty() && !mNonEmptyViews.isEmpty()){
            if(getAdapter().getItemCount() == 0){
                // show all empty views
                ToolBox.showViews(mEmptyViews);

                //hide recycler view
                setVisibility(View.GONE);

                // hide all non empty view
                ToolBox.hideViews(mNonEmptyViews);


            }else{
                // hide all empty views
                ToolBox.hideViews(mEmptyViews);

                //show recycler view
                setVisibility(View.VISIBLE);

                // show all non empty view
                ToolBox.showViews(mNonEmptyViews);
            }
        }
    }

    public DevelopersRecyclerView(Context context) {
        super(context);
    }

    public DevelopersRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DevelopersRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null){
            adapter.registerAdapterDataObserver(mObserver);
        }
        mObserver.onChanged();
    }

    public void hideIfEmpty(View... view) {
        mNonEmptyViews = Arrays.asList(view);
    }

    public void showIfEmpty(View... mEmptyView) {
        mEmptyViews = Arrays.asList(mEmptyView);
    }
}
