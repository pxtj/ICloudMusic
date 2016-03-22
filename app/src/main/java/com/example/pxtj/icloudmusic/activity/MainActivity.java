package com.example.pxtj.icloudmusic.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pxtj.icloudmusic.R;
import com.example.pxtj.icloudmusic.adapter.TabContentPagerAdapter;
import com.example.pxtj.icloudmusic.fragment.DiscoverFragment;
import com.example.pxtj.icloudmusic.fragment.FriendsFragment;
import com.example.pxtj.icloudmusic.fragment.MusicFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Toolbar toolbar;

    private ViewPager topToolbarViewPager;
    private TabLayout topToolbarTablayout;
    private List<Fragment> topToolbarTabFragmentList;
    private List<String> topToolbarTabTitleList;

    private DiscoverFragment discoverFragment;
    private MusicFragment musicFragment;
    private FriendsFragment friendsFragment;

    private ImageView playBarPlayList;
    private ImageView playBarPlay;
    private ImageView playBarNext;

    private boolean playBarPlayState = false;

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

        TextView navExit = (TextView)navigationView.findViewById(R.id.nav_exit);
        TextView navSettings = (TextView)navigationView.findViewById(R.id.nav_setting);
        navExit.setOnClickListener(this);
        navSettings.setOnClickListener(this);

        //top toolbar tab layout:
        topToolbarViewPager = (ViewPager)findViewById(R.id.top_toolbar_tab_view_pager);
        topToolbarTablayout = (TabLayout)findViewById(R.id.top_toolbar_tab);
        initTopToolbarTabFragment();
        initTopToolbarTabTitle();
        TabContentPagerAdapter topToolbarPagerAdapter = new TabContentPagerAdapter(getSupportFragmentManager(),
                topToolbarTabFragmentList, topToolbarTabTitleList);
//        TabContentPagerAdapter topToolbarPagerAdapter = new TabContentPagerAdapter(getSupportFragmentManager(),
//                topToolbarTabFragmentList, null);
        topToolbarViewPager.setAdapter(topToolbarPagerAdapter);

//        topToolbarTablayout.addTab(topToolbarTablayout.newTab().setIcon(R.drawable.actionbar_discover_normal));
//        topToolbarTablayout.addTab(topToolbarTablayout.newTab().setIcon(R.drawable.actionbar_music_normal));
//        topToolbarTablayout.addTab(topToolbarTablayout.newTab().setIcon(R.drawable.actionbar_music_normal));

        topToolbarTablayout.setupWithViewPager(topToolbarViewPager);

        playBarPlayList = (ImageView)findViewById(R.id.playbar_playlist);
        playBarPlay = (ImageView)findViewById(R.id.playbar_play);
        playBarNext = (ImageView)findViewById(R.id.playbar_next);
        playBarPlayList.setOnClickListener(this);
        playBarPlay.setOnClickListener(this);
        playBarNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.playbar_playlist:
                break;
            case R.id.playbar_play:
                playBarPlayState = !playBarPlayState;
                if (playBarPlayState){
                    playBarPlay.setImageResource(R.drawable.playbar_btn_pause);
                }else {
                    playBarPlay.setImageResource(R.drawable.playbar_btn_play);
                }
                break;
            case R.id.playbar_next:
                break;

            case R.id.nav_setting:
                break;

            case R.id.nav_exit:
                finish();
                break;

            default:break;
        }
    }

    private void initToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        //设置Menu的监听支持：
        setSupportActionBar(toolbar);
//        去掉标题栏
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
        switch (item.getItemId()){

            case R.id.my_message:
                break;
            case R.id.score_market:
                break;
            case R.id.pay_music:
                break;
            case R.id.online_music_non_use_data:
                break;
            case R.id.music_recognition:
                break;
            case R.id.theme_skin:
                break;
            case R.id.night_mode:
                break;
            case R.id.time_stop_play:
                break;
            case R.id.music_alarm_clock:
                break;
            case R.id.my_music_cloud:
                break;
            default:break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
