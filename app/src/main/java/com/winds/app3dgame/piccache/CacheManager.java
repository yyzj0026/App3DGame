package com.winds.app3dgame.piccache;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import com.winds.app3dgame.inteface.Callback;

/**
 * Created by Administrator on 2016/6/27.
 */
public class CacheManager {
    private WebCache webCache=new WebCache();
    private SdCardCache sdCardCache=new SdCardCache();
    private MemoryCache memoryCache=new MemoryCache();
    private Handler handler=new Handler();

    public void getCache(final String urlPath, final ImageView iv){
        Bitmap bitmap=memoryCache.getPicFromLruCache(urlPath);
        if(bitmap!=null){
            Log.i("aaa","缓存里已存有图片");
        }else if((bitmap=sdCardCache.getPicFromSdCard(urlPath))!=null){
            memoryCache.addPicToLruCache(urlPath,bitmap);
            Log.i("aaa","sd卡里存有图片，但缓存没有");
        }else{
            webCache.getPicFromWeb(urlPath, new Callback() {
                @Override
                public void getResult(byte[] b) {
                    final Bitmap bitmap1=ImgChange.getImg(b,100,100);
                    sdCardCache.saveBitmapToSdCard(bitmap1,urlPath);
                    memoryCache.addPicToLruCache(urlPath,bitmap1);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(bitmap1);
                        }
                    });
                }
            });

            Log.i("aaa","从网络上下载，刚存入sd卡和缓存");
        }

        iv.setImageBitmap(bitmap);
    }

//    public void saveCache(final String urlPath){
//        webCache.getPicFromWeb(urlPath, new Callback() {
//            @Override
//            public void getResult(byte[] b) {
//                if(b!=null){
//                    final Bitmap bitmap1=ImgChange.getImg(b,60,60);
//                    sdCardCache.saveBitmapToSdCard(bitmap1,urlPath);
//                    memoryCache.addPicToLruCache(urlPath,bitmap1);
//
//                }else {
//                    Log.i("aaa","图片下载有问题");
//                }
//            }
//        });
//    }

    public void clear(){
        memoryCache.clear();
        sdCardCache.clear();
    }
}
