package com.example.mymvpbase.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.example.mymvpbase.AppDeleger;
import com.example.mymvpbase.bean.javaBean;
import com.example.mymvpbase.fragment.FirstFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by junping on 2017/3/17.
 */
public class TopModel  implements ITopModel{

    @Override
    public void loadDate(String url, final int type, final onloadFirstList list) {
        LogUtils.e("loadDate"+url);
        OkGo.get( url )     // 请求方式和请求url
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        // s 即为所需要的结果
                        LogUtils.e(type+":onSuccess:"+response.toString());
                        JSONObject jsonObject=JSON.parseObject(s);
                        String str="";
                        switch (type){
                            case FirstFragment.TOPFRAGMENT_ONE:
                                str=AppDeleger.TOP_ID;
                                break;
                            case FirstFragment.TOPFRAGMENT_TWO:
                                str=AppDeleger.NBA_ID;
                                break;
                            case FirstFragment.TOPFRAGMENT_THREE:
                                str=(AppDeleger.CAR_ID);
                                break;
                            case FirstFragment.TOPFRAGMENT_FOUR:
                                str=(AppDeleger.JOKE_ID);
                                break;
                        }
                        JSONArray sd=jsonObject.getJSONArray(str);
                        List<javaBean> beanlist= JSON.parseArray(sd.toString(),javaBean.class);
                        list.onSuccess(beanlist);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        list.onFailure("qqcw",e);
                        super.onError(call, response, e);
                    }
                });
    }


}
