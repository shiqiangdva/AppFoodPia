package com.kevin.appfoodpie;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import com.android.volley.VolleyError;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.kevin.appfoodpie.adapters.PopMoreAdapter;
import com.kevin.appfoodpie.adapters.SearchAdapter;
import com.kevin.appfoodpie.beans.PopBean;
import com.kevin.appfoodpie.beans.SearchBean;
import com.kevin.appfoodpie.values.NetHelper;
import com.kevin.appfoodpie.values.NetListener;
import com.kevin.appfoodpie.values.PopClick;
import com.kevin.appfoodpie.values.UrlValue;

import java.util.List;

public class SearchDetailsActivity extends BaseActivity implements View.OnClickListener, PopClick {

    private EditText etSearch;
    private LRecyclerView recyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private SearchAdapter adapter;
    private List<SearchBean.ItemsBean> data;
    private int i = 1;
    private ImageButton btnDel,btnReturn;
    private ImageButton btnPop;
    private PopupWindow popupWindow;
    private PopBean popBean;
    private PopMoreAdapter popMoreAdapter;
    private RecyclerView rv;
    private Intent intent;

    @Override
    int setLayout() {
        return R.layout.activity_search_details;
    }

    @Override
    void initView() {
        etSearch = (EditText) findViewById(R.id.search_edt_more);
        recyclerView = (LRecyclerView) findViewById(R.id.search_rv_more);
        adapter = new SearchAdapter(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        btnDel = (ImageButton) findViewById(R.id.search_delete_more);
        btnReturn = (ImageButton) findViewById(R.id.search_return_more);
        btnPop = (ImageButton) findViewById(R.id.nutritional_more);
        popBean = new PopBean();
        popMoreAdapter = new PopMoreAdapter(this);
    }

    @Override
    void initData() {

        // 显示数据在rv中
        GetData();

        // 按钮的点击事件
        BtnDelFinish();

        popupWindow = new PopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = getLayoutInflater().inflate(R.layout.pop_other,null);
        popupWindow.setContentView(view);
        // 点击外面让popup消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        rv = (RecyclerView) view.findViewById(R.id.pop_other);
        NetHelper.MyRequest(UrlValue.POP, PopBean.class, new NetListener<PopBean>() {
            @Override
            public void successListener(PopBean response) {
                popBean = response;
                popMoreAdapter.setData(popBean);
                rv.setAdapter(popMoreAdapter);
                GridLayoutManager manager = new GridLayoutManager(SearchDetailsActivity.this, 3);
                rv.setLayoutManager(manager);
                btnPop.setOnClickListener(SearchDetailsActivity.this);
                popMoreAdapter.setPopClick(SearchDetailsActivity.this);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

    }

    private void BtnDelFinish() {
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.kevin.appfoodpie.MY_BR");
                intent.putExtra("My_BR","刷新数据");
                sendBroadcast(intent);
                finish();
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchDetailsActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void GetData() {
        StartUrl(UrlAll(1));
        recyclerView.setAdapter(lRecyclerViewAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                i = i + 1;
                StartUrl(UrlAll(i));
            }
        });
    }

    private void StartUrl(String url) {
        NetHelper.MyRequest(url, SearchBean.class, new NetListener<SearchBean>() {
            @Override
            public void successListener(SearchBean response) {
                data = response.getItems();
                adapter.setData(data);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    public String UrlAll(int i){
        intent = getIntent();
        etSearch.setText(intent.getStringExtra("food"));
        return UrlValue.SEARCHONE + i + UrlValue.SEARCHTWO + intent.getStringExtra("food");
    }

    public String UrlOther(int i,String data){
        return UrlValue.SEARCHONE + i + UrlValue.SEARCHTWO + intent.getStringExtra("food") + UrlValue.SEARCHTHREE + data;
    }

    @Override
    public void onClick(View v) {
        popupWindow.showAsDropDown(btnPop);
    }

    @Override
    public void PopListener(String pos) {

    }

    @Override
    public void OtherListener(String data) {
        adapter.Clean();
        StartUrl(UrlOther(i,data));
    }
}
