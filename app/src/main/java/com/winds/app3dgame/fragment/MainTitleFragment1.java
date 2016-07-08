package com.winds.app3dgame.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.winds.app3dgame.AsyncTask.MainTitleFragment1AsyncTask;
import com.winds.app3dgame.R;
import com.winds.app3dgame.adapter.MainTitleFragment1Adapter;
import com.winds.app3dgame.adapter.MainTitleFragment1ListViewAdapter;
import com.winds.app3dgame.customview.MainTitleFragmentViewPager;
import com.winds.app3dgame.dao.NewsDao;
import com.winds.app3dgame.piccache.CacheManager;
import com.winds.app3dgame.service.DownloadNewsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
@SuppressLint("ValidFragment")
public class MainTitleFragment1 extends Fragment implements ViewPager.OnPageChangeListener,PullToRefreshBase.OnRefreshListener2{
    private MainTitleFragmentViewPager viewPager;
    private LinearLayout ll;
    private PullToRefreshListView pullToRefreshListView;

    private MainTitleFragment1Adapter viewPagerAdapter;
    private MainTitleFragment1ListViewAdapter listViewAdapter;

    private List<ImageView> imgList;
    private ImageView[] imgDot;
    private int[] imgArray={R.drawable.default1, R.drawable.default2,R.drawable.default3};
    private List<HashMap<String,Object>> articleList;

    private int currentIndex;
    private int pageIndex=1;

    public  MainTitleFragment1(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_title_fragment1,null);

        ll= (LinearLayout) view.findViewById(R.id.main_title_fragment1_ll);
        viewPager= (MainTitleFragmentViewPager) view.findViewById(R.id.main_title_fragment_viewpager);
        pullToRefreshListView= (PullToRefreshListView) view.findViewById(R.id.main_title_fragment1_pulltorefresh);

        initData();
        initDot();
        initViewPager();
        initPullToRefreshListView();

        return view;
    }

    public void initDot(){
        imgDot=new ImageView[imgList.size()];

        for(int i=0;i<imgDot.length;i++){
            imgDot[i]=(ImageView) ll.getChildAt(i);
            imgDot[i].setEnabled(false);
        }
        currentIndex=0;
        imgDot[currentIndex].setEnabled(true);
    }

    public void initData(){
        imgList=new ArrayList<>();
        for(int i=0;i<3;i++){
            ImageView img=new ImageView(getActivity());
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setImageResource(imgArray[i]);

            imgList.add(img);
        }
    }

    public void initPullToRefreshListView(){
        CacheManager cacheManager=new CacheManager();
        articleList=new ArrayList<>();
        ListView listView=pullToRefreshListView.getRefreshableView();
        listViewAdapter=new MainTitleFragment1ListViewAdapter(articleList,getContext(),cacheManager);
        listView.setAdapter(listViewAdapter);   //listview也应该加监听
        if(articleList==null){
            Log.i("aaa","fragment1里articleList为空");
        }
        getInfo();

        pullToRefreshListView.setOnRefreshListener(this);
    }

    public void initViewPager(){
        viewPagerAdapter=new MainTitleFragment1Adapter(imgList);
        viewPager.addOnPageChangeListener(this);//viewPage还可以加载点击监听
        viewPager.setAdapter(viewPagerAdapter);
    }

// 下边是3个ViewPager的监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position<0||position+1>imgList.size()){
            return;
        }
        imgDot[position].setEnabled(true);
        imgDot[currentIndex].setEnabled(false);
        currentIndex=position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //下边两个是PullToRefresh的监听
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {   //这里进行刷新
        Log.i("aaa","文章部分碎片1刷新数据");
        Intent intent2=new Intent(getContext(), DownloadNewsService.class);
        intent2.putExtra("urlPath",1);
        getContext().startService(intent2);
        getInfo();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {   //这里加载下一页
        Log.i("aaa","文章部分碎片1读取下一页页面");
        Intent intent2=new Intent(getContext(), DownloadNewsService.class);
        pageIndex+=1;
        intent2.putExtra("pageIndex",pageIndex);
        getContext().startService(intent2);
        getInfo();

    }

    public void getInfo(){
        MainTitleFragment1AsyncTask asyncTask=new MainTitleFragment1AsyncTask(articleList,getContext(),listViewAdapter,pullToRefreshListView);
        asyncTask.execute("title","pubdate","feedback","litpic");
    }
}
