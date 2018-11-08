package com.example.tokgank.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> mList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapter(FragmentManager fm,List<Fragment> fragments,List<String> mList){
        super(fm);
        this.fragments = fragments;
        this.mList = mList;
    };

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position%mList.size());
    }


    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

}
