package com.example.mymvpbase.model;

import com.example.mymvpbase.bean.DataDetilBean;

/**
 *  新闻详情的 model
 */

public interface IDetilModel {

    interface  OnloadFirstDataDetilListener{
        void  onSuccess(DataDetilBean  s);
        void  onFailure(String str,Exception e);
    }

    void loadDetilData(String url,OnloadFirstDataDetilListener listener);
}
