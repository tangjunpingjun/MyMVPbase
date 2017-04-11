package com.example.mymvpbase.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymvpbase.R;
import com.example.mymvpbase.bean.Model;
import com.example.mymvpbase.bean.javaBean;
import com.example.mymvpbase.utils.ImageLoaderUtils;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.thinkcool.circletextimageview.CircleTextImageView;

import java.util.List;

/**
 *  这是javabean的适配器。
 */
public class BaseAdapterDeleger  extends AdapterDelegate<List<Model>> {
    private Context mContext;
    private LayoutInflater mInflater;

    public BaseAdapterDeleger(Context context) {
        this.mContext = context;
        this.mInflater=LayoutInflater.from(mContext);
    }

    //判断这个数据是不是有这个adapter来负责给定的数据
    @Override
    protected boolean isForViewType(@NonNull List<Model> items, int position) {
        return items.get(position) instanceof javaBean;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view=mInflater.inflate(R.layout.item_first_news,parent, false);
        return new xwenViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<Model> items, int position, @NonNull RecyclerView.ViewHolder holder,
                                    @NonNull List<Object> payloads) {
        xwenViewHolder xwenHolder= (xwenViewHolder) holder;
        javaBean entity= (javaBean) items.get(position);
        xwenHolder.desc.setText(entity.getDigest());
        xwenHolder.title.setText(entity.getTitle());
        ImageLoaderUtils.display(mContext,xwenHolder.img,entity.getImgsrc());
    }

    static class xwenViewHolder extends RecyclerView.ViewHolder {
        private CircleTextImageView img;
        private AppCompatTextView title;
        private AppCompatTextView desc;
        public xwenViewHolder(View itemView) {
            super(itemView);
            img= (CircleTextImageView) itemView.findViewById(R.id.imgView);
            title= (AppCompatTextView) itemView.findViewById(R.id.tvTitle);
            desc= (AppCompatTextView) itemView.findViewById(R.id.tvDesc);
        }
    }
}
