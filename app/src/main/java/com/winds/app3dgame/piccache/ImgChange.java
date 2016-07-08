package com.winds.app3dgame.piccache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by Administrator on 2016/6/27.
 */
public class ImgChange {
    public static Bitmap getImg(byte[] data,int width,int height){  //这里的长宽为最终想要现实的长宽

        Bitmap initBitmap= BitmapFactory.decodeByteArray(data,0,data.length); //此处是转换真实的图片，其实可以不要
        Log.i("aaa","原始图片大小："+initBitmap.getByteCount());

        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;  //只获取图片大小，不获取图片本身
        Bitmap bitmapBefore=BitmapFactory.decodeByteArray(data,0,data.length,options);  //第一次获取图片,只获取图片的大小

        options.inSampleSize=getSampleSize(options,width,height);   //第二次获取图片,有给定的比例,进行了压缩
        Log.i("aaa","比例："+options.inSampleSize);
        options.inJustDecodeBounds=false;//要获取真实图片了
        Bitmap bitmapAfter=BitmapFactory.decodeByteArray(data,0,data.length,options);

        return bitmapAfter;
    }

    public static int getSampleSize(BitmapFactory.Options options,int endWidth,int endHeight){//这里的长宽为最终想要现实的长宽
        int picWidth=options.outWidth;
        int picHeight=options.outHeight;
        Log.i("aaa","原始图片的宽："+picWidth+" 高："+picHeight);

        int sampleSize=1;

        if(picWidth>endWidth||picHeight>endHeight){
            int widthRatio=Math.round((float)picWidth/endWidth);
            int heightRatio=Math.round((float)picHeight/endHeight);

            sampleSize=widthRatio>heightRatio?heightRatio:widthRatio;
        }

        return sampleSize;
    }
}
