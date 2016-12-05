package com.kevin.appfoodpie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.beans.TenBean;
import com.kevin.appfoodpie.values.TenClick;

/**
 * Created by dllo on 16/12/5.
 */

public class TenAdapter extends RecyclerView.Adapter<TenAdapter.TenViewHolder>{

    private Context context;
    private TenBean bean;
    private TenClick tenClick;

    public void setTenClick(TenClick tenClick) {
        this.tenClick = tenClick;
    }

    public TenAdapter(Context context) {
        this.context = context;
    }

    public void setBean(TenBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public TenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ten,parent,false);
        TenViewHolder holder = new TenViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TenViewHolder holder, final int position) {
        holder.tv.setText(bean.getKeywords().get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenClick.TenListener(bean.getKeywords().get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return bean != null ? bean.getKeywords().size() : 0;
    }

    class TenViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public TenViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_ten);
        }
    }
}
