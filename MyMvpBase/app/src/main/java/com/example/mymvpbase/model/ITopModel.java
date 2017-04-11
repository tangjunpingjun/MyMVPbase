package com.example.mymvpbase.model;

import com.example.mymvpbase.bean.javaBean;

import java.util.List;

/**
 * firstTopModel 的model 逻辑处理
 */

public interface ITopModel {

    //这是开始加载数据后给 presenter会调请求的结果的
    interface onloadFirstList{
        void  onSuccess(List<javaBean> list);
        void  onFailure(String str,Exception e);
    }

    void loadDate(String url,int type , onloadFirstList list);

}
