package com.kevin.appfoodpie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.beans.EvaluationBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/24.
 */

public class EvaluationAdapter extends BaseAdapter{

    private List<EvaluationBean.FeedsBean> data;
    private Context context;

    public EvaluationAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<EvaluationBean.FeedsBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void clean(){
        data.clear();
    }

    @Override
    public int getCount() {
        return data.size() != 0 ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position) != null ? data.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fragment_eva,parent,false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(data.get(position).getBackground()).into(myViewHolder.img);
        myViewHolder.tv_source.setText(data.get(position).getSource());
        myViewHolder.tv_title.setText(data.get(position).getTitle());
        myViewHolder.tv_tail.setText(data.get(position).getTail());

        return convertView;
    }

    class MyViewHolder{
        private ImageView img;
        private TextView tv_source,tv_title,tv_tail;
        public MyViewHolder(View view) {
            img = (ImageView) view.findViewById(R.id.item_fragment_eva_background_img);
            tv_source = (TextView) view.findViewById(R.id.item_fragment_eva_source_tv);
            tv_title = (TextView) view.findViewById(R.id.item_fragment_eva_title_tv);
            tv_tail = (TextView) view.findViewById(R.id.item_fragment_eva_tail_tv);
        }
    }

}
