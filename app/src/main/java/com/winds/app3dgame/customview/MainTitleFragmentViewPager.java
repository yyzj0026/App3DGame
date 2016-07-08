package com.winds.app3dgame.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/7/6.
 */
public class MainTitleFragmentViewPager extends ViewPager{

    public MainTitleFragmentViewPager(Context context) {
        super(context);
    }

    public MainTitleFragmentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);//不允许父类拦截触摸事件
        return super.dispatchTouchEvent(ev);
    }
}
