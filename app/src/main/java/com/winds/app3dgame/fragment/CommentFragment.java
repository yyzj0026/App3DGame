package com.winds.app3dgame.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winds.app3dgame.R;

/**
 * Created by Administrator on 2016/7/8.
 */
public class CommentFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_title_fragment1,null);



        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
