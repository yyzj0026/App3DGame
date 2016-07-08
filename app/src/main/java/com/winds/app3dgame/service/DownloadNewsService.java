package com.winds.app3dgame.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.winds.app3dgame.dao.NewsDao;
import com.winds.app3dgame.fragment.MainTitleFragment1;
import com.winds.app3dgame.models.NewsInfo;
import com.winds.app3dgame.piccache.ImgChange;
import com.winds.app3dgame.piccache.MemoryCache;
import com.winds.app3dgame.piccache.SdCardCache;
import com.winds.app3dgame.piccache.WebCache;
import com.winds.app3dgame.utils.JsonUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DownloadNewsService extends Service {
    private Handler handler;
    private String url="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=1&paging=1&page=";
    private NewsDao dao;

    @Override
    public void onCreate() {
        handler=new Handler();
        dao=new NewsDao(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int pageIndex=intent.getIntExtra("pageIndex",1);
        boolean welcomePage=intent.getBooleanExtra("welcomePage",false);
        boolean readInfo=intent.getBooleanExtra("readInfo",false);
        final String[] strings=intent.getStringArrayExtra("selectColumn");

        if(readInfo){
            new Thread(){
                public void run(){
                    readInfo(strings);
                }
            }.start();
        }else{
            this.getInfo(pageIndex,welcomePage,strings);
        }

        return START_REDELIVER_INTENT;
    }

    public boolean savePicOk(NewsInfo newsInfo,NewsDao dao){
        boolean isOk=false;

        SdCardCache sdCardCache=new SdCardCache();
//        MemoryCache memoryCache=new MemoryCache();
        String picPath=newsInfo.getLitpic();


        if(!picPath.equals("none")){
            String[] str=picPath.split("/");
            String fileName=str[str.length-1];

            byte[] b= WebCache.getWebCache(picPath);
            if(b!=null){
                Bitmap bitmap1= ImgChange.getImg(b,60,60);
                sdCardCache.saveBitmapToSdCard(bitmap1,picPath);
            }

            String newPath= sdCardCache.getSdCardPath().getAbsolutePath()+ File.separator+fileName;
            newsInfo.setLitpic(newPath);
            dao.update(newsInfo);

            isOk=true;
        }


        return isOk;
    }

    public void getInfo(final int pageIndex, final boolean welcomePage, final String[] strings){
        if(pageIndex==1){
            dao.deleteAll();
        }
        new Thread(){
            public void run(){
                List<NewsInfo> newsInfoList=new ArrayList<NewsInfo>();

                byte[] info=WebCache.getWebCache(url+pageIndex);
                if(info!=null){
                    try {
                        String strJson = new String(info,"utf-8");
                        newsInfoList= JsonUtils.getJson(strJson);

                        if(newsInfoList!=null){

                            for(NewsInfo info1:newsInfoList){
                                dao.insert(info1);
                                savePicOk(info1,dao);
                            }

                            if(!welcomePage){
                                readInfo(strings);
//                                List<HashMap<String,Object>> list=dao.getAllNewsList(strings);
//                                MainTitleFragment1.articleList.clear();
//                                MainTitleFragment1.articleList.addAll(list);
//                                handler.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        MainTitleFragment1.listViewAdapter.notifyDataSetChanged();
//                                        MainTitleFragment1.pullToRefreshListView.onRefreshComplete();
//                                    }
//                                });
                            }
                        }else{
                            Log.i("aaa","Json解析有问题");
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }else {
                    Log.i("aaa","网络异常");
                }
            }
        }.start();
    }

    public void readInfo(String[] strings){
        List<HashMap<String,Object>> list=dao.getAllNewsList(strings);
        MainTitleFragment1.articleList.clear();
        MainTitleFragment1.articleList.addAll(list);
        handler.post(new Runnable() {
            @Override
            public void run() {
                MainTitleFragment1.listViewAdapter.notifyDataSetChanged();
                MainTitleFragment1.pullToRefreshListView.onRefreshComplete();
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        stopSelf();
    }
}
