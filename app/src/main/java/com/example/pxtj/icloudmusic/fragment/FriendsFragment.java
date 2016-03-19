package com.example.pxtj.icloudmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pxtj.icloudmusic.R;

/**
 * Created by Porify on 2016/3/19.
 */
public class FriendsFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View friendsView = inflater.inflate(R.layout.friends, container, false);
        return friendsView;
    }

}
