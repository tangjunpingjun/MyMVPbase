package com.example.mymvpbase.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymvpbase.R;
import com.example.mymvpbase.bean.Model;
import com.example.mymvpbase.bean.buttombean;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

/**
 * Created by junping on 2017/4/11.
 */

public class ButtomAdapterDeleger  extends AdapterDelegate<List<Model>>{
    private Context mContext;

    public ButtomAdapterDeleger(Context context) {
        mContext = context;
    }

    @Override
    protected boolean isForViewType(@NonNull List<Model> items, int position) {
        return items.get(position) instanceof buttombean;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_first_buttom ,parent,false);
        return  new DefultViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<Model> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {

    }

    static class DefultViewHolder extends RecyclerView.ViewHolder {
        public DefultViewHolder(View itemView) {
            super(itemView);
        }
    }


}
