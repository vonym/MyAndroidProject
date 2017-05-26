package com.example.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by vonym on 17-1-3.
 */

public class MainVpAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public MainVpAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainVpAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    /**
     * 返回每个界面的Fragment对象
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    /**
     * 返回Fragment的数量
     *
     * @return
     */
    @Override
    public int getCount() {
        return mFragments.size();
    }
}
