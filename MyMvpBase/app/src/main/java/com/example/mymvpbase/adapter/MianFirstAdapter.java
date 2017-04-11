package com.example.mymvpbase.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.mymvpbase.bean.Model;
import com.example.mymvpbase.bean.buttombean;
import com.example.mymvpbase.bean.footbean;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import java.util.ArrayList;
import java.util.List;

/**
 *  统一的adapter
 */
public class MianFirstAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private AdapterDelegatesManager<List<Model>> mDelegatesManager;
    private List<Model> mList;
    public MianFirstAdapter(Context context) {
        this.mContext = context;
        this.mList=new ArrayList<>();
        mDelegatesManager = new AdapterDelegatesManager<>();
        mDelegatesManager.addDelegate(new BaseAdapterDeleger(mContext));
        mDelegatesManager.addDelegate(new FootAdapterDeleger(mContext));
        mDelegatesManager.addDelegate(new ButtomAdapterDeleger(mContext));
    }

    public void setData(List<Model> modelList){
        mList = modelList;
        notifyDataSetChanged();
    }

    public void addFoot( ){
        mList.add(new footbean());
        notifyItemInserted( mList.size()-1);
    }

    public void remoreFoot( ){
        notifyItemRemoved( mList.size()-1 );
        mList.remove( mList.size()-1 );
    }

    public void addButtom(){
        mList.add(new buttombean());
        notifyItemInserted( mList.size()-1);
    }

    @Override
    public int getItemViewType(int position) {
        return mDelegatesManager.getItemViewType(mList,position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegatesManager.onCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mDelegatesManager.onBindViewHolder(mList,position,holder);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        mDelegatesManager.onBindViewHolder(mList,position,holder,payloads);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
