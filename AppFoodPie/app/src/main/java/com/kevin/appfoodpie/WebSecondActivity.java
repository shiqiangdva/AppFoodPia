package com.kevin.appfoodpie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kevin.appfoodpie.values.DBTool;
import com.kevin.appfoodpie.values.Url;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class WebSecondActivity extends BaseActivity {

    private WebView webView;
    private ImageView imgBack;
    private ImageView imgIove;
    private int i;

    private LinearLayout share;

    @Override
    int setLayout() {
        return R.layout.activity_web_second;
    }

    @Override
    void initView() {
        webView = (WebView) findViewById(R.id.evaluation_web);
        imgBack = (ImageView) findViewById(R.id.eva_back);
        imgIove = (ImageView) findViewById(R.id.img_love);
        share = (LinearLayout) findViewById(R.id.eva_share_ll);
    }

    @Override
    void initData() {
        Intent intent = getIntent();
        final String url = intent.getStringExtra("url");
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /////////////////////////////////////

        if (!DBTool.getInstance().isUrlSave(url)) {
            i = 1;
            imgIove.setSelected(false);
        } else {
            i = 2;
            imgIove.setSelected(true);
        }

        imgIove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i % 2 != 0) {
                    // 没存 要存 单数
                    imgIove.setSelected(true);
                    Url url1 = new Url(null, url);
                    DBTool.getInstance().insertUrl(url1);

                    i = i + 1;
                } else {
                    // 存过 要删 双数
                    imgIove.setSelected(false);
                    i = i + 1;

                    DBTool.getInstance().deleteByUrl(url);
                }


            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });

    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}
