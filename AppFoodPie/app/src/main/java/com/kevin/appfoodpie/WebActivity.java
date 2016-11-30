package com.kevin.appfoodpie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.kevin.appfoodpie.R;

public class WebActivity extends BaseActivity {
    private WebView webView;
    private String url;
    private ImageView imgBack;

    @Override
    int setLayout() {
        return R.layout.activity_web;
    }

    @Override
    void initView() {
        // 绑定webView
        webView = (WebView) findViewById(R.id.aty_web_show);
        imgBack = (ImageView) findViewById(R.id.aty_web_iv);
    }

    @Override
    void initData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("link");
        webView.loadUrl(url);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
