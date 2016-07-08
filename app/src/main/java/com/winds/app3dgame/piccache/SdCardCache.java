package com.winds.app3dgame.piccache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/27.
 */
public class SdCardCache {

    public boolean isSdCardMounted(){
        String state= Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }else {
            return false;
        }
    }

    public  File getSdCardPath(){
        if(isSdCardMounted()){
            File file=Environment.getExternalStorageDirectory();
            File cacheFile=new File(file,"picCache");

            if(!cacheFile.exists()){
                cacheFile.mkdirs();
            }

            return cacheFile;
        }
        return null;
    }

    public long getSdCardFreeSize(){
        String sdpath=getSdCardPath().getAbsolutePath();   //考虑下这个是否合适
        if(sdpath!=null){
            StatFs statFs=new StatFs(sdpath);
            int freeCount=statFs.getFreeBlocks();
            int size=statFs.getBlockSize();

//            Log.i("aaa","/mnt/sdcard/picCache大小:"+(long)freeCount*size);
//            //下面是测试：
//            StatFs statFs1=new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
//            Log.i("aaa","/mnt/sdcard大小:"+(long)statFs1.getFreeBlocks()*statFs1.getBlockSize());

            return (long)freeCount*size;
        }
        return 0;
    }

    public synchronized boolean savePicTOSdCard(byte[] data,String urlPath){
        FileOutputStream fos=null;
        boolean isSave=false;

        if(getSdCardFreeSize()>=data.length){
            String picName=urlPath.substring(urlPath.lastIndexOf("/")+1);

            File picFile=new File(getSdCardPath(),picName);
            try {
                fos=new FileOutputStream(picFile);
                fos.write(data,0,data.length);

                isSave=true;
                Log.i("aaa","保存文件成功");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(fos!=null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }else{
            throw new RuntimeException("SD卡空间不足");
        }
        return isSave;
    }

    public Bitmap getPicFromSdCard(String urlPath){
        Bitmap bitmap=null;
        if(isSdCardMounted()){
            if(urlPath!=null){  //这句是不是没必要要
                String picName=urlPath.substring(urlPath.lastIndexOf("/")+1);
                File picFile=new File(getSdCardPath(),picName);
                if(picFile.exists()){
                    bitmap= BitmapFactory.decodeFile(picFile.getAbsolutePath());
                }
            }
        }
        return bitmap;
    }

    public boolean deletePicFromSdCard(String urlPath){
        if(isSdCardMounted()){
            if(urlPath!=null){
                String picName=urlPath.substring(urlPath.lastIndexOf("/")+1);
                File picFile=new File(getSdCardPath(),picName);
                if(picFile.exists()){
                    return picFile.delete();
                }
            }
        }
        return false;
    }

    public void clear(){
        if(isSdCardMounted()){
            File cacheFile=getSdCardPath();
            File[] allPic=cacheFile.listFiles();
            for(File file:allPic){
                file.delete();
            }
            cacheFile.delete();
            Log.i("aaa","删除文件夹");
        }
    }

    public void saveBitmapToSdCard(Bitmap bitmap,String urlPath){
        byte[] b=bitmapBytes(bitmap);
        savePicTOSdCard(b,urlPath);
    }

    public byte[] bitmapBytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
