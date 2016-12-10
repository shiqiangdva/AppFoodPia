package com.kevin.appfoodpie;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.kevin.appfoodpie.adapters.HistoryListAdapter;
import com.kevin.appfoodpie.adapters.TenAdapter;
import com.kevin.appfoodpie.beans.TenBean;
import com.kevin.appfoodpie.values.DBTool;
import com.kevin.appfoodpie.values.DaoMaster;
import com.kevin.appfoodpie.values.DaoSession;
import com.kevin.appfoodpie.values.DividerItemDecoration;
import com.kevin.appfoodpie.values.Food;
import com.kevin.appfoodpie.values.FoodDao;
import com.kevin.appfoodpie.values.NetHelper;
import com.kevin.appfoodpie.values.NetListener;
import com.kevin.appfoodpie.values.NoScrollListView;
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
    private NoScrollListView lv;
    private HistoryListAdapter hisAdapter;
    private List<String> listName;
    private Food food;

    private MyBR br;

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
        lv = (NoScrollListView) findViewById(R.id.search_history_list);
        hisAdapter = new HistoryListAdapter(this);
        // 大保健数据库的各种初始化
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "FoodFile.db", null);
//        DaoMaster master = new DaoMaster(helper.getWritableDb());
//        DaoSession session = master.newSession();
//        foodDao = session.getFoodDao();

        br = new MyBR();
    }

    @Override
    void initData() {
        // 搜索键获取et内容
        SearchGo();

        // 下面的rv的数据
        RvData();

        // btn返回按钮点击事件
        BtnBackFinish();

        //数据路list///
//        final List<Food> list = foodDao.loadAll();
        final List<Food> list = DBTool.getInstance().queryAll();

        // 取反 让新的数据放在上面
        listName = new ArrayList<>();
//        hisAdapter.setData(list);
        for (int i = list.size(); i > 0; i--) {
            listName.add(list.get(i-1).getName());
        }
        hisAdapter.setData(listName);
        lv.setAdapter(hisAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = listName.get(position);
                Intent intent = new Intent(SearchActivity.this, SearchDetailsActivity.class);
                intent.putExtra("food", data);
                startActivity(intent);
            }
        });
        // 添加尾布局
        View view = getLayoutInflater().inflate(R.layout.foot_view, null);
        TextView tvFoot = (TextView) view.findViewById(R.id.tv_foot_trash);
        tvFoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hisAdapter.Clean();
                listName.clear();
                list.clear();
                FoodDelAll();
                hisAdapter.setData(listName);
            }
        });
        if (list.size() > 0){
            lv.addFooterView(view);
        }else {
            lv.removeFooterView(view);
        }


        /////////////
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.kevin.appfoodpie.MY_BR");
        registerReceiver(br,intentFilter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
    }

    public void newUi(){
        //数据路list///
//        final List<Food> list = foodDao.loadAll();
        final List<Food> list = DBTool.getInstance().queryAll();
        // 取反 让新的数据放在上面
        listName = new ArrayList<>();
//        hisAdapter.setData(list);
        for (int i = list.size(); i > 0; i--) {
            listName.add(list.get(i-1).getName());
        }

        hisAdapter.Clean();
//        listName.clear();
        hisAdapter.setData(listName);
//        hisAdapter.Clean();
        lv.setAdapter(hisAdapter);
    }

    class MyBR extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra("My_BR");
            if (data.equals("刷新数据")){
                newUi();
            }
        }
    }

    public void FoodDelAll(){
//        Food foodDel = foodDao.queryBuilder().where(FoodDao.Properties.Name.eq(name)).build().unique();
//        foodDao.deleteAll();
        DBTool.getInstance().deleteAll();
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
                GridLayoutManager manager = new GridLayoutManager(SearchActivity.this, 2);
                recyclerView.setLayoutManager(manager);
                adapter.setTenClick(SearchActivity.this);
                // 加线
                recyclerView.addItemDecoration(new DividerItemDecoration(SearchActivity.this, DividerItemDecoration.VERTICAL_LIST));
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
//                // 数据库查重并添加
//                Food food = foodDao.queryBuilder().where(FoodDao.Properties.Name.eq(etData)).build().unique();
//                if (food == null){
//                    Food newData = new Food(null,etData);
//                    foodDao.insert(newData);
//                }else {
//                    Food newData = new Food(null,etData);
//                    foodDao.deleteByKey(food.getId());
//                    foodDao.insert(newData);
//                }
                if (!DBTool.getInstance().isSave(etData)){
                    Food newData = new Food(null,etData);
                    DBTool.getInstance().insertPerson(newData);
                }else {
                    Food newData = new Food(null,etData);
                    DBTool.getInstance().deleteByName(etData);
                    DBTool.getInstance().insertPerson(newData);
                }

                Intent intent = new Intent(SearchActivity.this, SearchDetailsActivity.class);
                intent.putExtra("food", etData);
                startActivity(intent);
            }
        });
    }

    @Override
    public void TenListener(String data) {
//        // 查重并添加数据
//        food = foodDao.queryBuilder().where(FoodDao.Properties.Name.eq(data)).build().unique();
//        if (food == null){
//            Food newData = new Food(null,data);
//            foodDao.insert(newData);
//        }else {
//            Food newData = new Food(null,data);
//            foodDao.deleteByKey(food.getId());
//            foodDao.insert(newData);
//        }
        if (!DBTool.getInstance().isSave(data)){
            Food newData = new Food(null,data);
            DBTool.getInstance().insertPerson(newData);
        }else {
            Food newData = new Food(null,data);
            DBTool.getInstance().deleteByName(data);
            DBTool.getInstance().insertPerson(newData);
        }


        Intent intent = new Intent(SearchActivity.this, SearchDetailsActivity.class);
        intent.putExtra("food", data);
        startActivity(intent);
    }
}
