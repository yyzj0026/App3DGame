
package com.winds.app3dgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.winds.app3dgame.adapter.GuiderAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private LinearLayout ll;
    private LayoutInflater inflater;
    private List<View> viewList;
    private GuiderAdapter adapter;
    private ImageView[] dotImg;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
//        Log.i("aaa","这里是guide页面");

        viewPager= (ViewPager) this.findViewById(R.id.guide_viewPager);
        ll= (LinearLayout) this.findViewById(R.id.guide_ll);

        initView();
        initDot();
    }

    public void initView(){
        viewList=new ArrayList<View>();
        inflater=LayoutInflater.from(this);

        View tabView1=inflater.inflate(R.layout.guide_tab_view1,null);
        View tabView2=inflater.inflate(R.layout.guide_tab_view2,null);
        View tabView3=inflater.inflate(R.layout.guide_tab_view3,null);
        viewList.add(tabView1);
        viewList.add(tabView2);
        viewList.add(tabView3);

        adapter=new GuiderAdapter(viewList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }

    public void initDot(){
        dotImg=new ImageView[viewList.size()];

        for(int i=0;i<viewList.size();i++){
            dotImg[i]= (ImageView) ll.getChildAt(i);
            dotImg[i].setEnabled(false);
        }

        currentIndex=0;
        dotImg[currentIndex].setEnabled(true);
    }

    public void ChangeLoginState(){
        SharedPreferences preferences=getSharedPreferences("Login",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();

        editor.putBoolean("isFirst",false);
        editor.commit();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position<0||position+1>viewList.size()){
            return;
        }

        dotImg[position].setEnabled(true);
        dotImg[currentIndex].setEnabled(false);
        currentIndex=position;

        if(position==viewList.size()-1){
            Button enter= (Button) viewList.get(position).findViewById(R.id.guide_enter_btn);
            enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ChangeLoginState();
                    Intent intent=new Intent(GuideActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
