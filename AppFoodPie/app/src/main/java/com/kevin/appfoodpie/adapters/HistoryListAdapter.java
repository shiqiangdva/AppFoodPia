package com.kevin.appfoodpie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.values.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/12/5.
 */

public class HistoryListAdapter extends BaseAdapter{

    private List<Food> data;
    private Context context;

    public HistoryListAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<Food> data) {
//        this.data = data;
        this.data.addAll(data);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
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
        HisViewHolder hisViewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_his,parent,false);
            hisViewHolder = new HisViewHolder(convertView);
            convertView.setTag(hisViewHolder);
        }else {
            hisViewHolder = (HisViewHolder) convertView.getTag();
        }
        hisViewHolder.tv.setText(data.get(position).getName());
        return convertView;
    }

    class HisViewHolder{
        private TextView tv;
        public HisViewHolder(View view) {
            tv = (TextView) view.findViewById(R.id.his_tv);
        }
    }

}
