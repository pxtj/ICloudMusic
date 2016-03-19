package com.example.pxtj.icloudmusic.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pxtj.icloudmusic.adapter.MyPagerAdapter;
import com.example.pxtj.icloudmusic.R;

import java.util.ArrayList;
import java.util.List;

public class CloudMusicActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    private ViewPager topToolbarViewPager;
    private TabLayout topToolbarTablayout;
    private List<View> topToolbarTabViewList;
    private List<String> topToolbarTabTitleList;

    private TabLayout discoverTabLayout;
    private ViewPager discoverViewPager;
    private List<View> discoverViewList;
    private List<String> discoverTitleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cloud_music);

        initToolbar();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CoordinatorLayout musicLayout = (CoordinatorLayout) findViewById(R.id.app_bar_cloud_music);

        //top toolbar tab layout:
        topToolbarViewPager = (ViewPager)findViewById(R.id.top_toolbar_tab_view_pager);
        topToolbarTablayout = (TabLayout)findViewById(R.id.top_toolbar_tab);
        initTopToolbarTabView();
        initTopToolbarTabTitle();
        MyPagerAdapter topToolbarPagerAdapter = new MyPagerAdapter(topToolbarTabViewList, topToolbarTabTitleList);
        topToolbarViewPager.setAdapter(topToolbarPagerAdapter);
        topToolbarTablayout.setupWithViewPager(topToolbarViewPager);


        //discover page:
        discoverTabLayout = (TabLayout)topToolbarTabViewList.get(0).findViewById(R.id.discover_tab);
        discoverViewPager = (ViewPager)topToolbarTabViewList.get(0).findViewById(R.id.discover_view_pager);
        initDiscoverTabView();
        initDiscoverTabTitle();
        MyPagerAdapter discoverPagerAdapter = new MyPagerAdapter(discoverViewList, discoverTitleList);
        Log.e("wrp", "discoverPagerAdapter: " + discoverPagerAdapter.toString());
        discoverViewPager.setAdapter(discoverPagerAdapter);
        discoverTabLayout.setupWithViewPager(discoverViewPager);



    }

    private void initToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        //设置Menu的监听支持：
        setSupportActionBar(toolbar);
        //去掉标题栏
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    public void initTopToolbarTabView(){
        topToolbarTabViewList = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        View discoverView = inflater.inflate(R.layout.discover, null);
        View musicView = inflater.inflate(R.layout.music, null);
        View friendsView = inflater.inflate(R.layout.friends, null);
        topToolbarTabViewList.add(discoverView);
        topToolbarTabViewList.add(musicView);
        topToolbarTabViewList.add(friendsView);
    }

    public void initTopToolbarTabTitle(){

        topToolbarTabTitleList = new ArrayList<>();
        topToolbarTabTitleList.add("fx");
        topToolbarTabTitleList.add("yy");
        topToolbarTabTitleList.add("py");
    }

    public void initDiscoverTabView(){
        discoverViewList = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();

        View personalView = inflater.inflate(R.layout.personal_recommand, null);
        View musicListView = inflater.inflate(R.layout.music_list, null);
        View musicRadioView = inflater.inflate(R.layout.music_radio, null);
        View rankingListView = inflater.inflate(R.layout.ranking_list, null);

        discoverViewList.add(personalView);
        discoverViewList.add(musicListView);
        discoverViewList.add(musicRadioView);
        discoverViewList.add(rankingListView);
    }

    public void initDiscoverTabTitle(){
        discoverTitleList = new ArrayList<>();
        discoverTitleList.add(getResources().getString(R.string.personal_recommendation));
        discoverTitleList.add(getResources().getString(R.string.music_list));
        discoverTitleList.add(getResources().getString(R.string.music_radio));
        discoverTitleList.add(getResources().getString(R.string.ranking_list));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cloud_music, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_message) {
            // Handle the camera action
        } else if (id == R.id.score_market) {

        } else if (id == R.id.pay_music) {

        } else if (id == R.id.online_music_non_use_data) {

        } else if (id == R.id.music_recognition) {

        } else if (id == R.id.theme_skin) {

        } else if (id == R.id.night_mode) {

        } else if (id == R.id.time_stop_play) {

        } else if (id == R.id.my_music_cloud) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
