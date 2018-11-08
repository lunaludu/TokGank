package com.example.tokgank;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.example.tokgank.adapter.ViewPagerAdapter;
import com.example.tokgank.base.BaseActivity;
import com.example.tokgank.module.pic.PictureFragment;
import com.example.tokgank.module.web.NoScrollViewPager;
import com.example.tokgank.ui.MetroFragment;
import com.example.tokgank.ui.NewFragment;
import com.example.tokgank.ui.VedioFragment;
import com.example.tokgank.widget.BottomNavigationViewHelper;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.viewpager)
    NoScrollViewPager viewPager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    private MenuItem menuItem;
    private ViewPagerAdapter adapter;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
//        getSupportActionBar().hide();
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_news:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.item_lib:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.item_find:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.item_more:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });



        adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NewFragment());
        adapter.addFragment(new PictureFragment());
        adapter.addFragment(new VedioFragment());
        adapter.addFragment(new MetroFragment());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
    }

}