package com.kevin.appfoodpie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.beans.PopBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/2.
 */

public class PopMoreAdapter extends RecyclerView.Adapter<PopMoreAdapter.PopViewHolder>{

    private PopBean data;
    private Context context;

    public PopMoreAdapter(Context context) {
        this.context = context;
    }

    public void setData(PopBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public PopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pop_more,parent,false);
        PopViewHolder holder = new PopViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PopViewHolder holder, int position) {
        holder.tv.setText(data.getTypes().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data != null ? data.getTypes().size() : 0;
    }

    class PopViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public PopViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_pop_more);
        }
    }

}
