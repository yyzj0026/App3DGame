package com.winds.app3dgame.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.winds.app3dgame.R;

/**
 * Created by Administrator on 2016/7/6.
 */
@SuppressLint("ValidFragment")
public class MainTitleFragment2 extends Fragment{
    private int typeId;


    public  MainTitleFragment2(int typeId){
        this.typeId=typeId;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_title_fragment2,null);

        TextView tv= (TextView) view.findViewById(R.id.main_title_fragment2_textview);
        tv.setText(typeId+"");
        return view;
    }
}
