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
import com.example.pxtj.icloudmusic.adapter.TabContentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Porify on 2016/3/19.
 */
public class DiscoverFragment extends Fragment{
    private TabLayout discoverTabLayout;
    private ViewPager discoverViewPager;
    private List<Fragment> discoverFragmentList;
    private List<String> discoverTitleList;

    private View discoverView;
    private Fragment recommendFragment, musicListFragment, radioFragment, rankingListFragment;

    private TabContentPagerAdapter discoverPagerAdapter;

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

        discoverFragmentList = new ArrayList<>();
        recommendFragment = new PersonalRecommendFragment();
        musicListFragment = new MusicListFragment();
        radioFragment = new AnchorRadioFragment();
        rankingListFragment = new RankingListFragment();
        discoverFragmentList.add(recommendFragment);
        discoverFragmentList.add(musicListFragment);
        discoverFragmentList.add(radioFragment);
        discoverFragmentList.add(rankingListFragment);

        discoverTitleList = new ArrayList<>();
        discoverTitleList.add(getResources().getString(R.string.personal_recommendation));
        discoverTitleList.add(getResources().getString(R.string.music_list));
        discoverTitleList.add(getResources().getString(R.string.music_radio));
        discoverTitleList.add(getResources().getString(R.string.ranking_list));

        discoverPagerAdapter = new TabContentPagerAdapter(getActivity().getSupportFragmentManager(), discoverFragmentList, discoverTitleList);
        discoverViewPager.setAdapter(discoverPagerAdapter);
        discoverTabLayout.setupWithViewPager(discoverViewPager);

    }

}
