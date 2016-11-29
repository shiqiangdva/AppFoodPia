package com.kevin.appfoodpie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.beans.EncyclopediaBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/11/23.
 */

public class GirdTwoAdapter extends BaseAdapter{

    private EncyclopediaBean bean;
    private Context context;

    public GirdTwoAdapter(Context context) {
        this.context = context;
    }

    public void setBean(EncyclopediaBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean.getGroup().get(1).getCategories().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getGroup().get(1).getCategories().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_encyclopedia,viewGroup,false);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        myViewHolder.tv.setText(bean.getGroup().get(1).getCategories().get(i).getName());
        Picasso.with(context).load(bean.getGroup().get(1).getCategories().get(i).getImage_url()).into(myViewHolder.img);
        return view;
    }
    class MyViewHolder{
        private ImageView img;
        private TextView tv;
        public MyViewHolder(View view) {
            img = (ImageView) view.findViewById(R.id.item_enc_img);
            tv = (TextView) view.findViewById(R.id.item_enc_tv);
        }
    }
}
