package com.example.mymvpbase.view;

import com.example.mymvpbase.bean.javaBean;

import java.util.List;

/**
 *
 */

public interface ITopView {

    void  hideProgress(); //关闭progress
    void  showprogress(); //显示progress

    void AddDate(List<javaBean> mList); //加载数据

    void showLoadFail(Exception e);  //当没有网络或者加载失败的时候，隐藏进度，自动弹出提示框


}
