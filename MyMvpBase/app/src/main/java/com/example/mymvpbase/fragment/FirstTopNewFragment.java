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
import com.example.mymvpbase.adapter.MianFirstAdapter;
import com.example.mymvpbase.bean.Model;
import com.example.mymvpbase.bean.javaBean;
import com.example.mymvpbase.presenter.FirstTopPresenter;
import com.example.mymvpbase.presenter.IFirstTopPresenter;
import com.example.mymvpbase.utils.ToastUtil;
import com.example.mymvpbase.view.ITopView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FirstTopNewFragment extends BaseFragmentLazy implements ITopView , SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private LinearLayoutManager mlayoutManager;
    private IFirstTopPresenter mPresenter;
    private int inttype=FirstFragment.TOPFRAGMENT_ONE;
    private MianFirstAdapter mAdapter;
    private int pageIndex=0;
    private Context mContext;
    private List<Model> detaList;

    private int lastVisibleItem; //最后的item
    private boolean isLoading; //是否整在加载
    private boolean isFootGone;


    public static FirstTopNewFragment newInstance(int type) {
        Bundle args = new Bundle();
        FirstTopNewFragment fragment = new FirstTopNewFragment();
        args.putInt("type",type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=new FirstTopPresenter(this);
        inttype = getArguments().getInt("type");
        LogUtils.i("onCreate: "+inttype);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_top_fragment, container,false);
        ButterKnife.bind(this, view);
        mContext=getActivity();
        initView();
//        onRefresh();
        return view;
    }

    @Override
    public void initView() {
        super.initView();
        mSwipeRefresh.setColorSchemeResources(R.color.cardview_light_background,R.color.cardview_shadow_end_color);
        mSwipeRefresh.setOnRefreshListener( this);
        mlayoutManager=new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mlayoutManager);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mContext)
                .colorResId(R.color.white)
                .size(26).showLastDivider().build());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter=new MianFirstAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        LogUtils.e("initView");
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(mAdapter!=null && lastVisibleItem + 1 == mAdapter.getItemCount() &&
                    newState==RecyclerView.SCROLL_STATE_IDLE && !isLoading && !isFootGone){
                    isLoading=true;
                    mAdapter.addFoot ();
                    isFootGone=true;
                    //滑动到底部
                    mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount());
                    mPresenter.loadData(inttype,pageIndex);
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                lastVisibleItem=mlayoutManager.findLastVisibleItemPosition();
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }


    //zai initeView之后
    @Override
    protected void lazyLoad() {
//        LogUtils.e(isPrepared+"lazyLoad"+isVisible+":"+mHasLoadedOnce+inttype);
        if(!isPrepared||!isVisible||mHasLoadedOnce){
            return;
        }
        onRefresh();
    }



    @Override
    public void hideProgress() {
        mSwipeRefresh.setRefreshing(false);
        isLoading=false;
        if(isFootGone){
            mAdapter.remoreFoot();
            isFootGone=false;
        }
    }

    @Override
    public void showprogress() {
        mSwipeRefresh.setRefreshing(true);
    }

    @Override
    public void AddDate(List<javaBean> mList) {
        if(null==detaList){
            detaList = new ArrayList<>();
        }

        detaList.addAll(mList);
        if(pageIndex == 0){
            mAdapter.setData(detaList);
        }else{
            if(mList.size()==0||mList==null){
                mAdapter.addButtom();
                mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount());
                isFootGone=true;
                return ;
            }
            mAdapter.notifyDataSetChanged();
        }
        pageIndex += AppDeleger.PAZE_SIZE;
        mHasLoadedOnce=true;
    }

    @Override
    public void showLoadFail(Exception e) {
        ToastUtil.showToast(mContext,e.toString());
    }

    //这是在swipe 中的刷新
    @Override
    public void onRefresh() {
        pageIndex=0;
        isLoading=true;
        isFootGone=false;
        if(null !=detaList){
            //下拉刷新， 请清除以前的数据
            detaList.clear();
        }
        LogUtils.i(pageIndex+"下拉刷新onRefresh"+inttype);
        mPresenter.loadData(inttype,pageIndex);
    }


    @Override
    public void onDestroyView() {
        mHasLoadedOnce=false;
        LogUtils.e("onDestroyView"+inttype);
        super.onDestroyView();

    }
}
