package com.kevin.appfoodpie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kevin.appfoodpie.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/1.
 */

public class PopAdapter extends BaseAdapter{

    private ArrayList<String> data;
    private Context context;

    public PopAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null && data.size() > 0 ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data != null ? data.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyPopViewHolder myPopViewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pop_list,parent,false);
            myPopViewHolder = new MyPopViewHolder(convertView);
            convertView.setTag(myPopViewHolder);
        }else {
            myPopViewHolder = (MyPopViewHolder) convertView.getTag();
        }
        myPopViewHolder.tvP.setText(data.get(position));
        return convertView;
    }

    class MyPopViewHolder{
        private TextView tvP;
        public MyPopViewHolder(View view) {
            tvP = (TextView) view.findViewById(R.id.tv_popWindow_item);
        }
    }

}
