package com.winds.app3dgame.piccache;


import com.winds.app3dgame.inteface.Callback;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/6/27.
 */
public class WebCache {
    public static byte[] getWebCache(String urlPath){
        try {
            URL url=new URL(urlPath);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            connection.connect();
            if(connection.getResponseCode()==200){
                InputStream is=connection.getInputStream();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                byte[] b=new byte[1024*5];
                int len=0;

                while((len=is.read(b,0,b.length))!=-1){
                    baos.write(b,0,len);
                    baos.flush();
                }

                if(is!=null){
                    is.close();
                }
                return baos.toByteArray();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public static void getPicFromWeb(final String urlPath, final Callback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] b=WebCache.getWebCache(urlPath);
                callback.getResult(b);
            }
        }).start();
    }
}
