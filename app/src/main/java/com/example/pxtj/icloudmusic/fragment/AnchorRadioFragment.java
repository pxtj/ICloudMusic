package com.example.pxtj.icloudmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pxtj.icloudmusic.R;

/**
 * Created by 10191628 on 2016/3/22.
 */
public class AnchorRadioFragment extends Fragment{
    private View anchorRadioView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        anchorRadioView = inflater.inflate(R.layout.music_radio, container, false);
        return anchorRadioView;
    }
}
