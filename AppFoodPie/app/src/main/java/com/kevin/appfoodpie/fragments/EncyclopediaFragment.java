package com.kevin.appfoodpie.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kevin.appfoodpie.LibraryMoreActivity;
import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.adapters.GirdOneAdapter;
import com.kevin.appfoodpie.adapters.GirdThreeAdapter;
import com.kevin.appfoodpie.adapters.GirdTwoAdapter;
import com.kevin.appfoodpie.beans.EncyclopediaBean;
import com.kevin.appfoodpie.values.UrlValue;

/**
 * A simple {@link Fragment} subclass.
 */
public class EncyclopediaFragment extends BaseFragment {

    private EncyclopediaBean data;
    private GridView gvOne;
    private GridView gvTwo;
    private GridView gvThree;
    private GirdOneAdapter oneAdapter;
    private GirdTwoAdapter twoAdapter;
    private GirdThreeAdapter threeAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_encyclopedia;
    }

    @Override
    void initView(View view) {
        gvOne = (GridView) view.findViewById(R.id.library_gird1);
        gvTwo = (GridView) view.findViewById(R.id.library_gird2);
        gvThree = (GridView) view.findViewById(R.id.library_gird3);
    }

    @Override
    void initData() {
        // 初始化
        initClass();
        // 请求网络
        initUrl();
    }

    private void initUrl() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(UrlValue.ENC_DATA_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 卡片1数据设置
                OneData(response);
                // 卡片2数据设置
                TwoData(response);
                // 卡片3数据设置
                ThreeData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void ThreeData(String response) {
        Gson gson = new Gson();
        data = gson.fromJson(response, EncyclopediaBean.class);
        threeAdapter.setBean(data);
        gvThree.setAdapter(threeAdapter);
        gvThree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取GridView的点击id,作为网址拼接来使用

                Intent intent = new Intent(getActivity(), LibraryMoreActivity.class);
                intent.putExtra("name",data.getGroup().get(2).getCategories().get(position).getName());
                intent.putExtra("key",data.getGroup().get(2).getKind());// group
                intent.putExtra("id",data.getGroup().get(2).getCategories().get(position).getId()+"");//1
                Log.d("aaa", "urlId:" + data.getGroup().get(2).getCategories().get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void TwoData(String response) {
        Gson gson = new Gson();
        data = gson.fromJson(response, EncyclopediaBean.class);
        twoAdapter.setBean(data);
        gvTwo.setAdapter(twoAdapter);
        gvTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取GridView的点击id,作为网址拼接来使用

                Intent intent = new Intent(getActivity(), LibraryMoreActivity.class);
                intent.putExtra("name",data.getGroup().get(1).getCategories().get(position).getName());
                intent.putExtra("key",data.getGroup().get(1).getKind());// group
                intent.putExtra("id",data.getGroup().get(1).getCategories().get(position).getId()+"");//1
                Log.d("aaa", "urlId:" + data.getGroup().get(1).getCategories().get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void OneData(String response) {
        Gson gson = new Gson();
        data = gson.fromJson(response, EncyclopediaBean.class);
        oneAdapter.setBean(data);
        gvOne.setAdapter(oneAdapter);
        gvOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取GridView的点击id,作为网址拼接来使用

                Intent intent = new Intent(getActivity(), LibraryMoreActivity.class);
                intent.putExtra("name",data.getGroup().get(0).getCategories().get(position).getName());
                intent.putExtra("key",data.getGroup().get(0).getKind());// group
                intent.putExtra("id",data.getGroup().get(0).getCategories().get(position).getId()+"");//1
                Log.d("aaa", "urlId:" + data.getGroup().get(0).getCategories().get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void initClass() {
        data = new EncyclopediaBean();
        oneAdapter = new GirdOneAdapter(getContext());
        twoAdapter = new GirdTwoAdapter(getContext());
        threeAdapter = new GirdThreeAdapter(getContext());
    }

}
