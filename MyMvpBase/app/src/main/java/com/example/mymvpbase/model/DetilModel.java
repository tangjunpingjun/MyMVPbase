package com.example.mymvpbase.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.example.mymvpbase.AppDeleger;
import com.example.mymvpbase.bean.DataDetilBean;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by junping on 2017/4/18.
 */

public class DetilModel implements IDetilModel {

    @Override
    public void loadDetilData(final String url, final OnloadFirstDataDetilListener listener) {
        String detailUrl = getDetailUrl(url);
        LogUtils.e("loadDate"+detailUrl);
        OkGo.get(detailUrl).tag(this).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                // s 即为所需要的结果
//                LogUtils.e(type+":onSuccess:"+response.toString());
                JSONObject jsonObject= JSON.parseObject(s);

                DataDetilBean bean= JSON.parseObject(jsonObject.get(url).toString() , DataDetilBean.class );
                listener.onSuccess( bean );
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                listener.onFailure("qqcw",e);
                super.onError(call, response, e);
            }
        });
    }

    private String getDetailUrl(String url) {
        StringBuffer sb = new StringBuffer(AppDeleger.NEW_DETAIL);
        sb.append(url).append(AppDeleger.END_DETAIL_URL);
        return sb.toString();
    }

}
