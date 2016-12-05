package com.kevin.appfoodpie;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.google.gson.Gson;
import com.kevin.appfoodpie.adapters.LibraryMoreAdapter;
import com.kevin.appfoodpie.adapters.PopAdapter;
import com.kevin.appfoodpie.adapters.PopMoreAdapter;
import com.kevin.appfoodpie.beans.EncyclopediaBean;
import com.kevin.appfoodpie.beans.FirstBean;
import com.kevin.appfoodpie.beans.LibraryMoreBean;
import com.kevin.appfoodpie.beans.PopBean;
import com.kevin.appfoodpie.values.NetHelper;
import com.kevin.appfoodpie.values.NetListener;
import com.kevin.appfoodpie.values.PopClick;
import com.kevin.appfoodpie.values.SerInfo;
import com.kevin.appfoodpie.values.UrlValue;

import java.util.ArrayList;
import java.util.List;

public class LibraryMoreActivity extends BaseActivity implements View.OnClickListener, PopClick {
    private LRecyclerView recyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
//    private List<LibraryMoreBean.FoodsBean> data;
    private LibraryMoreAdapter adapter;
    private int i = 1;
    private ImageButton imgBack;
    private TextView tvName;
    private String nameTv;
    private PopupWindow popupWindow, popupWindowMore;
    private ArrayList<String> dataPop;
    private ListView listView;

    private EncyclopediaBean bean;
    private Intent intent;
    private Button btnPop;
    private PopBean popBean;
    private ImageButton imgBtn;
    private String urlG;
    private String urlId;
    private int count;
    private int postion;
    private ArrayList<Integer> countId;

    @Override
    int setLayout() {
        return R.layout.activity_library_more;
    }

    @Override
    void initView() {
        recyclerView = (LRecyclerView) findViewById(R.id.library_more_rv);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        imgBack = (ImageButton) findViewById(R.id.library_more_return);
        tvName = (TextView) findViewById(R.id.library_more_title);
        imgBtn = (ImageButton) findViewById(R.id.library_more_nutritional_element_ib);
    }

    @Override
    void initData() {
        // 数据初始化
        FirstData();
        // 获取数据
        getData();
        // 返回按钮的点击事件
        Click();
        // pop的Data
        popWinData();

        // 大的pop的Data
        popMoreData();


    }

    private void popMoreData() {
        /**
         *
         */
        popupWindowMore = new PopupWindow(this);
        popupWindowMore.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindowMore.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = getLayoutInflater().inflate(R.layout.pop_rv, null);
        popupWindowMore.setContentView(view);
        popupWindowMore.setOutsideTouchable(true);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.pop_lrv);

        popBean = new PopBean();
        NetHelper.MyRequest(UrlValue.POP, PopBean.class, new NetListener<PopBean>() {
            @Override
            public void successListener(PopBean response) {
                popBean = response;
                final PopMoreAdapter moreAdapter = new PopMoreAdapter(LibraryMoreActivity.this);
                moreAdapter.setData(popBean);
//                lRecyclerViewAdapter = new LRecyclerViewAdapter(moreAdapter);
                recyclerView.setAdapter(moreAdapter);

                GridLayoutManager manager = new GridLayoutManager(LibraryMoreActivity.this, 3);
                recyclerView.setLayoutManager(manager);
                moreAdapter.setPopClick(LibraryMoreActivity.this);
                imgBtn.setOnClickListener(LibraryMoreActivity.this);

            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    private void popWinData() {
        popupWindow = new PopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = getLayoutInflater().inflate(R.layout.item_pop, null);
        popupWindow.setContentView(view);
        // 点击外面让popup消失
        popupWindow.setOutsideTouchable(true);

        listView = (ListView) view.findViewById(R.id.lv_pop);
        dataPop = new ArrayList<>();

        NetHelper.MyRequest(UrlValue.ENC_DATA_URL, EncyclopediaBean.class, new NetListener<EncyclopediaBean>() {
            @Override
            public void successListener(EncyclopediaBean response) {
                bean = response;
                PopAdapter popAdapter = new PopAdapter(LibraryMoreActivity.this);
                postion = intent.getIntExtra("p", 0);
                String group = intent.getStringExtra("key");
                count = intent.getIntExtra("popCount", 0);
                Log.d("lol", postion + "," + group + "," + count);
                if (group.equals("group")) {
                    dataPop.add("全部");
                    // 存name 到一个集合中
                    for (int i1 = 0; i1 < count; i1++) {
//                        Log.d("sb", bean.
//                                getGroup().
//                                get(0).
//                                getCategories().
//                                get(postion).
//                                getSub_categories().
//                                get(i1).
//                                getName());
                        dataPop.add(bean.getGroup().get(0).getCategories().get(postion).getSub_categories().get(i1).getName());
                    }
                    /************/
                    countId = new ArrayList<>();
                    // 把id 做循环 存到 一个集合中
                    for (int i2 = 0; i2 < count; i2++) {
                        countId.add(bean.getGroup().get(0).getCategories().get(postion).getSub_categories().get(i2).getId());
                        Log.d("jj", "bean.getGroup().get(0).getCategories().get(postion).getSub_categories().get(i2).getId():" + bean.getGroup().get(0).getCategories().get(postion).getSub_categories().get(i2).getId());
                    }
                    /************/

                } else if (group.equals("brand")) {
                    dataPop.add("无");
                    for (int i1 = 0; i1 < count; i1++) {
                        Log.d("lol", "我走到了这里");
                        dataPop.add(bean.getGroup().get(1).getCategories().get(postion).getSub_categories().get(i1).getName());
                    }
                } else {
                    dataPop.add("无");
                    for (int i1 = 0; i1 < count; i1++) {
                        dataPop.add(bean.getGroup().get(2).getCategories().get(postion).getSub_categories().get(i1).getName());
                    }
                }

                popAdapter.setData(dataPop);
                listView.setAdapter(popAdapter);
/*************/
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0){
                            adapter.clean();
                            StartUrl(UrlAll(1));
                        }else {
                            adapter.clean();
                            StartUrl(UrlPop(countId.get(position - 1), i));
                        }

//                        recyclerView.setAdapter(lRecyclerViewAdapter);
                        Toast.makeText(LibraryMoreActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });
/*************/

                btnPop = (Button) findViewById(R.id.library_more_all);
                btnPop.setOnClickListener(LibraryMoreActivity.this);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    private void Click() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvName.setText(nameTv);
    }

    private void getData() {
        StartUrl(UrlAll(1));
        recyclerView.setAdapter(lRecyclerViewAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        // 下拉刷新
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                i = 1;
                adapter.clean();
                StartUrl(UrlAll(i));
//                recyclerView.setPullRefreshEnabled(true);
                recyclerView.refreshComplete();
//                lRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
        // 上拉加载
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                i = i + 1;
                StartUrl(UrlAll(i));
            }
        });

    }

    private void FirstData() {
        adapter = new LibraryMoreAdapter(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
//        data = new ArrayList<>();
        bean = new EncyclopediaBean();
        dataPop = new ArrayList<>();
    }

    public String UrlAll(int i) {
        intent = getIntent();
        urlG = intent.getStringExtra("key");
        urlId = intent.getStringExtra("id");
        nameTv = intent.getStringExtra("name");
//        Log.d("aaa", urlId+"");
//        Log.d("think", UrlValue.FUCKONE + urlG + UrlValue.FUCKTWO + urlId + UrlValue.FUCKTHREE + i + UrlValue.FUCKFOUR);
        return UrlValue.FUCKONE + urlG + UrlValue.FUCKTWO + urlId + UrlValue.FUCKTHREE + i + UrlValue.FUCKFOUR;
    }

    // J 为popMore 的 拼接
    public String UrlMore(int i,String j){
        Log.d("jj", UrlValue.POPONE + urlG + UrlValue.POPTWO + urlId + UrlValue.POPTHREE + j + UrlValue.POPFOUR + i + UrlValue.POPFIVE);
        return UrlValue.POPONE + urlG + UrlValue.POPTWO + urlId + UrlValue.POPTHREE + j + UrlValue.POPFOUR + i + UrlValue.POPFIVE;
    }

    // ok 是传来的pop忧桑
    public String UrlPop(int ok, int i) {
        Log.d("jj", UrlValue.FUCKONE + urlG + UrlValue.FUCKTWO + urlId + "(&sub_value=" + ok + ")" + UrlValue.FUCKTHREE + i + UrlValue.FUCKFOUR);
        return UrlValue.FUCKONE + urlG + UrlValue.FUCKTWO + urlId + "(&sub_value=" + ok + ")" + UrlValue.FUCKTHREE + i + UrlValue.FUCKFOUR;
    }

    private void StartUrl(String url) {
        NetHelper.MyRequest(url, LibraryMoreBean.class, new NetListener<LibraryMoreBean>() {
            @Override
            public void successListener(LibraryMoreBean response) {
//                data = response.getFoods();
                adapter.setData(response.getFoods());
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.library_more_all:
                popupWindow.showAsDropDown(btnPop);
                break;
            case R.id.library_more_nutritional_element_ib:
                popupWindowMore.setAnimationStyle(R.style.popwin_anim_style);
                popupWindowMore.showAsDropDown(imgBtn);
                break;
        }
    }

    // popMore的接口回调
    @Override
    public void PopListener(String pos) {
        adapter.clean();
        StartUrl(UrlMore(i,pos));
    }

    @Override
    public void OtherListener(String data) {

    }
}
