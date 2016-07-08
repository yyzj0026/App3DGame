package com.winds.app3dgame;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    private WebSettings webSettings;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_cache);

        Intent intent=getIntent();
        String arcurl=intent.getStringExtra("arcurl");
        String title=intent.getStringExtra("title");

        webView= (WebView) this.findViewById(R.id.webcache_webview);
        webSettings=webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webView.loadUrl(arcurl);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        actionBar=getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);  //true还是false
    }
}
