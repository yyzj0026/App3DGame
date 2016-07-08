package com.winds.app3dgame.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class MainTitleFragment1Adapter extends PagerAdapter {
    private List<ImageView> imgList;

    public MainTitleFragment1Adapter(List<ImageView> imgList) {
        this.imgList = imgList;
    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imgList.get(position));
        return imgList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgList.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
