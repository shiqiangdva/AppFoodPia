package com.kevin.appfoodpie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class SecondActivity extends BaseActivity {

    private ImageView imgSmall,imgLager;
    private TextView tvTitle,tvLike;

    @Override
    int setLayout() {
        return R.layout.activity_second;
    }

    @Override
    void initView() {
        imgSmall = (CircleImageView) findViewById(R.id.aty_sec_img_small);
        imgLager = (ImageView) findViewById(R.id.aty_sec_img_big);
        tvTitle = (TextView) findViewById(R.id.aty_sec_tv_title);
        tvLike = (TextView) findViewById(R.id.aty_sec_tv_like);
    }

    @Override
    void initData() {
        Intent intent = getIntent();
        String imgS = intent.getStringExtra("publisher_avatar");
        String imgB = intent.getStringExtra("card_image");
        String tvT = intent.getStringExtra("title");
        int tvL = intent.getIntExtra("like_ct",100);

//        Log.d("cc", tvL+"");

        Picasso.with(this).load(imgS).into(imgSmall);
        Picasso.with(this).load(imgB).into(imgLager);
        tvTitle.setText(tvT);
        tvLike.setText(tvL+"");
    }
}
