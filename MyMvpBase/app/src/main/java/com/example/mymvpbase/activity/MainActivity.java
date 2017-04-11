package com.example.mymvpbase.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mymvpbase.R;
import com.example.mymvpbase.utils.SnackbarUtils;
import com.example.mymvpbase.utils.ToastUtil;
import com.example.mymvpbase.view.IMainView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements IMainView {
    private Context mContext;
    private ActionBarDrawerToggle mToggle;
    private FragmentManager mFragmentManager;
    private FragmentFactory mfactory;
    private MenuItem mLastMenuItem;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fragment)
    FrameLayout mFragment;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext=this;
        setSupportActionBar(mToolbar);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SnackbarUtils.Long(view,"Replace with your own action");
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState(); //初始化
        initView();
    }

    private void initView() {
        mFragmentManager =getSupportFragmentManager();
        mfactory=new FragmentFactory(mFragmentManager,mContext);
        switchContent(R.id.nav_camera);
        mLastMenuItem=mNavView.getMenu().findItem(R.id.nav_camera);
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    switch (item.getItemId()) {
                        case R.id.nav_camera:
                            switchContent(R.id.nav_camera);
                            mToolbar.setTitle("新闻");
                            break;
                        case R.id.nav_gallery:
                            switchContent(R.id.nav_gallery);
                            mToolbar.setTitle("精选");
                            break;
//                        case R.id.nav_slideshow:
//                            switchContent(R.id.nav_slideshow);
//                            mToolbar.setTitle("网易");
//                            break;
//                        case R.id.nav_manage:
//                            switchContent(R.id.nav_manage);
//                            mToolbar.setTitle("mToolbar");
//                            break;
                    }
                    mLastMenuItem = item;
                    item.setChecked(true);
                    return true;
                }
        });
    }

    @Override
    public Fragment switchContent(int id) {
        //instantiateItem从FragmentManager中查找Fragment，找不到就getItem新建一个，
        Fragment fragment= (Fragment) mfactory.instantiateItem(mFragment,id);
        mfactory.setPrimaryItem(mFragment, 0, fragment);
        //最后finishUpdate提交事务。
        mfactory.finishUpdate(mFragment);
        return fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            ToastUtil.showToast(mContext," 菜单中的设置。");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            _exit();
        }
    }

    long mExitTime=0;

    /**
     * 退出
     */
    private void _exit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }



}
