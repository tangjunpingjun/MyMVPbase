package com.example.mymvpbase;

import android.app.Activity;
import android.app.Application;

import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.squareup.leakcanary.LeakCanary;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by junping on 2017/3/17.
 */

public class MyApplication extends Application {

    private List<Activity> activityList = new LinkedList<>();
    private static MyApplication instance; //单列模式

    public static MyApplication getInstance(){
        if(null == instance){
            instance=new MyApplication();
        }
        return instance;
    }

    public void addActivity(Activity activity){
        activityList.add(activity);
    }
    /**
     * 遍历所有Activity并finish，但是不关闭应用
     * （这个方法主要是用于 修改密码和退出登录后 跳转登陆界面，需关闭掉被addActivity（Activity activity）方法添加进activityList的Activity）
     * 不然按返回键会回到之前界面
     */
    public void exitActivity(){
        for(Activity activity :activityList){
            activity.finish();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getLogConfig() //选项配置
                .configAllowLog(true)  // 配置日志是否输出
                .configTagPrefix("myapp") //// 配置日志前缀
                .configShowBorders(true) //显示边界
                .configFormatTag("%d{HH:mm:ss:SSS} %t %caller{-5}") //标签格式
                .configLevel(LogLevel.TYPE_VERBOSE); //显示

        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this); //注册 leakCanary 内存检查

        OkGo.init(this);
        OkGo.getInstance()
                // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                .debug("OkGo", Level.INFO, true)

                //如果使用默认的 60秒,以下三行也不需要传
                .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间

                //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                .setCacheMode(CacheMode.NO_CACHE);

                //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
//                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
//                .setRetryCount(3)

                //如果不想让框架管理cookie（或者叫session的保持）,以下不需要
//              .setCookieStore(new MemoryCookieStore())            //cookie使用内存缓存（app退出后，cookie消失）
//                .setCookieStore(new PersistentCookieStore())        //cookie持久化存储，如果cookie不过期，则一直有效

                //可以设置https的证书,以下几种方案根据需要自己设置
//                .setCertificates()                                  //方法一：信任所有证书,不安全有风险
//              .setCertificates(new SafeTrustManager())            //方法二：自定义信任规则，校验服务端证书
//              .setCertificates(getAssets().open("srca.cer"))      //方法三：使用预埋证书，校验服务端证书（自签名证书）
//              //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
//               .setCertificates(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"))//

                //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
//               .setHostnameVerifier(new SafeHostnameVerifier())

                //可以添加全局拦截器，不需要就不要加入，错误写法直接导致任何回调不执行
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return chain.proceed(chain.request());
//                    }
//                })

                //这两行同上，不需要就不要加入
//                .addCommonHeaders(headers)  //设置全局公共头
//                .addCommonParams(params);   //设置全局公共参数



    }
}
