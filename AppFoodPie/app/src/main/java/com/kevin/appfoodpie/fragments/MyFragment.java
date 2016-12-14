package com.kevin.appfoodpie.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.appfoodpie.CollectionActivity;
import com.kevin.appfoodpie.LoginActivity;
import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.TestActivity;
import com.kevin.appfoodpie.beans.EventBusBean;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFragment{

    private Button btnLogin;
    private TextView tvCollection;

    private ImageView img;
    private TextView tv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    void initView(View view) {
        btnLogin = (Button) view.findViewById(R.id.fragment_my_btn);
        tvCollection = (TextView) view.findViewById(R.id.myCollection_tv);

        img = (ImageView) view.findViewById(R.id.iv_my);
        tv = (TextView) view.findViewById(R.id.tv_my);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    void initData() {

        // 跳转到登录页
        LoginBtn();

        // sp 获取数据
        spGetData();


        // 收藏的点击事件
        collectionBtn();

    }

    private void spGetData() {
        // SP取
        SharedPreferences sp = getActivity().getSharedPreferences("L", Context.MODE_PRIVATE);

        if (sp.getString("EbImg","1").equals("1")){
            img.setImageResource(R.mipmap.img_default_avatar);
        }else {
            Picasso.with(getContext()).load(sp.getString("EbImg",null)).into(img);
        }
        tv.setText(sp.getString("EbTv","我的"));
        btnLogin.setText(sp.getString("EbBtn","点击登录"));
    }

    private void collectionBtn() {
        tvCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(EventBusBean bean){
        Picasso.with(getContext()).load(bean.getImg()).into(img);
        tv.setText(bean.getName());
        btnLogin.setText(bean.getBtn());

        SharedPreferences.Editor editor = getContext().getSharedPreferences("L", Context.MODE_PRIVATE).edit();
        editor.putString("EbImg",bean.getImg());
        editor.putString("EbTv",bean.getName());
        editor.putString("EbBtn",bean.getBtn());
        editor.commit();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    private void LoginBtn() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnLogin.getText().toString().equals("点击登录")){
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    Log.e("MyFragment", "abc");
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("L", Context.MODE_PRIVATE).edit();
                    editor.putString("EbImg",null);
                    editor.putString("EbTv",null);
                    editor.putString("EbBtn",null);
                    editor.commit();

                    // back 方法
                    back();

                    SharedPreferences sp = getActivity().getSharedPreferences("L",Context.MODE_PRIVATE);

                    img.setImageResource(R.mipmap.img_default_avatar);
                    tv.setText(sp.getString("EbTv","我的"));
                    btnLogin.setText(sp.getString("EbBtn","点击登录"));

                }
            }
        });
    }

    public void back(){

        Platform mPf = ShareSDK.getPlatform(getActivity(), SinaWeibo.NAME);
        //如果要删除授权信息，重新授权
        ShareSDK.removeCookieOnAuthorize(true);
        mPf.removeAccount();


    }

}
