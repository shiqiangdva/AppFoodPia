package com.kevin.appfoodpie;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.kevin.appfoodpie.adapters.LibraryMoreAdapter;
import com.kevin.appfoodpie.beans.LibraryMoreBean;
import com.kevin.appfoodpie.values.NetHelper;
import com.kevin.appfoodpie.values.NetListener;
import com.kevin.appfoodpie.values.UrlValue;

import java.util.ArrayList;
import java.util.List;

public class LibraryMoreActivity extends BaseActivity {
    private LRecyclerView recyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<LibraryMoreBean.FoodsBean> data;
    private LibraryMoreAdapter adapter;
    private int i = 1;
    private ImageButton imgBack;
    private TextView tvName;
    private String nameTv;

    @Override
    int setLayout() {
        return R.layout.activity_library_more;
    }

    @Override
    void initView() {
        recyclerView = (LRecyclerView) findViewById(R.id.library_more_rv);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        imgBack = (ImageButton) findViewById(R.id.library_more_return);
        tvName = (TextView) findViewById(R.id.library_more_title);
    }

    @Override
    void initData() {
        // 数据初始化
        FirstData();
        // 获取数据
        getData();
        // 返回按钮的点击事件
        Click();
    }

    private void Click() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvName.setText(nameTv);
    }

    private void getData() {
        StartUrl(UrlAll(1));
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        recyclerView.setAdapter(lRecyclerViewAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        // 下拉刷新
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                i = 1;
                adapter.clean();
                StartUrl(UrlAll(i));
//                recyclerView.setPullRefreshEnabled(true);
                recyclerView.refreshComplete();
                lRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
        // 上拉加载
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                i = i + 1;
                StartUrl(UrlAll(i));
            }
        });

    }

    private void FirstData() {
        adapter = new LibraryMoreAdapter(this);
        data = new ArrayList<>();
    }

    public String UrlAll(int i){
        Intent intent = getIntent();
        String urlG = intent.getStringExtra("key");
        String urlId = intent.getStringExtra("id");
        nameTv = intent.getStringExtra("name");
        Log.d("aaa", urlId+"");
        Log.d("think", UrlValue.FUCKONE + urlG + UrlValue.FUCKTWO + urlId + UrlValue.FUCKTHREE + i + UrlValue.FUCKFOUR);
        return UrlValue.FUCKONE + urlG + UrlValue.FUCKTWO + urlId + UrlValue.FUCKTHREE + i + UrlValue.FUCKFOUR;
    }

    private void StartUrl(String url) {
        NetHelper.MyRequest(url, LibraryMoreBean.class, new NetListener<LibraryMoreBean>() {
            @Override
            public void successListener(LibraryMoreBean response) {
                data = response.getFoods();
                adapter.setData(data);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }



}
