package com.kevin.appfoodpie;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.kevin.appfoodpie.beans.ThreeBean;
import com.kevin.appfoodpie.values.DBTool;
import com.kevin.appfoodpie.values.DataMore;
import com.kevin.appfoodpie.values.NetHelper;
import com.kevin.appfoodpie.values.NetListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThreeActivity extends BaseActivity{
    private int i;

    private CircleImageView img;
    private TextView tvName;
    private TextView tvQk,tvDb,tvZf,tvTs,tvSs;
    private TextView tvQk_b,tvDb_b,tvZf_b,tvTs_b,tvSs_b;
    private ImageView imgBack;
    private EditText et;
    private TextView tvB;
    private ImageView love;
    private String imgUrl;
    private String fuck;

    @Override
    int setLayout() {
        return R.layout.activity_three;
    }

    @Override
    void initView() {
        img = (CircleImageView) findViewById(R.id.three_acy_img);
        tvName = (TextView) findViewById(R.id.three_acy_tv_name);

        tvQk = (TextView) findViewById(R.id.tv_sb_qk);
        tvDb = (TextView) findViewById(R.id.tv_sb_dbz);
        tvZf = (TextView) findViewById(R.id.tv_sb_zf);
        tvTs = (TextView) findViewById(R.id.tv_sb_tsh);
        tvSs = (TextView) findViewById(R.id.tv_sb_ssxw);

        tvQk_b = (TextView) findViewById(R.id.tv_sb_qk_b);
        tvDb_b = (TextView) findViewById(R.id.tv_sb_dbz_b);
        tvZf_b = (TextView) findViewById(R.id.tv_sb_zf_b);
        tvTs_b = (TextView) findViewById(R.id.tv_sb_tsh_b);
        tvSs_b = (TextView) findViewById(R.id.tv_sb_ssxw_b);

        imgBack = (ImageView) findViewById(R.id.three_acy_back);
        et = (EditText) findViewById(R.id.three_acy_et);
        tvB = (TextView) findViewById(R.id.three_acy_tv_qk);
        love = (ImageView) findViewById(R.id.three_acy_love);
    }

    @Override
    void initData() {
        // 判断进入哪个getIntent
        ifGoWhere();

        // 返回按钮
        backBtn();

        if (!DBTool.getInstance().isDataSave(tvName.getText().toString())) {
            i = 1;
            Log.d("hh", "我走了这一步");
            love.setSelected(false);
        } else {
            i = 2;
            love.setSelected(true);
        }

        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i % 2 != 0) {
                    // 没存 要存 单数
                    love.setSelected(true);
////////////
                    Intent intent = getIntent();
                    if (intent.getStringExtra("if").equals("2")){
                        imgUrl = intent.getStringExtra("imgT");
                    }else {
                        imgUrl = fuck;
                    }
////////////
                    DataMore dataMore = new DataMore(null,imgUrl,tvName.getText().toString(),
                            tvQk.getText().toString(),tvDb.getText().toString(),
                            tvZf.getText().toString(),tvTs.getText().toString(),tvSs.getText().toString());
                    DBTool.getInstance().insertData(dataMore);

                    i = i + 1;
                } else {
                    // 存过 要删 双数
                    love.setSelected(false);
                    i = i + 1;
                    DBTool.getInstance().deleteByData(tvName.getText().toString());
                }
            }
        });

    }

    private void ifGoWhere() {
        Intent intent = getIntent();
        if (intent.getStringExtra("if").equals("2")){
            // 展示数据
            showData();

        }else {
            et.setText(intent.getStringExtra("name6"));
            // 解析数据
            StartUrl(UrlAll());
        }
    }

    public String UrlAll(){
        Intent intent = getIntent();
        String code = intent.getStringExtra("code6");
        String type = intent.getStringExtra("type6");
        String name = intent.getStringExtra("name6");
        Log.d("ThreeActivity", "http://food.boohee.com/fb/v1/" + type + "s/" + code + "/mode_show?token=" + name);
        return "http://food.boohee.com/fb/v1/" + type + "s/" + code + "/mode_show?token=" + name;
    }

    private void StartUrl(String url) {
        NetHelper.MyRequest(url, ThreeBean.class, new NetListener<ThreeBean>() {
            @Override
            public void successListener(ThreeBean response) {
                fuck = response.getThumb_image_url();
                Picasso.with(ThreeActivity.this).load(response.getThumb_image_url()).into(img);
                tvName.setText(response.getName());
//                et.setText(response.getName());
                tvB.setText(response.getCalory());

                tvQk.setText(response.getCalory());
                tvDb.setText(response.getProtein());
                tvZf.setText(response.getFat());
                tvTs.setText(response.getCarbohydrate());
                tvSs.setText(response.getFiber_dietary());

                if (Float.valueOf(response.getCalory()) > 250){
                    tvQk_b.setText("高热量");
                }else if (Float.valueOf(response.getCalory()) < 150){
                    tvQk_b.setText("低热量");
                }else {
                    tvQk_b.setText("");
                }

                if (Float.valueOf(response.getProtein()) > 10){
                    tvDb_b.setText("高蛋白");
                }else {
                    tvDb_b.setText("");
                }

                if (Float.valueOf(response.getFat()) > 15){
                    tvZf_b.setText("高脂肪");
                }else if (Float.valueOf(response.getFat()) < 2){
                    tvZf_b.setText("低脂肪");
                }else {
                    tvZf_b.setText("");
                }

                if (Float.valueOf(response.getCarbohydrate()) > 20){
                    tvTs_b.setText("高糖分");
                }else {
                    tvTs_b.setText("");
                }

                if (Float.valueOf(response.getFiber_dietary()) > 1){
                    tvSs_b.setText("高纤维");
                }else {
                    tvSs_b.setText("");
                }

            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    private void backBtn() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com");
                intent.putExtra("SX","SX");
                sendBroadcast(intent);
                finish();
            }
        });
    }

    private void showData() {
        Intent intent = getIntent();
        Picasso.with(this).load(intent.getStringExtra("imgT")).into(img);
        tvName.setText(intent.getStringExtra("nameT"));
        et.setText(intent.getStringExtra("nameT"));
        tvB.setText(intent.getStringExtra("caloryQk"));

        tvQk.setText(intent.getStringExtra("caloryQk"));
        tvDb.setText(intent.getStringExtra("proteinDbz"));
        tvZf.setText(intent.getStringExtra("fatZf"));
        tvTs.setText(intent.getStringExtra("carbohydrateTs"));
        tvSs.setText(intent.getStringExtra("fiber_dietarySsxw"));

        if (Float.valueOf(intent.getStringExtra("caloryQk")) > 250){
            tvQk_b.setText("高热量");
        }else if (Float.valueOf(intent.getStringExtra("caloryQk")) < 150){
            tvQk_b.setText("低热量");
        }else {
            tvQk_b.setText("");
        }

        if (Float.valueOf(intent.getStringExtra("proteinDbz")) > 10){
            tvDb_b.setText("高蛋白");
        }else {
            tvDb_b.setText("");
        }

        if (Float.valueOf(intent.getStringExtra("fatZf")) > 15){
            tvZf_b.setText("高脂肪");
        }else if (Float.valueOf(intent.getStringExtra("fatZf")) < 2){
            tvZf_b.setText("低脂肪");
        }else {
            tvZf_b.setText("");
        }

        if (Float.valueOf(intent.getStringExtra("carbohydrateTs")) > 20){
            tvTs_b.setText("高糖分");
        }else {
            tvTs_b.setText("");
        }

        if (Float.valueOf(intent.getStringExtra("fiber_dietarySsxw")) > 1){
            tvSs_b.setText("高纤维");
        }else {
            tvSs_b.setText("");
        }
    }
}
