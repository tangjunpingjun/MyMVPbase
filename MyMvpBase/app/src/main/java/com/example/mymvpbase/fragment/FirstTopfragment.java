package com.example.mymvpbase.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.example.mymvpbase.AppDeleger;
import com.example.mymvpbase.R;
import com.example.mymvpbase.adapter.FirstAdapter;
import com.example.mymvpbase.bean.javaBean;
import com.example.mymvpbase.presenter.FirstTopPresenter;
import com.example.mymvpbase.presenter.IFirstTopPresenter;
import com.example.mymvpbase.utils.SnackbarUtils;
import com.example.mymvpbase.view.ITopView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FirstTopfragment extends BaseFragment implements ITopView ,SwipeRefreshLayout.OnRefreshListener{

    private FirstAdapter mFirstAdapter;
    private int pageIndex=0;
    private List<javaBean> Data;
    private int type= FirstFragment.TOPFRAGMENT_ONE;
    private IFirstTopPresenter firstPresenter;


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private Context mContent;
    private LinearLayoutManager mLinearLayoutManager;

    public static FirstTopfragment newInstance(int type){
        FirstTopfragment fragment=new FirstTopfragment();
        Bundle bundle=new Bundle();
        bundle.putInt("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firstPresenter =new FirstTopPresenter(this);
        type = getArguments().getInt("type");
        LogUtils.i("onCreate"+type);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_top_fragment, null);
        ButterKnife.bind(this, view);
        mContent=getActivity();
        return view;

    }

    @Override
    public void onResume() {
        initView();
        onRefresh();
        super.onResume();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    private void initView() {
        mLinearLayoutManager=new LinearLayoutManager(mContent);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mContent).colorResId(R.color.colorAccent)
        .margin(16).showLastDivider().build());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFirstAdapter=new FirstAdapter(mContent);
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent,R.color.colorPrimaryDark);
        mSwipeRefresh.setOnRefreshListener(this);

        mRecyclerView.setAdapter(mFirstAdapter);
    }


    @Override
    public void hideProgress() {
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void showprogress() {
        mSwipeRefresh.setRefreshing(true);
    }

    //想 当于mSwipeRefresh.post 开始加载
    @Override
    public void AddDate(List<javaBean> mList) {
        if(null == Data){
            Data =new ArrayList<>();
        }
        Data.addAll(mList);
        if(pageIndex ==0 ){
            mFirstAdapter.setDate(Data);
        }else{
            if(mList==null || mList.size()==0){
                SnackbarUtils.Short( mRecyclerView,"没有数据了，不进行加载。");
            }
            mFirstAdapter.notifyDataSetChanged();
        }

        pageIndex += AppDeleger.PAZE_SIZE;

        LogUtils.e( pageIndex+":"+Data.size() );
    }

    @Override
    public void showLoadFail(Exception e) {
        View view = getActivity() == null ? mRecyclerView.getRootView() : getActivity().findViewById(R.id.drawer_layout);
//        Snackbar.make(view,"数据加载失败",Snackbar.LENGTH_SHORT).show();
        SnackbarUtils.Indefinite(mRecyclerView,"数据加载失败");

    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        pageIndex=0;
        if(null !=Data){
            //下拉刷新， 请清除以前的数据
            Data.clear();
        }
        LogUtils.i(pageIndex+"下拉刷新onRefresh"+type);
        firstPresenter.loadData(type,pageIndex);
    }
}
