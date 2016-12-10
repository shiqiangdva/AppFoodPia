package com.kevin.appfoodpie.fragments;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.adapters.CFoodAdapter;
import com.kevin.appfoodpie.values.DBTool;
import com.kevin.appfoodpie.values.DataMore;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CFoodFragment extends BaseFragment {

    private List<DataMore> data;
    private CFoodAdapter adapter;
    private ListView lv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_cfood;
    }

    @Override
    void initView(View view) {
        data = new ArrayList<>();
        adapter = new CFoodAdapter(getContext());
        lv = (ListView) view.findViewById(R.id.lv_food);
    }

    @Override
    void initData() {
        data = DBTool.getInstance().queryDataAll();
        adapter.setData(data);
        lv.setAdapter(adapter);
    }
}
