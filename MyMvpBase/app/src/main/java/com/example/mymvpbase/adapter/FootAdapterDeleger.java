package com.example.mymvpbase.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymvpbase.R;
import com.example.mymvpbase.bean.Model;
import com.example.mymvpbase.bean.footbean;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

/**
 * 加载中
 */

public class FootAdapterDeleger extends AdapterDelegate<List<Model>> {
    private Context mContext;

    public FootAdapterDeleger(Context context) {
        mContext = context;
    }

    @Override
    protected boolean isForViewType(@NonNull List<Model> items, int position) {
        return items.get( position) instanceof footbean;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_first_footer, parent,false);
        return new FootViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<Model> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        FootViewHolder footHolder= (FootViewHolder) holder;

    }

    static class FootViewHolder extends RecyclerView.ViewHolder {
        TextView  msg;
        public FootViewHolder(View itemView) {
            super(itemView);
            msg= (TextView) itemView.findViewById(R.id.more_data_msg);
        }
    }
}
