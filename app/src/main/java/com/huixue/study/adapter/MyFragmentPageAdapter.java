package com.huixue.study.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/6/3.
 * 自定义适配器
 */
public class MyFragmentPageAdapter extends FragmentPagerAdapter{
        private List<Fragment> list;
    public MyFragmentPageAdapter(FragmentManager fm,List<Fragment> list){
        super(fm);
        this.list = list;


    }
 /*写好返回值*/
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
