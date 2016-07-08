package com.winds.app3dgame.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.winds.app3dgame.adapter.MainTitleFragment1ListViewAdapter;
import com.winds.app3dgame.dao.NewsDao;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
public class MainTitleFragment1AsyncTask extends AsyncTask<String,Void,List<HashMap<String,Object>>> {
    private List<HashMap<String,Object>> articleList;
    private Context context;
    private MainTitleFragment1ListViewAdapter listViewAdapter;
    private PullToRefreshListView pullToRefreshListView;

    public MainTitleFragment1AsyncTask(List<HashMap<String, Object>> articleList, Context context, MainTitleFragment1ListViewAdapter listViewAdapter, PullToRefreshListView pullToRefreshListView) {
        this.articleList = articleList;
        this.context = context;
        this.listViewAdapter = listViewAdapter;
        this.pullToRefreshListView = pullToRefreshListView;
    }

    @Override
    protected List<HashMap<String, Object>> doInBackground(String... strings) {
        NewsDao dao=new NewsDao(context);

        return dao.getAllNewsList(strings);
    }

    @Override
    protected void onPostExecute(List<HashMap<String, Object>> list) {

        if(articleList==null){
            Log.i("aaa","articleList为空");
        }
        articleList.clear();
        articleList.addAll(list);
        listViewAdapter.notifyDataSetChanged();
        pullToRefreshListView.onRefreshComplete();
        super.onPostExecute(list);
    }
}
