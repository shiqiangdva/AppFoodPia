package com.kevin.appfoodpie.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kevin.appfoodpie.LoginActivity;
import com.kevin.appfoodpie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFragment{

    private Button btnLogin;

    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    void initView(View view) {
        btnLogin = (Button) view.findViewById(R.id.fragment_my_btn);
    }

    @Override
    void initData() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
