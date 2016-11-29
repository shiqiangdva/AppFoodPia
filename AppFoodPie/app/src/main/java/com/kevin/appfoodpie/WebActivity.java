package com.kevin.appfoodpie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.kevin.appfoodpie.R;

public class WebActivity extends BaseActivity {
    private WebView webView;
    private String url;

    @Override
    int setLayout() {
        return R.layout.activity_web;
    }

    @Override
    void initView() {
        // 绑定webView
        webView = (WebView) findViewById(R.id.aty_web_show);
    }

    @Override
    void initData() {

    }
}
