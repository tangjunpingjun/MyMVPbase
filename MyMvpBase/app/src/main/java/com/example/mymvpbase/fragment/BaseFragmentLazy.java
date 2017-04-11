package com.example.mymvpbase.fragment;

import android.support.v4.app.Fragment;

import com.apkfuns.logutils.LogUtils;

/**
 * 延迟加载的fragment
 */
public abstract class BaseFragmentLazy extends Fragment {

    /** Fragment当前状态是否可见 */
    protected boolean isVisible;
    /** 标志位，标志已经初始化完成 */
    protected boolean isPrepared;
    /** 是否已被加载过一次，第二次就不再去请求数据了 */
    protected boolean mHasLoadedOnce;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 初始化完成时数据
     *  就加载数据 延迟加载
     */

    public void initView(){
        isPrepared=true;
        LogUtils.e("初始化完成时数据initView");
        lazyLoad();
    }

    protected void onInvisible(){

    }
    /**
     * 可见
     */
    protected void onVisible(){
        lazyLoad();
    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();
}
