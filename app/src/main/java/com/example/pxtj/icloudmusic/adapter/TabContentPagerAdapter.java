package com.example.pxtj.icloudmusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Porify on 2016/3/19.
 */
public class TabContentPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragmentList;
    private List<String> stringList;

    public TabContentPagerAdapter(FragmentManager fragmentManager){
        this(fragmentManager, null, null);
    }
    public  TabContentPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList){
        this(fragmentManager, fragmentList, null);
    }
    public TabContentPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList, List<String> stringList){
        super(fragmentManager);
        this.fragmentList = fragmentList;
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (stringList != null){
            return stringList.get(position);
        }
        return null;
    }


}
