package com.example.mymvpbase.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymvpbase.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */

public class FirstFragment extends BaseFragment {

    public  static final int  TOPFRAGMENT_ONE=0;
    public static final int  TOPFRAGMENT_TWO=1;
    public static final int  TOPFRAGMENT_THREE=2;
    public static final int  TOPFRAGMENT_FOUR=3;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private MypagerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container,false);
        ButterKnife.bind(this, view);
        initViewPager();
        return view;
    }

    private void initViewPager() {
        mAdapter=new MypagerAdapter(getChildFragmentManager());
        mAdapter.addFragment(FirstTopNewFragment.newInstance(TOPFRAGMENT_ONE),"头条");
        mAdapter.addFragment(FirstTopNewFragment.newInstance(TOPFRAGMENT_TWO),"NBA");
        mAdapter.addFragment(FirstTopNewFragment.newInstance(TOPFRAGMENT_THREE),"汽车");
        mAdapter.addFragment(FirstTopNewFragment.newInstance(TOPFRAGMENT_FOUR),"笑话");

        mViewpager.setOffscreenPageLimit(1);
        mTabLayout.addTab(mTabLayout.newTab().setText("头条"));
        mTabLayout.addTab(mTabLayout.newTab().setText("NBA"));
        mTabLayout.addTab(mTabLayout.newTab().setText("汽车"));
        mTabLayout.addTab(mTabLayout.newTab().setText("笑话"));
        mViewpager.setAdapter(mAdapter);

        mTabLayout.setupWithViewPager(mViewpager);

    }


    public static class MypagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList=new ArrayList<>();
        private final List<String> mTitleString= new ArrayList<>();

        public MypagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        public void addFragment(Fragment f, String title){
            mFragmentList.add(f);
            mTitleString.add(title);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleString.get(position);
        }

    }


    ///没有下面的代码会出现切换fragment的时候重影现象：
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }
}
