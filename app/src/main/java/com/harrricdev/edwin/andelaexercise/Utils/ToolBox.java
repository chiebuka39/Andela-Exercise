package com.harrricdev.edwin.andelaexercise.Utils;


import android.view.View;


import java.util.List;


/**
 * Created by chiebuka on 12/18/2016.
 */
public class ToolBox {




    public static void showViews(List<View> views){
        for (View view : views){
            if(view != null){
                view.setVisibility(View.VISIBLE);
            }

        }
    }

    public static void hideViews(List<View> views){
        for (View view : views){
            if(view != null){
                view.setVisibility(View.GONE);
            }
            ;
        }
    }



}
