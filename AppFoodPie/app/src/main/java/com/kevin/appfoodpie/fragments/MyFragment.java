package com.kevin.appfoodpie.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kevin.appfoodpie.CollectionActivity;
import com.kevin.appfoodpie.LoginActivity;
import com.kevin.appfoodpie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFragment{

    private Button btnLogin;
    private TextView tvCollection;

    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    void initView(View view) {
        btnLogin = (Button) view.findViewById(R.id.fragment_my_btn);
        tvCollection = (TextView) view.findViewById(R.id.myCollection_tv);
    }

    @Override
    void initData() {
        // 跳转到登录页
        LoginBtn();

        tvCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);
            }
        });

    }

    private void LoginBtn() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
