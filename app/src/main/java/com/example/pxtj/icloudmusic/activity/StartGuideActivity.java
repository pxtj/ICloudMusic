package com.example.pxtj.icloudmusic.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pxtj.icloudmusic.R;
import com.example.pxtj.icloudmusic.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Porify on 2016/3/22.
 */
public class StartGuideActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener{

    private List<View> startViewList;
    private View startView1, startView2, startView3, startView4, startView5;

    private MyPagerAdapter startPageAdapter;
    private ViewPager startViewPager;
    private ImageView[] dotImageViews;
    private  int currentViewIndex;

    private GestureDetector gestureDetector;    //用户动作检测；
    private int flaggingWidth;  //设置互动翻页时需要滚动的页面的长度；

    private Button loginRegisterBtn, experienceNowBtn;

    private static final String FIRST_OPEN_PREF_NAME = "is_first_pref";
    private static final String FIRST_OPEN = "first_open";
    private static final String LOGIN_VIEW = "login_view";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //首先判断是否是一次进入应用：
        SharedPreferences pref = getSharedPreferences(FIRST_OPEN_PREF_NAME, MODE_PRIVATE);
        boolean isFirstOpen = pref.getBoolean(FIRST_OPEN, true);
        boolean isLoginView = pref.getBoolean(LOGIN_VIEW, false);
        if (!isFirstOpen){
            goToMainActivity();
        } else if (isLoginView){
            //如果在登录界面，则启动登录页面的Activity
        }

        //full screen display.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start_guide);

        initStartView();
        initDotImage();

        startViewPager = (ViewPager)findViewById(R.id.guide_viewpager);
        startPageAdapter = new MyPagerAdapter(startViewList);
        startViewPager.setAdapter(startPageAdapter);
        startViewPager.setOnPageChangeListener(this);

        //获取屏幕分辨率：
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //设置翻页所需要滚动的屏幕的长度：
        flaggingWidth = dm.widthPixels / 3;

        //检测动作，当滑动到最后一页时，判断滑动的距离，如果超过屏幕的3分之1，则进入的MainActivity；
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (currentViewIndex == startViewList.size() - 1){
                    if (e1.getX() - e2.getX() >= flaggingWidth){
                        goToMainActivity();
                        return true;
                    }
                }
                return false;
            }
        });

        loginRegisterBtn = (Button)findViewById(R.id.login_register_btn);
        experienceNowBtn = (Button)findViewById(R.id.experience_now_btn);

        //为立即体验的按钮设置红色边框：
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setStroke(2, getResources().getColor(android.R.color.holo_red_light));
        experienceNowBtn.setBackground(gradientDrawable);

        loginRegisterBtn.setOnClickListener(this);
        experienceNowBtn.setOnClickListener(this);

    }

    private void initStartView(){
        startViewList = new ArrayList<>();

        LayoutInflater inflater = getLayoutInflater();
        startView1 = inflater.inflate(R.layout.start_guide_view1, null);
        startView2 = inflater.inflate(R.layout.start_guide_view2, null);
        startView3 = inflater.inflate(R.layout.start_guide_view3, null);
        startView4 = inflater.inflate(R.layout.start_guide_view4, null);
        startView5 = inflater.inflate(R.layout.start_guide_view5, null);

        startViewList.add(startView1);
        startViewList.add(startView2);
        startViewList.add(startView3);
        startViewList.add(startView4);
        startViewList.add(startView5);

    }

    private void initDotImage(){
        dotImageViews = new ImageView[startViewList.size()];
        LinearLayout dotLayout = (LinearLayout)findViewById(R.id.start_dot_layout);

        for (int i = 0; i < dotImageViews.length; i++){
            dotImageViews[i] = (ImageView)dotLayout.getChildAt(i);
            dotImageViews[i].setSelected(false);
        }
        currentViewIndex = 0;
        dotImageViews[0].setSelected(true);
    }

    private void goToMainActivity(){
        writeInfoToSharedPreference(false, false);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageSelected(int position) {
        if (position < 0 || position > startViewList.size() -1 || currentViewIndex == position){
            return;
        }
        dotImageViews[position].setSelected(true);
        dotImageViews[currentViewIndex].setSelected(false);
        currentViewIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    //检测屏幕点击事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (gestureDetector.onTouchEvent(ev)){
            ev.setAction(MotionEvent.ACTION_CANCEL);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_register_btn:
                writeInfoToSharedPreference(true, true);
                Toast.makeText(this, "pressed login and register btn", Toast.LENGTH_LONG).show();
                break;
            case R.id.experience_now_btn:
                goToMainActivity();
                break;
            default:break;
        }
    }

    private void writeInfoToSharedPreference(boolean isFirstOpen, boolean isLoginView){
        SharedPreferences preferences = getSharedPreferences(FIRST_OPEN_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(FIRST_OPEN, isFirstOpen);
        editor.putBoolean(LOGIN_VIEW, isLoginView);
        editor.commit();

    }
}
