package com.example.mymvpbase.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.apkfuns.logutils.LogUtils;
import com.example.mymvpbase.R;
import com.example.mymvpbase.bean.javaBean;
import com.example.mymvpbase.presenter.FirstDetilPersenter;
import com.example.mymvpbase.presenter.IFirstDetilPresenter;
import com.example.mymvpbase.utils.ImageLoaderUtils;
import com.example.mymvpbase.view.IDetilView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.mymvpbase.R.id.toolbar;

/**
 *  新闻的详情界面
 */

public class FirstDetilActivity extends AppCompatActivity implements IDetilView {
    private Context mContext;
    private javaBean mData;//详情数据
    private IFirstDetilPresenter mPresenter;

    @BindView(R.id.ivImage)
    ImageView mIvImage;
    @BindView(toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.htNewsContent)
    HtmlTextView mHtNewsContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_detils);
        ButterKnife.bind(this);
        mContext=this;
        InitView();
    }

    /**
     *  初始化
     */
    private void InitView() {
        if (Build.VERSION.SDK_INT >= 21) {
            //使用了SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN和SYSTEM_UI_FLAG_LAYOUT_STABLE，
            // 注意两个Flag必须要结合在一起使用，表示会让应用的主体内容占用系统状态栏的空间，
            // 最后再调用Window的setStatusBarColor()方法将状态栏设置成透明色就可以了。
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN  //全屏
                    |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;  //
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
        setSupportActionBar(mToolbar);
        // 给左上角图标的左边加上一个返回的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //通过 NavigationDrawer 打开关闭 抽屉---返回
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e(" 返回上一级");
                onBackPressed();//返回上一级
            }
        });

        mData= (javaBean) getIntent().getSerializableExtra("news");
        mCollapsingToolbar.setTitle(mData.getTitle());
        ImageLoaderUtils.display(mContext,mIvImage,mData.getImgsrc());
        mPresenter=new FirstDetilPersenter(mContext, this);
        mPresenter.LoadContent(mData.getDocid());
    }



    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showprogress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetilContent(String s) {
        mHtNewsContent.setHtml(s );
//        mHtNewsContent.setHtml(s , new HtmlHttpImageGetter( mHtNewsContent));

    }

    @Override
    public void showLoadFail(String s, Exception e) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
