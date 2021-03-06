package com.harrricdev.edwin.andelaexercise.Main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.harrricdev.edwin.andelaexercise.Models.Item;
import com.harrricdev.edwin.andelaexercise.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by edwin on 3/6/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private Context mContext;
    private List<Item> mDevelopers;
    private LayoutInflater mInflater;

    public MainAdapter(Context context, List<Item> mDevelopers) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        reloadAdapter(mDevelopers);
    }

    public void reloadAdapter(List<Item> developers) {
        mDevelopers = developers;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.dev_list_row, parent, false);

        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item developer = mDevelopers.get(position);

        holder.username.setText(developer.getLogin());
        String url = developer.getAvatarUrl();
        Log.d("AMIN", url);

        /*Glide
                .with(mContext.getApplicationContext())
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.profile_place)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(100,100)
                .crossFade()
                .into(holder.avatar);*/

        Picasso.with(mContext).load(url).placeholder(R.drawable.profile_place).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return mDevelopers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        TextView username;

        public MyViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.gravar);
            username = (TextView) itemView.findViewById(R.id.list_username);
        }
    }
}
