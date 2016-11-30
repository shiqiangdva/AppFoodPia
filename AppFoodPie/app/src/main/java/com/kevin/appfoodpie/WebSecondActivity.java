package com.kevin.appfoodpie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

public class WebSecondActivity extends BaseActivity {

    private WebView webView;
    private ImageView imgBack;

    @Override
    int setLayout() {
        return R.layout.activity_web_second;
    }

    @Override
    void initView() {
        webView = (WebView) findViewById(R.id.evaluation_web);
        imgBack = (ImageView) findViewById(R.id.eva_back);
    }

    @Override
    void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.loadUrl(url);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
