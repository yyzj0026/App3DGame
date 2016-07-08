package com.winds.app3dgame;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.winds.app3dgame.adapter.MainFragmentAdapter;
import com.winds.app3dgame.fragment.MainTitleFragment1;
import com.winds.app3dgame.fragment.MainTitleFragment10;
import com.winds.app3dgame.fragment.MainTitleFragment2;
import com.winds.app3dgame.fragment.MainTitleFragment3;
import com.winds.app3dgame.fragment.MainTitleFragment4;
import com.winds.app3dgame.fragment.MainTitleFragment5;
import com.winds.app3dgame.fragment.MainTitleFragment6;
import com.winds.app3dgame.fragment.MainTitleFragment7;
import com.winds.app3dgame.fragment.MainTitleFragment8;
import com.winds.app3dgame.fragment.MainTitleFragment9;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private RadioGroup radioGroup_top;
    private RadioGroup radioGroup_bottom;
    private HorizontalScrollView horizontalScrollView;
    private RadioButton titleBtn1;

    private List<Fragment> fragmentList;
    private MainFragmentAdapter fragmentAdapter;
    private RadioButton[] radioButtons;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        initData();
    }

    public void initView(){
        horizontalScrollView= (HorizontalScrollView) this.findViewById(R.id.main_scrollView);
        viewPager= (ViewPager) this.findViewById(R.id.main_viewPager);
        radioGroup_top= (RadioGroup) this.findViewById(R.id.main_radioGroup_title);
        radioGroup_bottom= (RadioGroup) this.findViewById(R.id.main_radioGroup_bottom);
        titleBtn1= (RadioButton) this.findViewById(R.id.main_title_btn1);
        titleBtn1.setChecked(true);
    }

    public void initData(){
        fragmentList=new ArrayList<>();
        MainTitleFragment1 mainTitleFragment1=new MainTitleFragment1();
        MainTitleFragment2 mainTitleFragment2=new MainTitleFragment2(2);
        MainTitleFragment3 mainTitleFragment3=new MainTitleFragment3(3);
        MainTitleFragment4 mainTitleFragment4=new MainTitleFragment4(4);
        MainTitleFragment5 mainTitleFragment5=new MainTitleFragment5(5);
        MainTitleFragment6 mainTitleFragment6=new MainTitleFragment6(6);
        MainTitleFragment7 mainTitleFragment7=new MainTitleFragment7(7);
        MainTitleFragment8 mainTitleFragment8=new MainTitleFragment8(8);
        MainTitleFragment9 mainTitleFragment9=new MainTitleFragment9(9);
        MainTitleFragment10 mainTitleFragment10=new MainTitleFragment10(10);
        fragmentList.add(mainTitleFragment1);
        fragmentList.add(mainTitleFragment2);
        fragmentList.add(mainTitleFragment3);
        fragmentList.add(mainTitleFragment4);
        fragmentList.add(mainTitleFragment5);
        fragmentList.add(mainTitleFragment6);
        fragmentList.add(mainTitleFragment7);
        fragmentList.add(mainTitleFragment8);
        fragmentList.add(mainTitleFragment9);
        fragmentList.add(mainTitleFragment10);

        fragmentAdapter=new MainFragmentAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(fragmentAdapter);

        radioButtons=new RadioButton[3];
        for(int i=0;i<3;i++){
            radioButtons[i]= (RadioButton) radioGroup_bottom.getChildAt(i);
        }
        currentIndex=0;
        radioButtons[currentIndex].setBackgroundColor(Color.parseColor("#6AC37A"));
    }

    public void initListener(){
        radioGroup_top.setOnCheckedChangeListener(this);
        radioGroup_bottom.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId){
            case R.id.main_title_btn1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.main_title_btn2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.main_title_btn3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.main_radioGroup_bottom_article:
                viewPager.setCurrentItem(0);

                if(currentIndex!=0){
                    radioButtons[0].setBackgroundColor(Color.parseColor("#6AC37A"));
                    radioButtons[currentIndex].setBackgroundColor(Color.BLACK);
                    currentIndex=0;
                }

                horizontalScrollView.smoothScrollTo(0,0);
                break;
            case R.id.main_radioGroup_bottom_chat:
//                viewPager.setCurrentItem(3);

                if(currentIndex!=1){
                    radioButtons[1].setBackgroundColor(Color.parseColor("#6AC37A"));
                    radioButtons[currentIndex].setBackgroundColor(Color.BLACK);
                    currentIndex=1;
                }

                horizontalScrollView.smoothScrollTo(0,0);
                break;
            case R.id.main_radioGroup_bottom_game:
//                viewPager.setCurrentItem(3);

                if(currentIndex!=2){
                    radioButtons[2].setBackgroundColor(Color.parseColor("#6AC37A"));
                    radioButtons[currentIndex].setBackgroundColor(Color.BLACK);
                    currentIndex=2;
                }

                horizontalScrollView.smoothScrollTo(0,0);
                break;

        }

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        horizontalScrollView.setVisibility(View.VISIBLE);
        radioGroup_top.setVisibility(View.VISIBLE);

        RadioButton btn= (RadioButton) radioGroup_top.getChildAt(position);
        btn.setChecked(true);
        int left=btn.getLeft();
        horizontalScrollView.smoothScrollTo(left-10,0);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
