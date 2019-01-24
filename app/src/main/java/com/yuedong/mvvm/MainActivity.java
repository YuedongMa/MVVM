package com.yuedong.mvvm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yuedong.base.util.ToastUtil;
import com.yuedong.down.DownLoadManager;
import com.yuedong.mvvm.base.BaseActivity;
import com.yuedong.mvvm.ui.home.HomeFragment;
import com.yuedong.mvvm.ui.home.adapter.SlideFragmentAdapter;
import com.yuedong.mvvm.ui.message.MessageFragment;
import com.yuedong.mvvm.ui.my.MyFragment;
import com.yuedong.view.bottombar.W_BottomBar;
import com.yuedong.view.bottombar.W_BottomBarTab;
import com.yuedong.view.bottombar.W_ViewPager;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    @BindView(R.id.viewPager)
    W_ViewPager viewPager;
    @BindView(R.id.bottomBar)
    W_BottomBar bottomBar;

    private Fragment[] fragments = {new HomeFragment(), new MessageFragment(), new MyFragment()};
    private int[] icon = {R.mipmap.ic_message_white_24dp, R.mipmap.ic_message_white_24dp, R.mipmap.ic_message_white_24dp};
    private String[] title = {"首页", "消息", "我的"};

    @Override
    public int initLayout(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle bundle) {
        initBottom();
//        viewModel.getVersion().observe(this, new Observer<VersionBean>() {
//            @Override
//            public void onChanged(@Nullable VersionBean versionBean) {
//                DownLoadManager.getInstance(R.mipmap.logo).defaultDownLoad(MainActivity.this,versionBean.getClient_tips(),versionBean.getClient_force_update(),versionBean.getClient_url());
//
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            ToastUtil.showToast("再次点击返回键退出");
        }
        mBackPressed = System.currentTimeMillis();
    }

    @Override
    public void netTry(Bundle bundle) {
        super.netTry(bundle);
       // viewModel.getVersion().setValue(new VersionBean());
    }

    @Override
    public void onActivityDestroy() {
        super.onActivityDestroy();
        DownLoadManager.getInstance().bindService(this);
        DownLoadManager.getInstance().unBindService(this);
    }

    private void initBottom() {
        viewPager.setOffscreenPageLimit(title.length);
        for (int i = 0; i < fragments.length; i++) {
            bottomBar.addItem(new W_BottomBarTab(this, icon[i], title[i], fragments[i]));
        }
        viewPager.setCanSlide(false);
        viewPager.setCanSmooth(false);
        viewPager.setAdapter(new SlideFragmentAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomBar.setCurrentItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomBar.setOnTabSelectedListener(new W_BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                viewPager.setCurrentItem(position);


            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
}
