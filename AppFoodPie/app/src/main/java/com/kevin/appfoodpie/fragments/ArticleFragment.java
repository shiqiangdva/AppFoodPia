package com.kevin.appfoodpie.fragments;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.WebSecondActivity;
import com.kevin.appfoodpie.adapters.ArticleAdapter;
import com.kevin.appfoodpie.values.DBTool;
import com.kevin.appfoodpie.values.Url;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends BaseFragment {

    private List<Url> data;
    private ListView lv;
    private ArticleAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_article;
    }

    @Override
    void initView(View view) {
        data = new ArrayList<>();
        lv = (ListView) view.findViewById(R.id.lv_article);
        adapter = new ArticleAdapter(getContext());
    }

    @Override
    void initData() {

        data = DBTool.getInstance().queryUrlAll();
        adapter.setData(data);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebSecondActivity.class);
                intent.putExtra("url",DBTool.getInstance().urlGet((position + 1) + ""));
                startActivity(intent);
            }
        });

    }


}
