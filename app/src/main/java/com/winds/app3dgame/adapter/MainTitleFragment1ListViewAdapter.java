package com.winds.app3dgame.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.winds.app3dgame.R;
import com.winds.app3dgame.piccache.CacheManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
public class MainTitleFragment1ListViewAdapter extends BaseAdapter{
    private List<HashMap<String,Object>> list;
    private Context context;
    private CacheManager manager;

    public MainTitleFragment1ListViewAdapter(List<HashMap<String, Object>> list, Context context, CacheManager manager) {
        this.list = list;
        this.context = context;
        this.manager = manager;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.main_listview_item,null);
            holder=new ViewHolder();
            holder.iv= (ImageView) convertView.findViewById(R.id.main_listview_item_imgview);
            holder.title_tv= (TextView) convertView.findViewById(R.id.main_listview_item_title);
            holder.date_tv= (TextView) convertView.findViewById(R.id.main_listview_item_date);
            holder.comment_tv= (TextView) convertView.findViewById(R.id.main_listview_item_comment);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        HashMap<String,Object> map=list.get(i);

        holder.title_tv.setText(map.get("title").toString());
        holder.comment_tv.setText(map.get("feedback").toString());

        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=sf.format(new Date(Long.parseLong(map.get("pubdate").toString())));
        holder.date_tv.setText(date);


        String path=map.get("litpic").toString();
        Log.i("aaa","图片地址："+path);
        if(!path.equals("none")){

            manager.getCache(path,holder.iv);

        }else{
            holder.iv.setImageResource(R.mipmap.ic_launcher);
        }

        return convertView;
    }

    class ViewHolder{
        ImageView iv=null;
        TextView title_tv=null;
        TextView date_tv=null;
        TextView comment_tv=null;
    }
}
