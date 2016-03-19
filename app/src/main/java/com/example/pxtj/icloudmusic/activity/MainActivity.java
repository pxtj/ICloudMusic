package com.example.pxtj.icloudmusic.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.pxtj.icloudmusic.R;
import com.example.pxtj.icloudmusic.adapter.TabContentPagerAdapter;
import com.example.pxtj.icloudmusic.fragment.DiscoverFragment;
import com.example.pxtj.icloudmusic.fragment.FriendsFragment;
import com.example.pxtj.icloudmusic.fragment.MusicFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    private ViewPager topToolbarViewPager;
    private TabLayout topToolbarTablayout;
    private List<Fragment> topToolbarTabFragmentList;
    private List<String> topToolbarTabTitleList;

    private DiscoverFragment discoverFragment;
    private MusicFragment musicFragment;
    private FriendsFragment friendsFragment;

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
        initTopToolbarTabFragment();
        initTopToolbarTabTitle();
        TabContentPagerAdapter topToolbarPagerAdapter = new TabContentPagerAdapter(getSupportFragmentManager(),
                topToolbarTabFragmentList, topToolbarTabTitleList);
        topToolbarViewPager.setAdapter(topToolbarPagerAdapter);
        topToolbarTablayout.setupWithViewPager(topToolbarViewPager);
//        topToolbarTablayout.setSelectedTabIndicatorColor(getColor(android.R.color.));

    }

    private void initToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        //设置Menu的监听支持：
        setSupportActionBar(toolbar);
        //去掉标题栏
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    public void initTopToolbarTabFragment(){
        topToolbarTabFragmentList = new ArrayList<>();
        discoverFragment = new DiscoverFragment();
        musicFragment = new MusicFragment();
        friendsFragment = new FriendsFragment();
        topToolbarTabFragmentList.add(discoverFragment);
        topToolbarTabFragmentList.add(musicFragment);
        topToolbarTabFragmentList.add(friendsFragment);
    }

    public void initTopToolbarTabTitle(){

        topToolbarTabTitleList = new ArrayList<>();
        topToolbarTabTitleList.add("fx");
        topToolbarTabTitleList.add("yy");
        topToolbarTabTitleList.add("py");
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
