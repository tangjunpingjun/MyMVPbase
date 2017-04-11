package com.example.mymvpbase.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.example.mymvpbase.R;
import com.example.mymvpbase.fragment.FirstFragment;


public class FragmentFactory extends FragmentPagerAdapter {
    protected Context mContext;

    public FragmentFactory(FragmentManager fm ,Context context ) {
        super(fm);
        this.mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case R.id.nav_camera:
                fragment=new FirstFragment();
                break;
            case R.id.nav_gallery:
                fragment=new FirstFragment();
                break;
//            case R.id.nav_slideshow:
//                ToastUtil.showToast(mContext,"hhhh");
//                break;
//            case R.id.nav_manage:
//                ToastUtil.showToast(mContext,"www");
//                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
