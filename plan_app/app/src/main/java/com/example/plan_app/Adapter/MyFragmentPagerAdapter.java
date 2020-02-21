package com.example.plan_app.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.plan_app.fragment.fragment1;
import com.example.plan_app.fragment.fragment2;
import com.example.plan_app.fragment.fragment3;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"Daily Routine", "Week Plan", "Master Plan"};
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public Fragment getItem(int position) {
        if (position == 1) {
            return new fragment2();
        } else if (position == 2) {
            return new fragment3();
        }
        return new fragment1();
    }
    public int getCount() {
        return mTitles.length;
    }
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
