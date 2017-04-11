package com.example.mymvpbase.view;

import android.support.v4.app.Fragment;

/**
 * Main View 的接口
 */
public interface IMainView {

//    void switchFirst(); //显示第一个viewPager
//
//    void switchTwo();
    //更换fragment
    Fragment switchContent(int id);
}
