package com.example.tokgank.ui;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.tokgank.R;
import com.example.tokgank.adapter.ViewPagerAdapter;
import com.example.tokgank.base.BaseFragment;
import com.example.tokgank.config.Config;
import com.example.tokgank.module.category.CategoryFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Hugh on 2018/4/27.
 */

public class NewFragment extends BaseFragment {

    private String[] tabs = new String[]{Config.CATEGORY_APP,Config.CATEGORY_ANDROID,Config.CATEGORY_IOS,Config.CATEGORY_FRONT,Config.CATEGORY_RECOMMEND,Config.CATEGORY_SOURE};

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    ViewPagerAdapter viewPagerAdapter;


    @Override
    protected boolean getUserVis() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void init() {

        List<Fragment> fragments = new ArrayList<>();
        List<String> mList = new ArrayList<>();

        for (int i = 0;i < tabs.length;i++){
            Fragment fragment = CategoryFragment.newInstance(tabs[i]);
            fragments.add(fragment);
            mList.add(tabs[i]);
            Log.d("HomeActivity",tabs[i]);
        }
        viewPagerAdapter=new ViewPagerAdapter(getChildFragmentManager(),fragments,mList);

        mViewPager.setCurrentItem(1);
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(viewPagerAdapter);
    }

}
