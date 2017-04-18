package com.example.mymvpbase.presenter;

import android.content.Context;

import com.example.mymvpbase.bean.DataDetilBean;
import com.example.mymvpbase.model.DetilModel;
import com.example.mymvpbase.model.IDetilModel;
import com.example.mymvpbase.view.IDetilView;

/**
 * Created by junping on 2017/4/17.
 */

public class FirstDetilPersenter implements IFirstDetilPresenter , IDetilModel.OnloadFirstDataDetilListener {
    private Context mContent;
    private IDetilView mView;
    private IDetilModel mModel;

    public FirstDetilPersenter(Context content ,IDetilView view) {
        this.mContent= content;
        this.mView = view;
        mModel= new DetilModel();
    }



    @Override
    public void LoadContent(String s) {
        mView.showprogress();
        mModel.loadDetilData(s,  this);
    }



    @Override
    public void onSuccess(DataDetilBean s) {
        if(s !=null){
            mView.showDetilContent(s.getBody());
        }
        mView.hideProgress();
    }

    @Override
    public void onFailure(String str, Exception e) {
       mView.hideProgress();
        mView.showLoadFail(str,e);
    }
}
