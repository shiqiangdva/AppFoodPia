package com.kevin.appfoodpie;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.kevin.appfoodpie.adapters.CompareAdapter;
import com.kevin.appfoodpie.beans.CompareBean;
import com.kevin.appfoodpie.beans.EBean;
import com.kevin.appfoodpie.beans.ThreeBean;
import com.kevin.appfoodpie.values.ComClick;
import com.kevin.appfoodpie.values.NetHelper;
import com.kevin.appfoodpie.values.NetListener;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class CompareActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imgLeft, imgRight;
    private String type;
    private String code;
    private String name;

    private CompareBean data;
    private CompareAdapter adapter;
    private ListView lv;

    private int i;
    private String urlImg;
    private static boolean flag = true;

    private String img1;
    private String img2;
    private ImageView backBtn;

    @Override
    int setLayout() {
        return R.layout.activity_compare;
    }

    @Override
    void initView() {
        imgLeft = (ImageView) findViewById(R.id.com_left);
        imgRight = (ImageView) findViewById(R.id.com_right);
        EventBus.getDefault().register(this);

        data = new CompareBean();
        adapter = new CompareAdapter(this);
        lv = (ListView) findViewById(R.id.com_lv);

        backBtn = (ImageView) findViewById(R.id.com_back);
    }

    @Override
    void initData() {
        imgLeft.setOnClickListener(this);
        imgRight.setOnClickListener(this);

        StartUrl(UrlAll());
        lv.setAdapter(adapter);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        if (img1 != null){
//            Picasso.with(CompareActivity.this).load(img1).into(imgLeft);
//        }
//        if (img2 != null){
//            Picasso.with(CompareActivity.this).load(img2).into(imgRight);
//        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(EBean bean) {
        type = bean.getType6();
        code = bean.getCode6();
        name = bean.getName6();
        urlImg = bean.getImg6();
        StartUrl(UrlAll());
        Log.e("CompareActivity", type + "--" + code + "---" + name + "---" + urlImg);
    }

    private void StartUrl(String url) {
        NetHelper.MyRequest(url, CompareBean.class, new NetListener<CompareBean>() {
            @Override
            public void successListener(CompareBean response) {
                data = response;
//                Log.d("jjjj", "data:" + data);

                if (flag) {
                    adapter.setI(1);
//                    img1 = urlImg;

                    Picasso.with(CompareActivity.this).load(urlImg).into(imgLeft);
                } else {
                    adapter.setI(2);
//                    img2 = urlImg;
                    Picasso.with(CompareActivity.this).load(urlImg).into(imgRight);
                }
                adapter.setData(data);

            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    public String UrlAll() {
        Intent intent = getIntent();
//        Log.d("jjjj", "http://food.boohee.com/fb/v1/" + type + "/" + code + "/mode_show?token=" + name);
//        return "http://food.boohee.com/fb/v1/" + type + "s/" + code + "/mode_show?token=" + name;

        Log.d("jjjj", "http://food.boohee.com/fb/v1/" + intent.getStringExtra("22")
                + "s/" + intent.getStringExtra("11") + "/mode_show?token=" + intent.getStringExtra("33"));
//        urlImg = intent.getStringExtra("44");
//        Log.d("CompareActivity", urlImg);
        Log.d("fuck you", "http://food.boohee.com/fb/v1/" + type
                + "s/" + code + "/mode_show?token=" + name);
        return "http://food.boohee.com/fb/v1/" + type
                + "s/" + code + "/mode_show?token=" + name;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.com_left:

                flag = true;

                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.com_right:

                flag = false;

                Intent intent1 = new Intent(this, SearchActivity.class);
                startActivity(intent1);
                break;
        }
    }


}
