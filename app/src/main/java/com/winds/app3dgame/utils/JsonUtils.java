package com.winds.app3dgame.utils;

import com.winds.app3dgame.models.NewsInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
public class JsonUtils {
    public static List<NewsInfo> getJson(String strJson){
        List<NewsInfo> list=null;
        try {
            list=new ArrayList<NewsInfo>();
            JSONObject str=new JSONObject(strJson);
            JSONObject data=str.getJSONObject("data");
            for(int i=0;i<20;i++){
                JSONObject num=data.getJSONObject(i+"");

                String id=num.getString("id");
                String typeid=num.getString("typeid");
                String typeid2=num.getString("typeid2");
                String sortrank=num.getString("sortrank");
                String flag=num.getString("flag");
                String ismake=num.getString("ismake");
                String channel=num.getString("channel");
                String arcrank=num.getString("arcrank");
                String click=num.getString("click");
                String money=num.getString("money");
                String title=num.getString("title");
                String shorttitle=num.getString("shorttitle");
                String color=num.getString("color");
                String writer=num.getString("writer");
                String source=num.getString("source");
                String litpic="http://www.3dmgame.com"+num.getString("litpic");
                String pubdate=num.getString("pubdate");
                String senddate=num.getString("senddate");
                String mid=num.getString("mid");
                String keywords=num.getString("keywords");
                String lastpost=num.getString("lastpost");
                String scores=num.getString("scores");
                String goodpost=num.getString("goodpost");
                String badpost=num.getString("badpost");
                String voteid=num.getString("voteid");
                String notpost=num.getString("notpost");
                String description=num.getString("description");
                String filename=num.getString("filename");
                String dutyadmin=num.getString("dutyadmin");
                String tackid=num.getString("tackid");
                String mtype=num.getString("mtype");
                String weight=num.getString("weight");
                String fby_id=num.getString("fby_id");
                String game_id=num.getString("game_id");
                String feedback=num.getString("feedback");
                String typedir=num.getString("typedir");
                String typename=num.getString("typename");
                String corank=num.getString("corank");
                String isdefault=num.getString("isdefault");
                String defaultname=num.getString("defaultname");
                String namerule=num.getString("namerule");
                String namerule2=num.getString("namerule2");
                String ispart=num.getString("ispart");
                String moresite=num.getString("moresite");
                String siteurl=num.getString("siteurl");
                String sitepath=num.getString("sitepath");
                String arcurl=num.getString("arcurl");
                String typeurl=num.getString("typeurl");

                JSONObject videolist=num.getJSONObject("videolist");
                JSONObject zero=videolist.getJSONObject(0+"");
                String body=zero.getString("body");

                NewsInfo newsInfo=new NewsInfo(id,typeid,typeid2,sortrank,flag,ismake,channel,arcrank,click,money,title,shorttitle,color,writer,source,litpic,pubdate,senddate,mid,keywords,lastpost,scores,goodpost,badpost,voteid,notpost,description,filename,dutyadmin,tackid,mtype,weight,fby_id,game_id,feedback,typedir,typename,corank,isdefault,defaultname,namerule,namerule2,ispart,moresite,siteurl,sitepath,arcurl,typeurl,body);

                list.add(newsInfo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
