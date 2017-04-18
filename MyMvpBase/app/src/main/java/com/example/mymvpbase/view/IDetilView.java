package com.example.mymvpbase.view;

/**
 *  新闻详情的 View
 */
public interface IDetilView {

    void  hideProgress(); //关闭progress
    void  showprogress(); //显示progress

    void showDetilContent(String  s); //加载数据

    void showLoadFail(String s ,Exception e);  //当没有网络或者加载失败的时候，隐藏进度，自动弹出提示框
}
