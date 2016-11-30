package com.kevin.appfoodpie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LoginActivity extends BaseActivity {

    private ImageButton btnBack;

    @Override
    int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    void initView() {
        btnBack = (ImageButton) findViewById(R.id.my_login_return);
    }

    @Override
    void initData() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
