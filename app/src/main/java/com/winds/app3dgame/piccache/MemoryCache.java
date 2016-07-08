package com.winds.app3dgame.piccache;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * Created by Administrator on 2016/6/27.
 */
public class MemoryCache {
    private LruCache<String,Bitmap> lruCache;

    public MemoryCache(){
        int maxMemory= (int) Runtime.getRuntime().maxMemory();  //单位是MB
        int cacheMemory=maxMemory/8;

        lruCache=new LruCache<String,Bitmap>(cacheMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024; //单位是MB,与上一致
            }
        };
    }

    public synchronized void addPicToLruCache(String urlPath,Bitmap bitmap){
        if(urlPath!=null){
            if(bitmap!=null){
                lruCache.put(urlPath,bitmap);
                Log.i("aaa","缓存保存文件成功");
            }
        }
    }

    public Bitmap getPicFromLruCache(String urlPath){
        Bitmap bitmap=null;
        if(urlPath!=null){
            bitmap=lruCache.get(urlPath);
        }
        return bitmap;
    }

    public synchronized void removePicFromLruCache(String urlPath){
        if(urlPath!=null){
            Bitmap bitmap=lruCache.remove(urlPath);
            if(bitmap!=null){
                bitmap.recycle();
            }
        }
    }

    public void clear(){
        if(lruCache.size()>0){
            lruCache.evictAll();
        }
        lruCache=null;
    }
}
