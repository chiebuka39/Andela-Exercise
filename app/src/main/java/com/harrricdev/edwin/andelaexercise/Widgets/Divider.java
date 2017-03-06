package com.harrricdev.edwin.andelaexercise.Widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.harrricdev.edwin.andelaexercise.R;


/**
 * Created by chiebuka on 12/21/2016.
 */
public class Divider extends RecyclerView.ItemDecoration {

    public static final String TAG = "TEAM3";

    //This saves the Drawable divider the seperate our recyclerView
    private Drawable mDivider;

    private int mOrientation;

    /**
     * Contructor to set up the Divider class
     * @param context
     * @param orientation
     */
    public Divider(Context context, int orientation){
        mDivider = ContextCompat.getDrawable(context, R.drawable.divider);

        if(orientation != LinearLayoutManager.VERTICAL){
            throw new IllegalArgumentException("This item Decoration can be used only with a recycler view that uses a linear layout manager");
        }

        mOrientation = orientation;
    }


    /**
     * this overrides RecyclerView.onItemDecoration draw method to draw the divider below the recycler view
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        if(mOrientation == LinearLayoutManager.VERTICAL){
            drawHorizontalDivider(c, parent,state);
        }
    }

    /**
     * This is to calculate where to draw the divider
     * @param c
     * @param parent
     * @param state
     */
    private void drawHorizontalDivider(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left, right,top,bottom;
        left = parent.getPaddingLeft();
        right = parent.getWidth() - parent.getPaddingRight();
        int count = parent.getChildCount();

        for(int i = 0; i < count; i++){
            View current = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) current.getLayoutParams();
            top = current.getBottom() + params.bottomMargin;
            bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }

    }

    /**
     *
     * Draw the divider below the recycler view row
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
       if(mOrientation == LinearLayoutManager.VERTICAL){
           outRect.set(0,0,0, mDivider.getIntrinsicHeight());
       }
    }
}
