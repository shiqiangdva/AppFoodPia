package com.kevin.appfoodpie;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kevin.appfoodpie.adapters.CollectionAdapter;
import com.kevin.appfoodpie.fragments.ArticleFragment;
import com.kevin.appfoodpie.fragments.CFoodFragment;

import java.util.ArrayList;

public class CollectionActivity extends BaseActivity {

    private ArrayList<Fragment> data;
    private CollectionAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView imgBack;

    @Override
    int setLayout() {
        return R.layout.activity_collection;
    }

    @Override
    void initView() {
        data = new ArrayList<>();
        tabLayout = (TabLayout) findViewById(R.id.tab_collection);
        viewPager = (ViewPager) findViewById(R.id.vp_collection);
        imgBack = (ImageView) findViewById(R.id.collection_iv_back);
    }

    @Override
    void initData() {
        // 两个fragment的数据
        fragmentData();

        // 返回按钮
        backBtn();
    }

    private void backBtn() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void fragmentData() {
        data.add(new ArticleFragment());
        data.add(new CFoodFragment());
        adapter = new CollectionAdapter(getSupportFragmentManager(),data);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.BLACK,Color.argb(255,255,1285,0));
        tabLayout.getTabAt(0).setText("文章");
        tabLayout.getTabAt(1).setText("食物");
    }
}
