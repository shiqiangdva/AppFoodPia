package com.kevin.appfoodpie.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.ThreeActivity;
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

    private MyBR br;

    @Override
    protected int setLayout() {
        return R.layout.fragment_cfood;
    }

    @Override
    void initView(View view) {
        data = new ArrayList<>();
        adapter = new CFoodAdapter(getContext());
        lv = (ListView) view.findViewById(R.id.lv_food);

        br = new MyBR();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com");
        getActivity().registerReceiver(br,intentFilter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(br);
    }

    @Override
    void initData() {
        data = DBTool.getInstance().queryDataAll();
//        for (DataMore dataMore : data) {
//            Log.d("CFoodFragment", dataMore.getId() + " " + dataMore.getImg() + " " + dataMore.getName() + " " + dataMore.getDb()
//                    + " " + dataMore.getQk() + " " + dataMore.getSs() + " " + dataMore.getTs()
//                    + " " + dataMore.getZf());
//        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ThreeActivity.class);
                intent.putExtra("if","2");
                intent.putExtra("caloryQk",data.get(position).getQk());
                intent.putExtra("proteinDbz",data.get(position).getDb());
                intent.putExtra("fatZf",data.get(position).getZf());
                intent.putExtra("carbohydrateTs",data.get(position).getTs());
                intent.putExtra("fiber_dietarySsxw",data.get(position).getSs());
                intent.putExtra("imgT",data.get(position).getImg());
                intent.putExtra("nameT",data.get(position).getName());
                startActivity(intent);

            }
        });

        adapter.setData(data);
        lv.setAdapter(adapter);
    }

    class MyBR extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getStringExtra("SX").equals("SX")){
                initData();
            }
        }
    }

}
