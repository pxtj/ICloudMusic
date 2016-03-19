package com.example.pxtj.icloudmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pxtj.icloudmusic.R;
import com.example.pxtj.icloudmusic.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Porify on 2016/3/19.
 */
public class DiscoverFragment extends Fragment{
    private TabLayout discoverTabLayout;
    private ViewPager discoverViewPager;
    private List<View> discoverViewList;
    private List<String> discoverTitleList;

    private View discoverView;
    private View recommendView, musicListView, radioView, rankingListView;

    private MyPagerAdapter discoverPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        discoverView = inflater.inflate(R.layout.discover, container, false);
        initView(inflater);
        return discoverView;
    }

    public void initView(LayoutInflater inflater){
        discoverTabLayout = (TabLayout) discoverView.findViewById(R.id.discover_tab);
        discoverViewPager = (ViewPager) discoverView.findViewById(R.id.discover_view_pager);

        discoverViewList = new ArrayList<>();
        recommendView = inflater.inflate(R.layout.personal_recommand, null);
        musicListView = inflater.inflate(R.layout.music_list, null);
        radioView = inflater.inflate(R.layout.music_radio, null);
        rankingListView = inflater.inflate(R.layout.ranking_list, null);
        discoverViewList.add(recommendView);
        discoverViewList.add(musicListView);
        discoverViewList.add(radioView);
        discoverViewList.add(rankingListView);

        discoverTitleList = new ArrayList<>();
        discoverTitleList.add(getResources().getString(R.string.personal_recommendation));
        discoverTitleList.add(getResources().getString(R.string.music_list));
        discoverTitleList.add(getResources().getString(R.string.music_radio));
        discoverTitleList.add(getResources().getString(R.string.ranking_list));

        discoverPagerAdapter = new MyPagerAdapter(discoverViewList, discoverTitleList);
        discoverViewPager.setAdapter(discoverPagerAdapter);
        discoverTabLayout.setupWithViewPager(discoverViewPager);

    }

}
