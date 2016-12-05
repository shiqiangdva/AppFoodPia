package com.kevin.appfoodpie;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.kevin.appfoodpie.adapters.HistoryListAdapter;
import com.kevin.appfoodpie.adapters.TenAdapter;
import com.kevin.appfoodpie.beans.TenBean;
import com.kevin.appfoodpie.values.DaoMaster;
import com.kevin.appfoodpie.values.DaoSession;
import com.kevin.appfoodpie.values.DividerItemDecoration;
import com.kevin.appfoodpie.values.Food;
import com.kevin.appfoodpie.values.FoodDao;
import com.kevin.appfoodpie.values.NetHelper;
import com.kevin.appfoodpie.values.NetListener;
import com.kevin.appfoodpie.values.TenClick;
import com.kevin.appfoodpie.values.UrlValue;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements TenClick {

    private EditText etSearch;
    private ImageButton btnSearch;
    private TenAdapter adapter;
    private RecyclerView recyclerView;
    private TenBean bean;
    private ImageButton btnBack;
    private String etData;
    private FoodDao foodDao;
    private ListView lv;
    private HistoryListAdapter hisAdapter;

    @Override
    int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    void initView() {
        etSearch = (EditText) findViewById(R.id.search_edt);
        btnSearch = (ImageButton) findViewById(R.id.search_search);
        adapter = new TenAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.ten_rvw);
        bean = new TenBean();
        btnBack = (ImageButton) findViewById(R.id.search_ret);
        lv = (ListView) findViewById(R.id.search_history_list);
        hisAdapter = new HistoryListAdapter(this);
        // 大保健数据库的各种初始化
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"FoodFile.db",null);
        DaoMaster master = new DaoMaster(helper.getWritableDb());
        DaoSession session = master.newSession();
        foodDao = session.getFoodDao();

    }

    @Override
    void initData() {
        // 搜索键获取et内容
        SearchGo();

        // 下面的rv的数据
        RvData();

        // btn返回按钮点击事件
        BtnBackFinish();

        /////
        List<Food> list = foodDao.loadAll();
        hisAdapter.setData(list);
        for (Food food : list) {
            Log.d("www", food.getName());
        }
        lv.setAdapter(hisAdapter);
    }

    private void BtnBackFinish() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void RvData() {
        NetHelper.MyRequest(UrlValue.TEN, TenBean.class, new NetListener<TenBean>() {
            @Override
            public void successListener(TenBean response) {
                bean = response;
                adapter.setBean(bean);
                recyclerView.setAdapter(adapter);
                GridLayoutManager manager = new GridLayoutManager(SearchActivity.this,2);
                recyclerView.setLayoutManager(manager);
                adapter.setTenClick(SearchActivity.this);
                // 加线
                recyclerView.addItemDecoration(new DividerItemDecoration(SearchActivity.this,DividerItemDecoration.VERTICAL_LIST));
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    private void SearchGo() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etData = etSearch.getText().toString();
                // 数据库查重并添加
                Food food = foodDao.queryBuilder().where(FoodDao.Properties.Name.eq(etData)).build().unique();
                if (food == null){
                    Food newData = new Food(null,etData);
                    foodDao.insert(newData);
                }

                Intent intent = new Intent(SearchActivity.this,SearchDetailsActivity.class);
                intent.putExtra("food", etData);
                startActivity(intent);
            }
        });
    }

    @Override
    public void TenListener(String data) {
        // 查重并添加数据
        Food food = foodDao.queryBuilder().where(FoodDao.Properties.Name.eq(data)).build().unique();
        if (food == null){
            Food newData = new Food(null,data);
            foodDao.insert(newData);
        }

        Intent intent = new Intent(SearchActivity.this,SearchDetailsActivity.class);
        intent.putExtra("food",data);
        startActivity(intent);
    }
}
