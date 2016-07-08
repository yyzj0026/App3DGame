package com.winds.app3dgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.winds.app3dgame.service.DownloadNewsService;
import com.winds.app3dgame.utils.NetUtils;

import pl.droidsonroids.gif.GifImageView;

public class WelcomeActivity extends AppCompatActivity {
    private GifImageView gifImg;
    private Animation animation;
    private boolean netState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        gifImg= (GifImageView) this.findViewById(R.id.gifView);
        animation=new TranslateAnimation(0f,0f,0f,0.5f);
        animation.setDuration(2000);
        gifImg.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("aaa","判断网络是否连接");
                netState= NetUtils.isNetOpen(WelcomeActivity.this);
                if(netState){
                    Log.i("aaa","后台进行数据下载,启动service");
                    Intent intent=new Intent(WelcomeActivity.this, DownloadNewsService.class);
                    intent.putExtra("pageIndex",1);
                    startService(intent);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                Log.i("aaa","判断网络是否开启和是否第一次登录");
                Intent intent1=null;
                if(!netState){
                    Toast.makeText(WelcomeActivity.this, "请连接网络", Toast.LENGTH_SHORT).show();
                }

                if(isFirstLogin()){
                    intent1=new Intent(WelcomeActivity.this,GuideActivity.class);
                }else{
                    intent1=new Intent(WelcomeActivity.this,MainActivity.class);
                }
                startActivity(intent1);
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public Boolean isFirstLogin(){
        SharedPreferences login=getSharedPreferences("Login", Context.MODE_PRIVATE);
        boolean firstLogin=login.getBoolean("isFirst",true);

        return firstLogin;
    }
}
