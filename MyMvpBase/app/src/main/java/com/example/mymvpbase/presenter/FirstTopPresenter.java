package com.example.mymvpbase.presenter;

import com.example.mymvpbase.AppDeleger;
import com.example.mymvpbase.bean.javaBean;
import com.example.mymvpbase.fragment.FirstFragment;
import com.example.mymvpbase.model.ITopModel;
import com.example.mymvpbase.model.TopModel;
import com.example.mymvpbase.view.ITopView;

import java.util.List;

/**
 * 逻辑层
 */

public class FirstTopPresenter implements IFirstTopPresenter ,ITopModel.onloadFirstList{
    private ITopView mTopView;
    private ITopModel mModel;


    public FirstTopPresenter(ITopView topView) {
        this.mTopView = topView;
        mModel=new TopModel();
    }

    @Override
    public void loadData(Object type, int pager) {
        String  url= getUrl((Integer) type,pager);
        //如果是开始就加载，所以 要有转圈
        if(pager==0){
            mTopView.showprogress();
        }
        mModel.loadDate(url, (Integer) type,this);
    }

    //获取url
    private String getUrl(int type, int pager) {
        StringBuilder sb=new StringBuilder();
        switch (type){
            case FirstFragment.TOPFRAGMENT_ONE:
                sb.append(AppDeleger.TOP_URL).append(AppDeleger.TOP_ID);
                break;
            case FirstFragment.TOPFRAGMENT_TWO:
                sb.append(AppDeleger.COMMON_URL).append(AppDeleger.NBA_ID);
                break;
            case FirstFragment.TOPFRAGMENT_THREE:
                sb.append(AppDeleger.COMMON_URL).append(AppDeleger.CAR_ID);
                break;
            case FirstFragment.TOPFRAGMENT_FOUR:
                sb.append(AppDeleger.COMMON_URL).append(AppDeleger.JOKE_ID);
                break;
            default:
                sb.append(AppDeleger.TOP_URL).append(AppDeleger.TOP_ID);
                break;
        }
        //pager 是从第几页到
        sb.append("/").append(pager).append(AppDeleger.END_URL);
        return sb.toString();
    }


    //model 回调来的 /

    @Override
    public void onSuccess(List<javaBean> list) {
        mTopView.hideProgress();
        mTopView.AddDate(list);
    }

    @Override
    public void onFailure(String str, Exception e) {
        mTopView.hideProgress();
        mTopView.showLoadFail(e);//错误提示
    }
}
