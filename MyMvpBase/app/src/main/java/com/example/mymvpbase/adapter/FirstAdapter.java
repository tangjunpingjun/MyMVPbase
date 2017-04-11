package com.example.mymvpbase.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.example.mymvpbase.R;
import com.example.mymvpbase.bean.javaBean;
import com.example.mymvpbase.utils.ImageLoaderUtils;
import com.thinkcool.circletextimageview.CircleTextImageView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.ViewHolder> {
    private Context mContext;
    private List<javaBean> mJavaBeanList;

    public FirstAdapter(Context context) {
        this.mContext = context;
        mJavaBeanList=new ArrayList<>();
    }

    //设置数据
    public void setDate(List<javaBean> list){
        this.mJavaBeanList = list;
        LogUtils.e( mJavaBeanList.size()+" setDate:");
        FirstAdapter.this.notifyDataSetChanged();
    }


    @Override
    public FirstAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_first_news , parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FirstAdapter.ViewHolder holder, int position) {
        javaBean bean= mJavaBeanList.get(position);
        LogUtils.e("ssss");
        holder.title.setText(bean.getTitle());
        holder.desc.setText(bean.getDigest());
        ImageLoaderUtils.display(mContext,holder.img,bean.getImgsrc());

    }

    @Override
    public int getItemCount() {
        return  mJavaBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleTextImageView img;
        private AppCompatTextView title;
        private AppCompatTextView desc;

        public ViewHolder(View itemView) {
            super(itemView);
            img= (CircleTextImageView) itemView.findViewById(R.id.imgView);
            title= (AppCompatTextView) itemView.findViewById(R.id.tvTitle);
            desc= (AppCompatTextView) itemView.findViewById(R.id.tvDesc);
        }
    }
}
