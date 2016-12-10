package com.kevin.appfoodpie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.values.DataMore;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/12/10.
 */

public class CFoodAdapter extends BaseAdapter{

    private List<DataMore> data;
    private Context context;

    public CFoodAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<DataMore> data) {
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
        CViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_food_c,parent,false);
            viewHolder = new CViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (CViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(data.get(position).getImg()).into(viewHolder.img);
        viewHolder.tvName.setText(data.get(position).getName());
        viewHolder.tvNum.setText(data.get(position).getQk());
        return convertView;
    }

    class CViewHolder{
        private CircleImageView img;
        private TextView tvName,tvNum;
        public CViewHolder(View view) {
            img = (CircleImageView) view.findViewById(R.id.food_img);
            tvName = (TextView) view.findViewById(R.id.food_tv_name);
            tvNum = (TextView) view.findViewById(R.id.food_tv_num);
        }
    }

}
