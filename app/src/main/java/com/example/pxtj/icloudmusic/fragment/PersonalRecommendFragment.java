package com.example.pxtj.icloudmusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.pxtj.icloudmusic.R;
import com.example.pxtj.icloudmusic.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.*;

/**
 * Created by 10191628 on 2016/3/22.
 */
public class PersonalRecommendFragment extends Fragment implements ViewPager.OnPageChangeListener{
    private View personalRecommendView;

    private List<View> recommendViewList;
    private MyPagerAdapter recommendPageAdapter;

    private ViewPager recommendViewPager;
    private ViewGroup dotViewGroup;
    private ImageView[] dotImagerViews;

    private int currentIndex = 0;

    private static final int AUTO_MSG = 1;
    private static final int AUTO_CHANGE_TIME = 5000;
    private static final int HANDLE_CHANGE_TIME = 10000;

    //通过Handler的方式来轮播：
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case AUTO_MSG:
                    autoPlay();
                    break;
                default:break;
            }
        }
    };

   @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (personalRecommendView == null){
            personalRecommendView = inflater.inflate(R.layout.personal_recommand, container, false);
        }
        return personalRecommendView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dotViewGroup = (ViewGroup)personalRecommendView.findViewById(R.id.recommend_viewpage_dot);
        recommendViewPager = (ViewPager)personalRecommendView.findViewById(R.id.recommend_viewpage);

        if (recommendViewList == null){
            initPagerView();
        }
        if (dotImagerViews == null){
            initDotView();
        }

        recommendPageAdapter = new MyPagerAdapter(recommendViewList);
        recommendViewPager.setAdapter(recommendPageAdapter);
        recommendViewPager.setOnPageChangeListener(this);

        mHandler.sendEmptyMessageDelayed(AUTO_MSG, AUTO_CHANGE_TIME);

    }

    private void initPagerView(){

        int[] imags = new int[]{R.drawable.prs_rcmd_page6, R.drawable.prs_rcmd_page5, R.drawable.prs_rcmd_page4,
                                R.drawable.prs_rcmd_page3, R.drawable.prs_rcmd_page2, R.drawable.prs_rcmd_page1};
        recommendViewList = new ArrayList<>();
        for (int i = 0; i < imags.length; i++){
            LinearLayout layout = new LinearLayout(getContext());
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            final ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setImageResource(imags[i]);
            layout.addView(imageView, layoutParams);
            recommendViewList.add(layout);
        }
    }

    private void initDotView(){

        dotImagerViews = new ImageView[recommendViewList.size()];
        LinearLayout.LayoutParams dotMargin = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dotMargin.setMargins(10, 0, 0, 0);  //设置各个点之间的距离；
        for (int i = 0; i < dotImagerViews.length; i++){
            final ImageView dotImage = new ImageView(getContext());
            dotImage.setLayoutParams(new LayoutParams(15, 15));
            dotImagerViews[i] = dotImage;
            dotImagerViews[i].setImageResource(R.drawable.lrc_icn_dot);
            dotViewGroup.addView(dotImagerViews[i], dotMargin);
        }
        dotImagerViews[0].setImageResource(R.drawable.actionbar_menu_dot);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position > recommendViewList.size() - 1 || position < 0){
            return;
        }
        dotImagerViews[currentIndex].setImageResource(R.drawable.lrc_icn_dot);
        dotImagerViews[position].setImageResource(R.drawable.actionbar_menu_dot);
        currentIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state){
            case ViewPager.SCROLL_STATE_IDLE:
                break;
            case ViewPager.SCROLL_STATE_DRAGGING:
                //当手动滑动之后，间隔10s在自动滑动到下一页；
                //先清除消息队列中未发送的消息，避免引发混乱；
                if (mHandler.hasMessages(AUTO_MSG)){
                    mHandler.removeMessages(AUTO_MSG);
                }
                mHandler.sendEmptyMessageDelayed(AUTO_MSG, HANDLE_CHANGE_TIME);
                break;
            default:break;
        }

    }
    private void autoPlay(){
        dotImagerViews[currentIndex].setImageResource(R.drawable.lrc_icn_dot);
        currentIndex += 1;
        if (currentIndex >= recommendViewList.size()){
            currentIndex = 0;
        }
        dotImagerViews[currentIndex].setImageResource(R.drawable.actionbar_menu_dot);
        recommendViewPager.setCurrentItem(currentIndex);
        mHandler.sendEmptyMessageDelayed(AUTO_MSG, AUTO_CHANGE_TIME);
    }



}
