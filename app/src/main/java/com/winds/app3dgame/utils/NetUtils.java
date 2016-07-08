package com.winds.app3dgame.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2016/7/5.
 */
public class NetUtils {
    public static boolean isNetOpen(Activity activity){
        boolean flag=false;

        ConnectivityManager manager= (ConnectivityManager) activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager!=null){
            NetworkInfo info=manager.getActiveNetworkInfo();
            if(info!=null||info.isAvailable()){
                flag=true;
            }
        }

        return flag;
    }
}
