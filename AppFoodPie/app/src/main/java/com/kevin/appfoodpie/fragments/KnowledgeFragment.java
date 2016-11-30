package com.kevin.appfoodpie.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.WebSecondActivity;
import com.kevin.appfoodpie.adapters.KnowledgeAdapter;
import com.kevin.appfoodpie.beans.KnowledgeBean;
import com.kevin.appfoodpie.values.MyApp;
import com.kevin.appfoodpie.values.MyClick;
import com.kevin.appfoodpie.values.NetHelper;
import com.kevin.appfoodpie.values.NetListener;
import com.kevin.appfoodpie.values.UrlValue;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeFragment extends BaseFragment{

//    private KnowledgeBean bean;
//    private ListView listView;
    private KnowledgeAdapter adapter;


    /**
     * 上拉刷新的控件
     * @return
     */
    private PullToRefreshListView mPullToRefreshListView;
    private List<KnowledgeBean.FeedsBean> lol;
    private int i = 1;

    @Override
    protected int setLayout() {
        return R.layout.fragment_knowledge;
    }

    @Override
    void initView(View view) {
//        listView = (ListView) view.findViewById(R.id.lv_fragment_knowledge);
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_fragment_knowledge);
        mPullToRefreshListView.setMode(Mode.BOTH);
    }

    @Override
    void initData() {
        // 初始化
        FirstUse();

        // 获取网络数据
        GetData();

    }

    private void GetData() {

        StartUrl(urlAll(1));


        mPullToRefreshListView.setAdapter(adapter);
        mPullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),WebSecondActivity.class);
                intent.putExtra("url",lol.get(position-1).getLink());
                startActivity(intent);
            }
        });

        mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                // 下拉刷新
                new PutDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                // 上拉加载
                new GetDataTask().execute();
            }
        });

    }

    public String urlAll(int i){
        String url1 = getArguments().getString("url1").toString();
        String url2 = getArguments().getString("url2").toString();

        return url1 + i + url2;
    }

    private void StartUrl(String u) {
//        String url = getArguments().getString("url").toString();
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                bean = gson.fromJson(response,KnowledgeBean.class);
//                adapter.setBean(bean);
//                listView.setAdapter(adapter);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(stringRequest);

        NetHelper.MyRequest(u, KnowledgeBean.class, new NetListener<KnowledgeBean>() {
            @Override
            public void successListener(KnowledgeBean response) {
//                lol = response.getFeeds();

                List<KnowledgeBean.FeedsBean> mid = response.getFeeds();
                if (lol == null) {
                    lol = mid;
                } else {
                    for (int i = 0; i < mid.size(); i++) {
                        lol.add(mid.get(i));
                    }
                }
                adapter.setData(lol);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

    }

    private void FirstUse() {
//        bean = new KnowledgeBean();
//        adapter = new KnowledgeAdapter(getContext());
        lol = new ArrayList<>();
        adapter = new KnowledgeAdapter(getContext());
    }

    public static KnowledgeFragment newInstance(String url1,String url2) {
        
        Bundle args = new Bundle();
        args.putString("url1",url1);
        args.putString("url2",url2);
        KnowledgeFragment fragment = new KnowledgeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // 上啦加载的异步任务
    private class GetDataTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            try {
                Thread.sleep(2000);
                i =i+1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            StartUrl(urlAll(integer));

            adapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullToRefreshListView.onRefreshComplete();
        }

    }

    // 下拉刷新的异步任务
    private class PutDataTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            try {
                Thread.sleep(2000);
//                if (i != 1){
//                    i = i - 1;
//                }
                i = 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            adapter.clean();
            StartUrl(urlAll(integer));

            adapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullToRefreshListView.onRefreshComplete();
        }

    }

    
}
