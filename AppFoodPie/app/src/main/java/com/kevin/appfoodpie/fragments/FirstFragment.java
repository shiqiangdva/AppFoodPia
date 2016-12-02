package com.kevin.appfoodpie.fragments;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.android.volley.VolleyError;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.SecondActivity;
import com.kevin.appfoodpie.adapters.FirstAdapter;
import com.kevin.appfoodpie.beans.FirstBean;
import com.kevin.appfoodpie.values.MyListener;
import com.kevin.appfoodpie.values.NetHelper;
import com.kevin.appfoodpie.values.NetListener;
import com.kevin.appfoodpie.values.UrlValue;
import com.kevin.appfoodpie.WebActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends BaseFragment implements MyListener {

//    private RecyclerView recyclerView;
    private FirstBean firstBean;
    private FirstAdapter adapter;

    private LRecyclerView recyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<FirstBean.FeedsBean> lol;
    private int i = 1;

    @Override
    protected int setLayout() {
        return R.layout.fragment_first;
    }

    @Override
    void initView(View view) {
//        recyclerView = (RecyclerView) view.findViewById(R.id.first_fragment_rv);
        recyclerView = (LRecyclerView) view.findViewById(R.id.first_fragment_rv);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
    }

    @Override
    void initData() {

        // 初始化
        firstUse();

        // 网络请求数据获取
        getData();
    }

    private void firstUse() {
        adapter = new FirstAdapter(getContext());
        lol = new ArrayList<>();
    }

    public String urlAll(int i) {
        return UrlValue.DVI_ONE + i + UrlValue.DVI_TWO;
    }

    private void getData() {

        StartUrl(urlAll(1));
        adapter.setMyListener(this);
        // 系统的Adapter
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        recyclerView.setAdapter(lRecyclerViewAdapter);


        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(manager);

        // 下拉刷新
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                i = 1;
                adapter.clean();
                StartUrl(urlAll(i));
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
                StartUrl(urlAll(i));
            }
        });

    }

    private void StartUrl(String url) {
        NetHelper.MyRequest(url, FirstBean.class, new NetListener<FirstBean>() {
            @Override
            public void successListener(FirstBean response) {
                lol = response.getFeeds();
                adapter.setData(lol);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    @Override
    public void FirstClick(String link, String card_image, String title,
                String publisher, String publisher_avatar, String description, int like_ct,int types) {
        if (types == 6){
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("link",link);
            startActivity(intent);
        }else {
            Intent intent = new Intent(getActivity(),SecondActivity.class);
            intent.putExtra("card_image",card_image);
            intent.putExtra("title",title);
            intent.putExtra("description",description);
            intent.putExtra("publisher_avatar",publisher_avatar);
            intent.putExtra("publisher",publisher);
            intent.putExtra("like_ct",like_ct);
//            Log.d("fff", "like_ct:" + like_ct);
            startActivity(intent);
        }
    }
}
