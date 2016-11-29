package com.kevin.appfoodpie;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.kevin.appfoodpie.fragments.EatFragment;
import com.kevin.appfoodpie.fragments.EncyclopediaFragment;
import com.kevin.appfoodpie.fragments.MyFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_encyclopedia,btn_eat,btn_my;

    @Override
    int setLayout() {
        return R.layout.activity_main;

    }

    @Override
    void initView() {
        btn_encyclopedia = (Button) findViewById(R.id.rbt_encyclopedia);
        btn_eat = (Button) findViewById(R.id.rbt_eat);
        btn_my = (Button) findViewById(R.id.rbt_my);
    }

    @Override
    void initData() {
        // 第一次进入时显示第一个界面
        firstInto();

        // 加监听事件
        addListener();

    }

    private void firstInto() {
        replaceFragment(R.id.mainAty_place,new EncyclopediaFragment());
        btn_encyclopedia.setSelected(true);
    }

    private void addListener() {
        btn_encyclopedia.setOnClickListener(this);
        btn_eat.setOnClickListener(this);
        btn_my.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rbt_encyclopedia:
                btn_encyclopedia.setSelected(true);
                btn_my.setSelected(false);
                btn_eat.setSelected(false);
                replaceFragment(R.id.mainAty_place,new EncyclopediaFragment());
                break;
            case R.id.rbt_eat:
                btn_encyclopedia.setSelected(false);
                btn_my.setSelected(false);
                btn_eat.setSelected(true);
                replaceFragment(R.id.mainAty_place,new EatFragment());
                break;
            case R.id.rbt_my:
                btn_encyclopedia.setSelected(false);
                btn_my.setSelected(true);
                btn_eat.setSelected(false);
                replaceFragment(R.id.mainAty_place,new MyFragment());
                break;
        }
    }

    // 替换站位布局的方法
    public void replaceFragment(int id, Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(id, fragment);
        transaction.commit();
    }

}
