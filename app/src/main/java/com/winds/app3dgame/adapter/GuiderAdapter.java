package com.winds.app3dgame.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class GuiderAdapter extends PagerAdapter{
    private List<View> list;

    public GuiderAdapter(List<View> list){
        this.list=list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(list.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
