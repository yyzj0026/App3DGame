package com.winds.app3dgame.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.winds.app3dgame.dao.NewsDao;
import com.winds.app3dgame.models.NewsInfo;
import com.winds.app3dgame.piccache.ImgChange;
import com.winds.app3dgame.piccache.MemoryCache;
import com.winds.app3dgame.piccache.SdCardCache;
import com.winds.app3dgame.piccache.WebCache;
import com.winds.app3dgame.utils.JsonUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class DownloadNewsService extends Service {
    public DownloadNewsService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String url="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=1&paging=1&page=";
        final int pageIndex=intent.getIntExtra("pageIndex",1);
        new Thread(){
            public void run(){
                List<NewsInfo> newsInfoList=new ArrayList<NewsInfo>();
                NewsDao dao=new NewsDao(getApplicationContext());

                if(pageIndex==1){
                    dao.deleteAll();
                }

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
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
