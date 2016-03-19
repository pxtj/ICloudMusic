package com.example.pxtj.icloudmusic.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Porify on 2016/3/18.
 */
public class MyPagerAdapter extends PagerAdapter{
    List<View> viewList;
    List<String> titleList;

    public MyPagerAdapter(List<View> viewList){
        this(viewList, null);
    }
    public MyPagerAdapter(List<View> viewList, List<String> titleList) {
        this.viewList = viewList;
        this.titleList = titleList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titleList != null){
            return titleList.get(position);
        }
        return null;
    }
}
