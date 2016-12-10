package com.kevin.appfoodpie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.values.Url;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/12/10.
 */

public class ArticleAdapter extends BaseAdapter{

    private List<Url> data;
    private Context context;

    public ArticleAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Url> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size() > 0 && data != null ? data.size() : 0;
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
        ArViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_article,parent,false);
            viewHolder = new ArViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ArViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(data.get(position).getId().toString());
        return convertView;
    }

    class ArViewHolder {
        private TextView tv;
        public ArViewHolder(View view) {
            tv = (TextView) view.findViewById(R.id.tv_article);
        }
    }
}
