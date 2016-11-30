package com.kevin.appfoodpie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebSecondActivity extends BaseActivity {

    private WebView webView;

    @Override
    int setLayout() {
        return R.layout.activity_web_second;
    }

    @Override
    void initView() {
        webView = (WebView) findViewById(R.id.evaluation_web);
    }

    @Override
    void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.loadUrl(url);
    }
}
