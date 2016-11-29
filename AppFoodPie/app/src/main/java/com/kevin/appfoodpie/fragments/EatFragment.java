package com.kevin.appfoodpie.fragments;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.adapters.TabAdapter;
import com.kevin.appfoodpie.values.UrlValue;

import java.util.ArrayList;
/**
 * A simple {@link Fragment} subclass.
 */
public class EatFragment extends BaseFragment {

    private ArrayList<Fragment> data;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected int setLayout() {
        return R.layout.fragment_eat;
    }

    @Override
    void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.eat_fragment_tab);
        viewPager = (ViewPager) view.findViewById(R.id.eat_fragment_vp);
    }

    @Override
    void initData() {
        data = new ArrayList<>();

        data.add(new FirstFragment());
        data.add(new EvaluationFragment());
        data.add(KnowledgeFragment.newInstance(UrlValue.BVI_ONE,UrlValue.BVI_TWO));
        data.add(KnowledgeFragment.newInstance(UrlValue.CVI_ONE,UrlValue.CVI_TWO));
        TabAdapter adapter = new TabAdapter(getChildFragmentManager(),data);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.BLACK,Color.argb(255,255,1285,0));

        tabLayout.getTabAt(0).setText("首页");
        tabLayout.getTabAt(1).setText("测评");
        tabLayout.getTabAt(2).setText("知识");
        tabLayout.getTabAt(3).setText("美食");


    }

}
