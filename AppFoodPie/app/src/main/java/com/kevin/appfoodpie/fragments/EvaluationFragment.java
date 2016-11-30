package com.kevin.appfoodpie.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.kevin.appfoodpie.adapters.EvaluationAdapter;
import com.kevin.appfoodpie.beans.EvaluationBean;
import com.kevin.appfoodpie.beans.LBBean;
import com.kevin.appfoodpie.values.NetHelper;
import com.kevin.appfoodpie.values.NetListener;
import com.kevin.appfoodpie.values.UrlValue;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluationFragment extends BaseFragment {

//        private EvaluationBean bean;
//    private ListView listView;
    private EvaluationAdapter adapter;
    private int i = 1;
    /**
     * 刷新的控件
     */
    private PullToRefreshListView mPullRefreshListView;
    private List<EvaluationBean.FeedsBean> lol;

    // 轮播图的相关定义
    private ArrayList<String> pics = new ArrayList<>();
    private LBBean lbBean;
    private Banner banner;
    private View viewHead;

    @Override
    protected int setLayout() {
        return R.layout.fragment_evaluation;
    }

    @Override
    void initView(View view) {
//        listView = (ListView) view.findViewById(R.id.lv_fragment_eva);

        // 头布局的View绑定id
        viewHead = LayoutInflater.from(getContext()).inflate(R.layout.head_lb, null);
        banner = (Banner) viewHead.findViewById(R.id.banner);

        /**
         * 刷新的控件
         */
        mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_fragment_eva);
        mPullRefreshListView.setMode(Mode.BOTH);//

        lol = new ArrayList<>();
        adapter = new EvaluationAdapter(getContext());

    }

    @Override
    void initData() {
        // 头布局中轮播图的设置数据
        HeadViewData();
        // listView中的数据解析获取
        ListViewData();

    }

    // listView 中的数据
    private void ListViewData() {
        StartUrl(Url_all(1));

        mPullRefreshListView.setAdapter(adapter);

        mPullRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {
            // 下拉刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                new PutDataTask().execute();
            }

            // 上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                new GetDataTask().execute();
            }
        });
        mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = lol.get(position).getLink();
                Intent intent = new Intent(getActivity(), WebSecondActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });


    }


    private void StartUrl(String url) {
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        StringRequest stringRequest = new StringRequest(UrlValue.EVA_FRAGMENT_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                bean = gson.fromJson(response,EvaluationBean.class);
////                adapter = new EvaluationAdapter(getContext());
////                adapter.setEvaluationBean(bean);
//                lol = bean.getFeeds();
//                adapter.setData(lol);
//
////                listView.addHeaderView(viewHead);
////
////                listView.setAdapter(adapter);
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(stringRequest);

        NetHelper.MyRequest(url, EvaluationBean.class, new NetListener<EvaluationBean>() {
            @Override
            public void successListener(EvaluationBean response) {
//                lol = response.getFeeds();

                List<EvaluationBean.FeedsBean> mid = response.getFeeds();
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


    private void HeadViewData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(UrlValue.LUN_BO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                lbBean = gson.fromJson(response, LBBean.class);
                for (int i = 0; i < lbBean.getData().getBanners().size(); i++) {
                    // 向集合中添加轮播图的网址
                    pics.add(lbBean.getData().getBanners().get(i).getImage_url());
                }
                Log.d("Sysout", "pics.size():" + pics.size());
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                banner.setImageLoader(new GlideImageLoader());
                banner.setImages(pics);
                banner.setBannerAnimation(Transformer.DepthPage);
                banner.isAutoPlay(true);
                banner.setDelayTime(3000);
                banner.setIndicatorGravity(BannerConfig.CENTER);
                banner.start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Sysout", "error" + error.getMessage());
            }
        });
        queue.add(request);
    }

    // 网址拼接的方法
    public String Url_all(int i) {
        return UrlValue.AVI_ONE + i + UrlValue.AVI_TWO;
    }

    // 下拉刷新的异步任务
    private class PutDataTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            try {
                Thread.sleep(2000);
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
            StartUrl(Url_all(integer));

            adapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();
        }

    }

    // 上啦加载的异步任务
    private class GetDataTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            try {
                Thread.sleep(2000);
                i = i + 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            StartUrl(Url_all(integer));

            adapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();
        }

    }

}
