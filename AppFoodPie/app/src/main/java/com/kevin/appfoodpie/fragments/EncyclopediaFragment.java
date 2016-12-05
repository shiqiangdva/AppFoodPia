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
import com.kevin.appfoodpie.values.NetHelper;
import com.kevin.appfoodpie.values.NetListener;
import com.kevin.appfoodpie.values.SerInfo;
import com.kevin.appfoodpie.values.UrlValue;

import java.util.ArrayList;

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

        NetHelper.MyRequest(UrlValue.ENC_DATA_URL, EncyclopediaBean.class, new NetListener<EncyclopediaBean>() {
            @Override
            public void successListener(EncyclopediaBean response) {
                data = response;

                GirdViewThree();

                GirdViewTwo();

                GirdViewOne();


            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    private void GirdViewThree() {
        //
        threeAdapter.setBean(data);
        gvThree.setAdapter(threeAdapter);
        gvThree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取GridView的点击id,作为网F址U拼C接K来使用

                Intent intent = new Intent(getActivity(), LibraryMoreActivity.class);
                intent.putExtra("name",data.getGroup().get(2).getCategories().get(position).getName());
                intent.putExtra("key",data.getGroup().get(2).getKind());// group
                intent.putExtra("id",data.getGroup().get(2).getCategories().get(position).getId()+"");//1
                Log.d("aaa", "urlId:" + data.getGroup().get(2).getCategories().get(position).getId());
                // pop
                intent.putExtra("p",position);
                intent.putExtra("popCount",data.getGroup().get(2).getCategories().get(position).getSub_category_count());
                startActivity(intent);
            }
        });
    }

    private void GirdViewTwo() {
        //
        twoAdapter.setBean(data);
        gvTwo.setAdapter(twoAdapter);
        gvTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取GridView的点击id,作为网址拼接来使用
                // fuck!!!

                Intent intent = new Intent(getActivity(), LibraryMoreActivity.class);
                intent.putExtra("name",data.getGroup().get(1).getCategories().get(position).getName());
                intent.putExtra("key",data.getGroup().get(1).getKind());// group
                intent.putExtra("id",data.getGroup().get(1).getCategories().get(position).getId()+"");//1
                Log.d("aaa", "urlId:" + data.getGroup().get(1).getCategories().get(position).getId());
                // pop
                intent.putExtra("p",position);
                intent.putExtra("popCount",data.getGroup().get(1).getCategories().get(position).getSub_category_count());
                startActivity(intent);
            }
        });
    }

    private void GirdViewOne() {
        //
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
                // pop
                intent.putExtra("p",position);
                intent.putExtra("popCount",data.getGroup().get(0).getCategories().get(position).getSub_category_count());


//                SerInfo serInfo = new SerInfo();
//                Bundle bundle = new Bundle();
//                Intent ins = new Intent(getActivity(),LibraryMoreActivity.class);
//                for (int i = 0; i < data.getGroup().get(0).getCategories().get(position).getSub_category_count(); i++) {
//                    serInfo.setName(data.getGroup().get(0).getCategories().get(position).getSub_categories().get(i).getName());
//                    serInfo.setId(data.getGroup().get(0).getCategories().get(position).getSub_categories().get(i).getId());
//                    bundle.putSerializable(i+"",serInfo);
//                    ins.putExtras(bundle);
//                    startActivity(ins);
//                }

                startActivity(intent);
            }
        });
    }

//    private void ThreeData(String response) {
////        Gson gson = new Gson();
////        data = gson.fromJson(response, EncyclopediaBean.class);
//        threeAdapter.setBean(data);
//        gvThree.setAdapter(threeAdapter);
//        gvThree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // 获取GridView的点击id,作为网F址U拼C接K来使用
//
//                Intent intent = new Intent(getActivity(), LibraryMoreActivity.class);
//                intent.putExtra("name",data.getGroup().get(2).getCategories().get(position).getName());
//                intent.putExtra("key",data.getGroup().get(2).getKind());// group
//                intent.putExtra("id",data.getGroup().get(2).getCategories().get(position).getId()+"");//1
//                Log.d("aaa", "urlId:" + data.getGroup().get(2).getCategories().get(position).getId());
//                // pop
//                intent.putExtra("p",position);
//                intent.putExtra("popCount",data.getGroup().get(2).getCategories().get(position).getSub_category_count());
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void TwoData(String response) {
//        Gson gson = new Gson();
//        data = gson.fromJson(response, EncyclopediaBean.class);
//        twoAdapter.setBean(data);
//        gvTwo.setAdapter(twoAdapter);
//        gvTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // 获取GridView的点击id,作为网址拼接来使用
//                // fuck!!!
//
//                Intent intent = new Intent(getActivity(), LibraryMoreActivity.class);
//                intent.putExtra("name",data.getGroup().get(1).getCategories().get(position).getName());
//                intent.putExtra("key",data.getGroup().get(1).getKind());// group
//                intent.putExtra("id",data.getGroup().get(1).getCategories().get(position).getId()+"");//1
//                Log.d("aaa", "urlId:" + data.getGroup().get(1).getCategories().get(position).getId());
//                // pop
//                intent.putExtra("p",position);
//                intent.putExtra("popCount",data.getGroup().get(1).getCategories().get(position).getSub_category_count());
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void OneData(String response) {
//        Gson gson = new Gson();
//        data = gson.fromJson(response, EncyclopediaBean.class);
//        oneAdapter.setBean(data);
//        gvOne.setAdapter(oneAdapter);
//        gvOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // 获取GridView的点击id,作为网址拼接来使用
//
//                Intent intent = new Intent(getActivity(), LibraryMoreActivity.class);
//                intent.putExtra("name",data.getGroup().get(0).getCategories().get(position).getName());
//                intent.putExtra("key",data.getGroup().get(0).getKind());// group
//                intent.putExtra("id",data.getGroup().get(0).getCategories().get(position).getId()+"");//1
//                Log.d("aaa", "urlId:" + data.getGroup().get(0).getCategories().get(position).getId());
//                // pop
//                intent.putExtra("p",position);
//                intent.putExtra("popCount",data.getGroup().get(0).getCategories().get(position).getSub_category_count());
//
////                SerInfo serInfo = new SerInfo();
////                Bundle bundle = new Bundle();
////                Intent ins = new Intent(getActivity(),LibraryMoreActivity.class);
////                for (int i = 0; i < data.getGroup().get(0).getCategories().get(position).getSub_category_count(); i++) {
////                    serInfo.setName(data.getGroup().get(0).getCategories().get(position).getSub_categories().get(i).getName());
////                    serInfo.setId(data.getGroup().get(0).getCategories().get(position).getSub_categories().get(i).getId());
////                    bundle.putSerializable(i+"",serInfo);
////                    ins.putExtras(bundle);
////                    startActivity(ins);
////                }
//
//                startActivity(intent);
//            }
//        });
//    }

    private void initClass() {
        data = new EncyclopediaBean();
        oneAdapter = new GirdOneAdapter(getContext());
        twoAdapter = new GirdTwoAdapter(getContext());
        threeAdapter = new GirdThreeAdapter(getContext());
    }

}
